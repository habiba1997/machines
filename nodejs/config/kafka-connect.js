const kafka = require('kafka-node');
const config = require('config');
const topicNames = require('./topics');

const host = config.get('kafka.host');
const port = config.get('kafka.port');
const groupId = config.get('kafka.groupId');

const options = {
  kafkaHost: `${host}:${port}`, // connect directly to kafka broker (instantiates a KafkaClient)
  sessionTimeout: 15000,
  // An array of partition assignment protocols ordered by preference.
  // 'roundrobin' or 'range' string for built ins (see below to pass in custom assignment protocol)
  protocol: ['roundrobin'],
  encoding: 'utf8', // default is utf8, use 'buffer' for binary data

  // Offsets to use for new groups other options could be 'earliest' or 'none' (none will emit an error if no offsets were saved)
  // equivalent to Java client's auto.offset.reset
  // If set true, consumer will fetch message from the given offset in the payloads
  fromOffset: 'latest', // default
  commitOffsetsOnFirstJoin: true, // on the very first time this consumer group subscribes to a topic, record the offset returned in fromOffset (latest/earliest)

  // Auto commit config
  autoCommit: true,
  autoCommitIntervalMs: 5000,
  // The max wait time is the maximum amount of time in milliseconds to block waiting if insufficient data is available at the time the request is issued, default 100ms
  fetchMaxWaitMs: 1000,
  // This is the minimum number of bytes of messages that must be available to give a response, default 1 byte
  fetchMinBytes: 1,
  // The maximum bytes to include in the message set for this partition. This helps bound the size of the response.
  fetchMaxBytes: 1024 * 1024,
  // If set to 'buffer', values will be returned as raw buffer objects.
  encoding: 'utf8',
  // encoding: "buffer",
  keyEncoding: 'utf8',

  // how to recover from OutOfRangeOffset error (where save offset is past server retention) accepts same value as fromOffset
  outOfRangeOffset: 'earliest', // default
};

options.groupId = groupId;
const topics = Object.values(topicNames);
// Object.values(topicNames).map((topicName) => topics.push({topic: topicName}));
// Object.values(topicNames).map((topicName) => topics.push(topic: topicName));

// const kafkaClient = new kafka.KafkaClient({kafkaHost: `${host}:${port}`});
// const kafkaConsumer = new kafka.Consum(kafkaClient, topics, options);
const kafkaConsumer = new kafka.ConsumerGroup(options, topics);

module.exports = kafkaConsumer;
