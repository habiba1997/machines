import {
    GET_CACHE_OPERATIONS,
    GET_CACHE_MACHINES,
    GET_CACHE_MACHINE_OPERATIONS,
    GET_CACHE_MATERIALS,
    GET_CACHE_LOCATIONS,
    MATERIALS_CACHE_ERROR,
    MACHINE_CACHE_ERROR,
    OPERATION_CACHE_ERROR,
    MACHINE_OPERATIONS_CACHE_ERROR,
    LOCATIONS_CACHE_ERROR
} from '../actions/types';

const initialState = {
    machines: [],
    operations: [],
    locations: [],
    materials: [],
    machine_operations: [],
    machine_loading: true,
    operation_loading: true,
    mor_loading: true,
    material_loading: true,
    location_loading: true,
    error: {},
};

const cacheElements = (state = initialState, action) => {
    const {type, payload} = action;

    switch (type) {
        case GET_CACHE_MACHINES:
            return {
                ...state, machines: payload, machine_loading: false,
            };
        case GET_CACHE_LOCATIONS:
            return {
                ...state, locations: payload, location_loading: false,
            };
        case GET_CACHE_MATERIALS:
            return {
                ...state, materials: payload, material_loading: false,
            };
        case GET_CACHE_OPERATIONS:
            return {
                ...state, operations: payload, operation_loading: false,
            };
        case GET_CACHE_MACHINE_OPERATIONS:
            return {
                ...state, machine_operations: payload, mor_loading: false,
            };
        case MACHINE_CACHE_ERROR:
            return {
                ...state, error: payload, machine_loading: false,

            };
        case MACHINE_OPERATIONS_CACHE_ERROR:
            return {
                ...state, error: payload, mor_loading: false,
            };
        case MATERIALS_CACHE_ERROR:
            return {
                ...state, error: payload, material_loading: false,
            };
        case LOCATIONS_CACHE_ERROR:
            return {
                ...state, error: payload, location_loading: false,
            };
        case OPERATION_CACHE_ERROR:
            return {
                ...state, error: payload, operation_loading: false,
            };
        default:
            return state;
    }
};

export default cacheElements;
