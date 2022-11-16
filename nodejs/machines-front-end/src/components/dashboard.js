import React, {Fragment} from 'react';
import {Link} from "react-router-dom";
import Dropdown from "./dropdown";
import CacheEnums from "./enums/cache-objects";
import SqlEnums from "./enums/sql-objects";
import Item from "./models/item";

const Dashboard = () => {
    const getCacheEnums = () => {
        return Object.values(CacheEnums).map(value => new Item({
            name: value.toString(), path: "/" + value.toString()
        }));
    };
    const getSqlEnums = () => {
        return Object.values(SqlEnums).map(value => new Item({
            name: value.toString(), path: "/" + value.toString()
        }));
    };
    return (<nav className='navbar bg-dark'>
        <div className="float-left">
            <h4>
                <Link to='/' className="font-white padding-left no-underline"><i
                    className='fas fa-user'></i> Machines</Link>
            </h4>
        </div>
        <div className="float-left">
            <Dropdown className="menu-item" dropdownMenuName="Cache Mater Data (nodejs)" items={getCacheEnums()}/>
        </div>
        <div className="float-left">
            <Dropdown className="menu-item" dropdownMenuName="Sql Mater Data (java)" items={getSqlEnums()}/>
        </div>
    </nav>);
};

export default Dashboard;