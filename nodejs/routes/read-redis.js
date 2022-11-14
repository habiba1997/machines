const express = require('express');
const router = express.Router();
const redisClient = require('../config/redis-connect');
const {MATERIAL, OPERATION, MACHINE, LOCATION} = require('../constants');
const Material = require('../models/material');
const Location = require('../models/location');
const Machine = require('../models/machine');
const Operation = require('../models/operation');

// @route   GET /
// @desc    Fetch material
// @access  public
router.get('/materials', async (request, response) => {
    try {
        var materials = [];
        const redisMaterialList = await redisClient.hGetAll(MATERIAL);
        Object.entries(redisMaterialList).forEach((jsonStringMaterialMapEntry) => {
            let jsonStringMaterial = jsonStringMaterialMapEntry[1];
            const materialObject = JSON.parse(jsonStringMaterial);
            const material = new Material(materialObject);
            materials.push(material);
        });
        response.json(materials);
    } catch (err) {
        console.error(err.message);
        response.status(500).send({errors: [{msg: 'Server Error'}]});
    }
});

// @route   GET /
// @desc    Fetch location
// @access  public
router.get('/locations', async (request, response) => {
    try {
        var locations = [];
        const redisLocationList = await redisClient.hGetAll(LOCATION);
        Object.entries(redisLocationList).map((jsonStringLocationEntry) => {
            let jsonStringLocation = jsonStringLocationEntry[1];
            const locationObject = JSON.parse(jsonStringLocation[1]);
            const location = new Location(locationObject);
            locations.push(location);
        });
        response.json(locations);
    } catch (err) {
        console.error(err.message);
        response.status(500).send({errors: [{msg: 'Server Error'}]});
    }
});

// @route   GET /
// @desc    Fetch machines
// @access  public
router.get('/machines', async (request, response) => {
    try {
        var machines = [];
        const redisMachineList = await redisClient.hGetAll(MACHINE);
        Object.entries(redisMachineList).forEach((jsonStringMachineEntry) => {
            let jsonStringMachine = jsonStringMachineEntry[1];
            const machineObject = JSON.parse(jsonStringMachine);
            const machine = new Material(machineObject);
            machines.push(machine);
        });
        response.json(machines);
    } catch (err) {
        console.error(err.message);
        response.status(500).send({errors: [{msg: 'Server Error'}]});
    }
});

// @route   GET /
// @desc    Fetch operations
// @access  public
router.get('/operations', async (request, response) => {
    try {
        var operations = [];
        const redisOperationList = await redisClient.hGetAll(OPERATION);
        Object.entries(redisOperationList).map((jsonStringOperationEntry) => {
            let jsonStringOperation = jsonStringOperationEntry[1];
            const operationObject = JSON.parse(jsonStringOperation);
            const operation = new Material(operationObject);
            operations.push(operation);
        });
        response.json(operations);
    } catch (err) {
        console.error(err.message);
        response.status(500).send({errors: [{msg: 'Server Error'}]});
    }
});

module.exports = router;
