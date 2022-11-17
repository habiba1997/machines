import {
    GET_SQL_PRODUCTION_ORDER,
    GET_SQL_MATERIALS,
    GET_SQL_LOCATIONS
} from '../actions/types';

const initialState = {
    locations: [],
    materials: [],
    production_orders: [],
    material_loading: true,
    location_loading: true,
    po_loading: true,
    error: {},
};

const sqlElements = (state = initialState, action) => {
    const {type, payload} = action;

    switch (type) {
        case GET_SQL_MATERIALS:
            return {
                ...state,
                materials: payload,
                material_loading: false,
            };
        case GET_SQL_PRODUCTION_ORDER:
            return {
                ...state,
                production_orders: payload,
                po_loading: false,
            };
        case GET_SQL_LOCATIONS:
            return {
                ...state,
                locations: payload,
                location_loading: false,
            };
        default:
            return state;
    }
};

export default sqlElements;
