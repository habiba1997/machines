import {
    CHANGE_OPERATION_STATUS, LINK_MACHINE_OPERATION, SQL_ERROR, UNLINK_MACHINE_OPERATION
} from '../actions/types';

const initialState = {
    error: {},
};

const kafkaActions = (state = initialState, action) => {
    const {type, payload} = action;

    switch (type) {
        case CHANGE_OPERATION_STATUS:
            return {
                ...state, error: payload,
            };
        case LINK_MACHINE_OPERATION:
            return {
                ...state, error: payload,
            };
        case UNLINK_MACHINE_OPERATION:
            return {
                ...state, error: payload,
            };
        case SQL_ERROR:
            return {
                ...state, error: payload,
            };
        default:
            return state;
    }
};

export default kafkaActions;
