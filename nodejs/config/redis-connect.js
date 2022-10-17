const redis = require('redis');
const config = require('config');

const host = config.get('redis.host');
const port = config.get('redis.port');
const client = redis.createClient(port, host);

console.log('Connecting to redis....');
client.on('error', (error) => console.error(`Error : ${error}`));
client.connect();
console.log('Redis Connected :D on ', host, port);

module.exports = client;
