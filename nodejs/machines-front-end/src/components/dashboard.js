import React from 'react';
import {Link} from "react-router-dom";
import Dropdown from "./dropdown";
import CacheEnums from "./enums/cache-objects";
import SqlEnums from "./enums/sql-objects";
import Item from "./models/item";
import actions from "./actions";

const Dashboard = () => {
    const getCacheEnums = () => {
        return Object.values(CacheEnums).map(value => new Item({
            name: value.toString(), path: "/cache/" + value.toString()
        }));
    };
    const getSqlEnums = () => {
        return Object.values(SqlEnums).map(value => new Item({
            name: value.toString(), path: "/create/" + value.toString()
        }));
    };

    return (<nav className='navbar bg-dark'>
        <div className="float-left">
            <h4>
                <Link to='/' className="font-white padding-left no-underline"><i
                    className='fas fa-truck'></i> <span>{' '} </span>Machines</Link>
            </h4>
        </div>
        <div className="float-left">
            <Dropdown className="menu-item" dropdownMenuName="Cache Mater Data" items={getCacheEnums()}/>
        </div>
        <div className="float-left">
            <Dropdown className="menu-item" dropdownMenuName="Create" items={getSqlEnums()}/>
        </div>
        <div className="float-left">
            <Dropdown className="menu-item" dropdownMenuName="Change" items={actions}/>
        </div>
    </nav>);
};

export default Dashboard;