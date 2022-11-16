import {
    GET_CACHE_OPERATIONS, GET_CACHE_MACHINES, GET_CACHE_MACHINE_OPERATIONS, GET_CACHE_MATERIALS, GET_CACHE_LOCATIONS
} from './types';
import {CACHE_ENDPOINT} from './endpoints';
import axios from 'axios';
import {setAlert} from './alert-action';


const config = {
    headers: {
        'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*',
    },
};

const endpoint = 'http://' + CACHE_ENDPOINT + '/redis';

export const getAllCacheMachines = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/machines');
        dispatch({
            type: GET_CACHE_MACHINES, payload: res.data,
        });
    } catch (err) {
        setAlert(err.response.statusText);
    }
};

export const getAllCacheOperations = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/operations');
        dispatch({
            type: GET_CACHE_OPERATIONS, payload: res.data,
        });
    } catch (err) {
        setAlert(err.response.statusText);
    }
};


export const getAllCacheMaterials = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/materials');
        dispatch({
            type: GET_CACHE_MATERIALS, payload: res.data,
        });
    } catch (err) {
        setAlert(err.response.statusText);
    }
};


export const getAllCacheLocations = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/locations');
        dispatch({
            type: GET_CACHE_LOCATIONS, payload: res.data,
        });
    } catch (err) {
        setAlert(err.response.statusText);
    }
};


export const getAllCacheMachineOperations = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/machine-operations');
        dispatch({
            type: GET_CACHE_MACHINE_OPERATIONS, payload: res.data,
        });
    } catch (err) {
        setAlert(err.response.statusText);
    }
};


