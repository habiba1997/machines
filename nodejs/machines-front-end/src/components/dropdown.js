import React from 'react';
import {Link} from "react-router-dom";
import PropTypes from 'prop-types';

const Dropdown = ({dropdownMenuName, items}) => {
    return (
    <div className="dropdown">
        <h4> {dropdownMenuName} <i className="fa fa-caret-down"></i></h4>
        {items.length > 0 && <div className="dropdown-content">
            {items.map((item, index) => <Link key={index} to={item.path}>{item.name}</Link>)}
        </div>}
    </div>);
};

Dropdown.prototype = {
    items: PropTypes.array.isRequired, dropdownMenuName: PropTypes.string.isRequired,
};


export default Dropdown;