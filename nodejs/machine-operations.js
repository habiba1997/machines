const TopicNames = require('./config/topics');
const redisClient = require('./config/redis-connect');

const {OPERATION, MACHINE, MACHINE_OPERATION} = require('./constants');
const Machine = require('./models/machine');
const Operation = require('./models/operation');
const MachineOperation = require('./models/machine-operation');
const kafkaConsumer = require('./config/kafka-connect');

kafkaConsumer.on('message', async (message) => {
    console.log(message);
    const topicName = message.topic;
    const topicValue = JSON.parse(message.value);
    switch (topicName) {
        case TopicNames.OPERATION_CHANGE_STATUS:
            await changeOperationStatus(topicValue);
            break;
        case TopicNames.MACHINE_OPERATION_LINKED:
            await linkMachineOperation(topicValue);
            break;
        case TopicNames.MACHINE_OPERATION_UNLINKED:
            await unlinkMachineOperation(topicValue);
            break;
    }
});

const changeOperationStatus = async ({operation, status}) => {
    await redisClient.hGetAll(OPERATION).then((operationJsonStringMapEntry) => {
        Object.entries(operationJsonStringMapEntry).forEach((operationJsonStringEntry) => {
            var operationStringObject = operationJsonStringMapEntry[1];
            const operationJsonObject = JSON.parse(operationStringObject);
            // if we found our operation
            if (operationJsonObject.name === operation) {
                operationJsonObject.status = status;
                setHashValueInRedis(
                    OPERATION,
                    operationJsonObject.name,
                    operationJsonObject,
                    'change operation ' + operation + ' to ' + status
                );
            }
        });
    });
    // change operation status in mors as well
    let machineOperationRedisMapEntry = await redisClient.hGetAll(MACHINE_OPERATION);
    const machineOperations = Object.entries(machineOperationRedisMapEntry);
    if (machineOperations.length == 0) {
        return;
    }
    machineOperations.forEach((machineOperationStringEntry) => {
        // obj[0] is map name (machine-operation)
        const machineOperationKeyName = machineOperationStringEntry[0];
        let operationName = machineOperationKeyName.split('-')[1];
        if (operationName === operation) {
            let machineOperationStringObject = machineOperationStringEntry[1];
            const machineOperationObject = JSON.parse(machineOperationStringObject);
            machineOperationObject.operation.status = status;
            setHashValueInRedis(
                MACHINE_OPERATION,
                machineOperationKeyName,
                machineOperationObject,
                'change machine operation status to ' + status
            );
        }
    });
};

const linkMachineOperation = async ({operation, machine}) => {
    let machineRedisObjectList = await redisClient.hGetAll(MACHINE);
    const resultMachine = findObjectByNameFromStringJsonList(machineRedisObjectList, machine);
    let operationRedisObjectList = await redisClient.hGetAll(OPERATION);
    const resultOperation = findObjectByNameFromStringJsonList(operationRedisObjectList, operation)

    if (resultMachine && resultOperation) {
        setHashValueInRedis(
            MACHINE_OPERATION,
            getMachineOperationName(machine, operation),
            new MachineOperation(new Machine(resultMachine), new Operation(resultOperation)),
            'link ' + machine + ' to ' + operation
        );
    }
};

const unlinkMachineOperation = async ({operation, machine}) => {
    let machineOperationRedisString = await redisClient.hGetAll(MACHINE_OPERATION);
    const machineOperationsMapEntries = Object.entries(machineOperationRedisString);
    if (machineOperationsMapEntries.length == 0) {
        return;
    }
    const resultMachineOperation = machineOperationsMapEntries.filter((machineOperationMapEntry) => {
        let machineOperationKeyName = machineOperationMapEntry[0];
        return machineOperationKeyName !== getMachineOperationName(machine, operation);
    });
    if (resultMachineOperation) {
        redisClient.hDel(MACHINE_OPERATION, getMachineOperationName(machine, operation)).then(
            (success) => {
                console.log(
                    'redis have successfully unlink ',
                    machine,
                    ' from ',
                    operation
                );
            },
            (error) => {
                console.log('ERROR: ', error);
            }
        );
    }
};

const getMachineOperationName = (machine, operation) => {
    return machine + '-' + operation;
};

const getObjectFromStringMapEntry = (StringMapEntry) => {
    return JSON.parse(StringMapEntry[1])
};

const findObjectByNameFromStringJsonList = (stringList, name) => {
    return Object.entries(stringList)
        .filter((stringEntry) => {
            const object = getObjectFromStringMapEntry(stringEntry);
            return object.name === name;
        })
        .map((stringEntry) => {
            let listObjects = getObjectFromStringMapEntry(stringEntry);
            return listObjects[0]; // return only the first one
        });
}

const setHashValueInRedis = (hashKey, hashField, hashValue, successfulMessage) => {
    redisClient.hSet(hashKey, hashField, JSON.stringify(hashValue)).then(
        (value) => {
            console.log('redis have successfully ' + successfulMessage);
        },
        (error) => {
            console.log('ERROR: ', error);
        }
    );
};


kafkaConsumer.on('error', function (err) {
    console.log('error', err);
});

module.exports = kafkaConsumer;
