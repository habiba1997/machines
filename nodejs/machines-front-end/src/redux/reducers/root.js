import {combineReducers} from 'redux';
import alert from './alert-reducer';
import cacheElements from "./cache-elements";
import saveElement from "./save-element";
import sqlElements from "./fetch-elements-from-database";

export default combineReducers({
    alert, cacheElements, saveElement, sqlElements
});
