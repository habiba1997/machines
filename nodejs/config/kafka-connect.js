const kafka = require('kafka-node');
const config = require('config');
const topicNames = require('./topics');

const host = config.get('kafka.host');
const port = config.get('kafka.port');
const groupId = config.get('kafka.groupId');

const kafkaOptions = {
    kafkaHost: `${host}:${port}`, // connect directly to kafka broker (instantiates a KafkaClient)
    sessionTimeout: 150000,
    sasl: {
        mechanism: 'plain',
        username: '7A5Y6NU3LPZFTMV3',
        password: 'SI9EuIEuDpxG1xRWdyznWzizDL9JOfytlWQTqpkr1PHzGoyY8ZucrEnDeXBnDYQ1',
    },
};

kafkaOptions.groupId = groupId;
const topics = Object.values(topicNames);
const kafkaConsumer = new kafka.ConsumerGroup(kafkaOptions, topics);

module.exports = kafkaConsumer;
