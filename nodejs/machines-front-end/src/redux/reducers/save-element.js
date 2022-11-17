import {
    CREATE_LOCATION, CREATE_MACHINE, CREATE_MATERIAL, CREATE_OPERATION, CREATE_PRODUCTION_ORDER, SQL_ERROR
} from '../actions/types';

const initialState = {
    machine: {}, operation: {}, location: {}, material: {}, production_order: {}, error: {},
};

const saveElement = (state = initialState, action) => {
    const {type, payload} = action;

    switch (type) {
        case CREATE_PRODUCTION_ORDER:
            return {
                ...state, production_order: payload,
            };
        case CREATE_LOCATION:
            return {
                ...state, location: payload,
            };
        case CREATE_MACHINE:
            return {
                ...state, machine: payload,
            };
        case CREATE_OPERATION:
            return {
                ...state, operation: payload,
            };
        case CREATE_MATERIAL:
            return {
                ...state, material: payload,
            };
        case SQL_ERROR:
            return {...state, error: payload};
        default:
            return state;
    }
};

export default saveElement;
