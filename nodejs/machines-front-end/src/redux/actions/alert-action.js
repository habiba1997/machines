import {v4} from 'uuid';
import {SET_ALERT, REMOVE_ALERT} from './types';

//todo: ask about two input methods
export const setAlert = (msg, alertType, timeout = 5000) => (dispatch) => {
    const id = v4();
    // from redux, dispatch action
    dispatch({
        type: SET_ALERT,
        payload: {msg, alertType, id},
    });

    // after setting an alert for some number of time, we need to remove the alert
    setTimeout(
        () =>
            dispatch({
                type: REMOVE_ALERT,
                payload: id,
            }),
        timeout
    );
};

export const closeAlert = (id) => (dispatch) => {
    dispatch({
        type: REMOVE_ALERT,
        payload: id,
    });
};
