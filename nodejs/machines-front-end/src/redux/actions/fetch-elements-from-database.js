import {
    GET_SQL_PRODUCTION_ORDER,
    GET_SQL_MATERIALS,
    GET_SQL_LOCATIONS,
} from './types';
import {SQL_ENDPOINT} from './endpoints';
import axios from 'axios';
import {setAlert} from './alert-action';


const config = {
    headers: {
        'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*',
    },
};
const endpoint = 'http://' + SQL_ENDPOINT + '/api';

export const getAllProductionOrders = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/production-orders');
        dispatch({
            type: GET_SQL_PRODUCTION_ORDER, payload: res.data,
        });
    } catch (err) {
        dispatch(setAlert(err.name + " : " + err.message, 'danger'));
    }
};

export const getAllMaterials = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/materials');
        dispatch({
            type: GET_SQL_MATERIALS, payload: res.data,
        });
    } catch (err) {
        dispatch(setAlert(err.name + " : " + err.message, 'danger'));
    }
};

export const getAllLocations = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/locations');
        dispatch({
            type: GET_SQL_LOCATIONS, payload: res.data,
        });
    } catch (err) {
        dispatch(setAlert(err.name + " : " + err.message, 'danger'));
    }
};

