const express = require('express');
const router = express.Router();
const redisClient = require('../config/redis-connect');
const { MATERIAL, OPERATION, MACHINE, LOCATION } = require('../constants');
const Material = require('../models/material');
const Location = require('../models/location');
const Machine = require('../models/machine');
const Operation = require('../models/operation');

// @route   GET /
// @desc    Fetch material
// @access  public
router.get('/materials', async (req, res) => {
  try {
    var materials = [];
    const redisReturn = await redisClient.hGetAll(MATERIAL);
    Object.entries(redisReturn).map((obj) => {
      const object = JSON.parse(obj[1]);
      const material = new Material(object);
      materials.push(material);
    });
    res.json(materials);
  } catch (err) {
    console.error(err.message);
    res.status(500).send({ errors: [{ msg: 'Server Error' }] });
  }
});

// @route   GET /
// @desc    Fetch location
// @access  public
router.get('/locations', async (req, res) => {
  try {
    var locations = [];
    const redisReturn = await redisClient.hGetAll(LOCATION);
    Object.entries(redisReturn).map((obj) => {
      const object = JSON.parse(obj[1]);
      const location = new Location(object);
      locations.push(location);
    });
    res.json(locations);
  } catch (err) {
    console.error(err.message);
    res.status(500).send({ errors: [{ msg: 'Server Error' }] });
  }
});

// @route   GET /
// @desc    Fetch machines
// @access  public
router.get('/machines', async (req, res) => {
  try {
    var machines = [];
    const redisReturn = await redisClient.hGetAll(MACHINE);
    Object.entries(redisReturn).map((obj) => {
      const object = JSON.parse(obj[1]);
      const machine = new Machine(object);
      machines.push(machine);
    });
    res.json(machines);
  } catch (err) {
    console.error(err.message);
    res.status(500).send({ errors: [{ msg: 'Server Error' }] });
  }
});

// @route   GET /
// @desc    Fetch operations
// @access  public
router.get('/operations', async (req, res) => {
  try {
    var operations = [];
    const redisReturn = await redisClient.hGetAll(OPERATION);
    Object.entries(redisReturn).map((obj) => {
      const object = JSON.parse(obj[1]);
      const operation = new Operation(object);
      operations.push(operation);
    });
    res.json(operations);
  } catch (err) {
    console.error(err.message);
    res.status(500).send({ errors: [{ msg: 'Server Error' }] });
  }
});

module.exports = router;
