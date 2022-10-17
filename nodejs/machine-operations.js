const TopicNames = require('./config/topics');
const redisClient = require('./config/redis-connect');

const { OPERATION, MACHINE, MACHINE_OPERATION } = require('./constants');
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

const changeOperationStatus = async ({ operation, status }) => {
  await redisClient.hGetAll(OPERATION).then((redisReturn) => {
    Object.entries(redisReturn).forEach((obj) => {
      const object = JSON.parse(obj[1]);
      // if we found our operation
      if (object.name === operation) {
        object.status = status;
        setHashValueInRedis(
          OPERATION,
          object.name,
          object,
          'change operation ' + operation + ' to ' + status
        );
      }
    });
  });
  // change operation status in mors as well
  let redisReturn = await redisClient.hGetAll(MACHINE_OPERATION);
  const machineOperations = Object.entries(redisReturn);
  if (machineOperations.length == 0) {
    return;
  }
  machineOperations.forEach((obj) => {
    // obj[0] is map name (machine-operation)
    const morName = obj[0];
    if (morName.split('-')[1] === operation) {
      const morObject = JSON.parse(obj[1]);
      morObject.operation.status = status;
      setHashValueInRedis(
        MACHINE_OPERATION,
        morName,
        morObject,
        'change machine operation status to ' + status
      );
    }
  });
};

const linkMachineOperation = async ({ operation, machine }) => {
  let redisReturn = await redisClient.hGetAll(MACHINE);
  const result_machine = Object.entries(redisReturn)
    .filter((obj) => {
      const object = JSON.parse(obj[1]);
      return object.name === machine;
    })
    .map((m) => JSON.parse(m[1]))[0];
  redisReturn = await redisClient.hGetAll(OPERATION);
  const result_operation = Object.entries(redisReturn)
    .filter((obj) => {
      const object = JSON.parse(obj[1]);
      return object.name === operation;
    })
    .map((m) => JSON.parse(m[1]))[0];

  if (result_machine && result_operation) {
    const machine_object = new Machine(result_machine);
    const operation_object = new Operation(result_operation);
    setHashValueInRedis(
      MACHINE_OPERATION,
      getMORName(machine, operation),
      new MachineOperation(machine_object, operation_object),
      'link ' + machine + ' to ' + operation
    );
  }
};

const unlinkMachineOperation = async ({ operation, machine }) => {
  let redisReturn = await redisClient.hGetAll(MACHINE_OPERATION);
  const machineOperations = Object.entries(redisReturn);
  if (machineOperations.length == 0) {
    return;
  }
  const result_mors = machineOperations.filter((obj) => {
    return obj[0] !== getMORName(machine, operation);
  });
  if (result_mors) {
    redisClient.hDel(MACHINE_OPERATION, getMORName(machine, operation)).then(
      (value) => {
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

const getMORName = (machine, operation) => {
  return machine + '-' + operation;
};

const setHashValueInRedis = (
  hashKey,
  hashField,
  hashValue,
  successfulMessage
) => {
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
