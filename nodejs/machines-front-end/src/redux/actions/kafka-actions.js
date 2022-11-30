import {
    CHANGE_OPERATION_STATUS, LINK_MACHINE_OPERATION, REMOVE_ALERT, SQL_ERROR, UNLINK_MACHINE_OPERATION
} from './types';
import {SQL_ENDPOINT} from '../endpoints';
import axios from 'axios';
import {setAlert} from './alert-action';


const config = {
    headers: {
        'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*',
    },
};

const endpoint = 'http://' + SQL_ENDPOINT + '/api';

export const changeOperationStatus = (operationName, operationNewStatus, navigate) => async (dispatch) => {
    try {
        const res = await axios.post(endpoint + '/change-status', {operationName, operationNewStatus}, config);
        dispatch({
            type: CHANGE_OPERATION_STATUS, payload: {res},
        });
        setTimeout(() => {
            var messages = res.data.messages;
            messages.forEach((msg) => dispatch(setAlert(msg.message, (msg.messageType === 'ERROR' && 'danger') || ((msg.messageType === 'SUCCESS' && 'success')))));

            if (!messages.some((msg) => (msg.messageType === 'ERROR' && 'danger'))) {
                navigate('/cache/operations');
            }
        }, 100);
    } catch (err) {
        let errMessage = err.response.data.message;
        dispatch(setAlert(errMessage, 'danger'));
        dispatch({type: SQL_ERROR, payload: err.response.data});
    }
};

export const linkMachineOperation = (machineName, operationName, navigate) => async (dispatch) => {
    try {
        const res = await axios.post(endpoint + '/link-machine-operation', {operationName, machineName}, config);
        dispatch({
            type: LINK_MACHINE_OPERATION, payload: {res},
        });
        setTimeout(() => {
            var messages = res.data.messages;
            messages.forEach((msg) => dispatch(setAlert(msg.message, (msg.messageType === 'ERROR' && 'danger') || ((msg.messageType === 'SUCCESS' && 'success')))));
            if (!messages.some((msg) => (msg.messageType === 'ERROR' && 'danger'))) {
                navigate('/cache/machine-operations')
            }
        }, 500);
    } catch (err) {
        let errMessage = err.response.data.message;
        dispatch(setAlert(errMessage, 'danger'));
        dispatch({type: SQL_ERROR, payload: err.response.data});
    }
};

export const unlinkMachineOperation = (machineName, operationName, navigate) => async (dispatch) => {
    try {
        const res = await axios.post(endpoint + '/unlink-machine-operation', {operationName, machineName}, config);
        dispatch({
            type: UNLINK_MACHINE_OPERATION, payload: {res},
        });
        var messages = res.data.messages;
        messages.forEach((msg) => dispatch(setAlert(msg.message, (msg.messageType === 'ERROR' && 'danger') || ((msg.messageType === 'SUCCESS' && 'success')))));

        if (!messages.some((msg) => (msg.messageType === 'ERROR' && 'danger'))) {
            navigate('/cache/machine-operations');
        }
    } catch (err) {
        let errMessage = err.response.data.message;
        dispatch(setAlert(errMessage, 'danger'));
        dispatch({type: SQL_ERROR, payload: err.response.data});
    }

};


