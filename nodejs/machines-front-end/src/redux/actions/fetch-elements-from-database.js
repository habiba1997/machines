import {
    GET_SQL_PRODUCTION_ORDER,
    GET_SQL_MATERIALS,
    GET_SQL_LOCATIONS,
    SQL_MATERIALS_ERROR,
    SQL_PRODUCTION_ORDER_ERROR,
    SQL_LOCATIONS_ERROR,
    SQL_ERROR, SQL_MORS_ERROR, GET_SQL_MORS,
} from './types';
import {SQL_ENDPOINT} from '../endpoints';
import axios from 'axios';
import {setAlert} from './alert-action';


const endpoint = 'http://' + SQL_ENDPOINT + '/api';

export const getAllProductionOrders = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/production-orders');
        dispatch({
            type: GET_SQL_PRODUCTION_ORDER, payload: res.data,
        });
    } catch (err) {
        let errMessage = err.response.data.message;
        dispatch(setAlert(errMessage, 'danger'));
        dispatch({type: SQL_PRODUCTION_ORDER_ERROR, payload: err.response.data});
    }
};

export const getAllMaterials = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/materials');
        dispatch({
            type: GET_SQL_MATERIALS, payload: res.data,
        });
    } catch (err) {
        let errMessage = err.response.data.message;
        dispatch(setAlert(errMessage, 'danger'));
        dispatch({type: SQL_MATERIALS_ERROR, payload: err.response.data});
    }
};

export const getAllLocations = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/locations');
        dispatch({
            type: GET_SQL_LOCATIONS, payload: res.data,
        });
    } catch (err) {
        let errMessage = err.response.data.message;
        dispatch(setAlert(errMessage, 'danger'));
        dispatch({type: SQL_LOCATIONS_ERROR, payload: err.response.data});
    }
};
export const getAllMachineOperations = () => async (dispatch) => {
    try {
        const res = await axios.get(endpoint + '/machine-operations');
        dispatch({
            type: GET_SQL_MORS, payload: res.data,
        });
    } catch (err) {
        dispatch(setAlert(err.message, 'danger'));
        dispatch({type: SQL_MORS_ERROR, payload: err});
    }
};
