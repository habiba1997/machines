import {
    CREATE_PRODUCTION_ORDER,
    CREATE_OPERATION,
    CREATE_MACHINE,
    CREATE_LOCATION, CREATE_MATERIAL
} from './types';
import {SQL_ENDPOINT} from './endpoints';
import axios from 'axios';
import {setAlert} from './alert-action';


const config = {
    headers: {
        'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*',
    },
};

const endpoint = 'http://' + SQL_ENDPOINT + '/api/create';

export const saveLocation = (location, navigate) => async (dispatch) => {
    try {
        const res = await axios.post(endpoint + '/location', location, config);
        dispatch({
            type: CREATE_LOCATION, payload: res.data,
        });
        dispatch(
            setAlert('Location ' + location.name + ' is Created', 'success')
        );
        navigate('/cache/locations')

    } catch (err) {
        dispatch(setAlert(err.name + " : " + err.message, 'danger'));
    }
};

export const saveMaterial = (material, navigate) => async (dispatch) => {
    try {
        const res = await axios.post(endpoint + '/material', material, config);
        dispatch({
            type: CREATE_MATERIAL, payload: res.data,
        });
        dispatch(
            setAlert('Material ' + material.name + ' is Created', 'success')
        );
        navigate('/cache/materials')

    } catch (err) {
        dispatch(setAlert(err.name + " : " + err.message, 'danger'));
    }
};

export const saveMachine = (machine, navigate) => async (dispatch) => {
    try {
        const res = await axios.post(endpoint + '/machine', machine, config);
        dispatch({
            type: CREATE_MACHINE, payload: res.data,
        });
        dispatch(
            setAlert('Machine ' + machine.name + ' is Created', 'success')
        );
        navigate('/cache/machines')

    } catch (err) {
        dispatch(setAlert(err.name + " : " + err.message, 'danger'));
    }
};

export const saveOperation = (operation, navigate) => async (dispatch) => {
    try {
        const res = await axios.post(endpoint + '/operation', operation, config);
        dispatch({
            type: CREATE_OPERATION, payload: res.data,
        });
        dispatch(
            setAlert('Operation ' + operation.name + ' is Created', 'success')
        );
        navigate('/cache/operations')

    } catch (err) {
        dispatch(setAlert(err.name + " : " + err.message, 'danger'));
    }
};

export const saveProductionOrder = (productionOrder, navigate) => async (dispatch) => {
    try {
        const res = await axios.post(endpoint + '/production-order', productionOrder, config);
        dispatch({
            type: CREATE_PRODUCTION_ORDER, payload: res.data,
        });
        dispatch(
            setAlert('Production Order ' + productionOrder.name + ' is Created', 'success')
        );
        navigate('/');

    } catch (err) {
        setAlert(err.name + " : " + err.message, 'danger');
    }
};

