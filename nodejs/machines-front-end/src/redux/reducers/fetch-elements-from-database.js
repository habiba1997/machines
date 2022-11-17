import {
    GET_SQL_PRODUCTION_ORDER,
    GET_SQL_MATERIALS,
    GET_SQL_LOCATIONS,
    SQL_LOCATIONS_ERROR,
    SQL_PRODUCTION_ORDER_ERROR,
    SQL_MATERIALS_ERROR, SQL_MORS_ERROR, GET_SQL_MORS
} from '../actions/types';

const initialState = {
    locations: [],
    materials: [],
    production_orders: [],
    machine_operations: [],
    mor_loading: true,
    material_loading: true,
    location_loading: true,
    po_loading: true,
    error: {},
};

const sqlElements = (state = initialState, action) => {
    const {type, payload} = action;

    switch (type) {
        case GET_SQL_MORS:
            return {
                ...state, machine_operations: payload, mor_loading: false,
            };
        case GET_SQL_MATERIALS:
            return {
                ...state, materials: payload, material_loading: false,
            };
        case GET_SQL_PRODUCTION_ORDER:
            return {
                ...state, production_orders: payload, po_loading: false,
            };
        case GET_SQL_LOCATIONS:
            return {
                ...state, locations: payload, location_loading: false,
            };
        case SQL_PRODUCTION_ORDER_ERROR:
            return {
                ...state, error: payload, po_loading: false,
            };
        case SQL_LOCATIONS_ERROR:
            return {
                ...state, error: payload, location_loading: false,
            };
        case SQL_MATERIALS_ERROR:
            return {
                ...state, error: payload, material_loading: false,
            };
        case SQL_MORS_ERROR:
            return {
                ...state, error: payload, mor_loading: false,

            }
        default:
            return state;
    }
};

export default sqlElements;
