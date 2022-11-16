import {combineReducers} from 'redux';
import alert from './alert-reducer';
import cacheElements from "./cache-elements";
import saveElement from "./save-element";

export default combineReducers({
    alert, cacheElements, saveElement
});
