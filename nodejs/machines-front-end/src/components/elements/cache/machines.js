import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {getAllCacheMachines} from "../../../redux/actions/cache-elements";
import Spinner from "../../Spinner";
import ObjectCard from "../object-card";
import machineImage from '../../images/machine.png';

//todo: remove cache
const Machines = ({cache, getAllCacheMachines, state: {machines, machine_loading}}) => {
    useEffect(() => {
        getAllCacheMachines();
    }, [getAllCacheMachines]);

    const createMachineObject = (machineRedisObject) => {
        return {
            name: machineRedisObject.name,
            machineType: machineRedisObject.machineType,
            location: machineRedisObject.location ? machineRedisObject.location.name : null
        };
    }

    return (<Fragment>
        {machine_loading ? (<Spinner/>) : (<Fragment>
            <h1 className='large text-primary'>Machines</h1>
            <p className='lead'>
                <i className='fas fa-globe'/> Browse Machine List
            </p>
            <div className="center">
                {' '}
                {machines.length > 0 ? (machines.map((machine, index) => (
                    <ObjectCard key={index} object={createMachineObject(machine)} image={machineImage}/>))) : (
                    <h4>No Machines found...</h4>)}{' '}
            </div>
            {' '}
        </Fragment>)}{' '}
    </Fragment>);
};

Machines.propTypes = {
    cache: PropTypes.bool.isRequired,
    getAllCacheMachines: PropTypes.func.isRequired,
    state: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    state: state.cacheElements,
});

export default connect(mapStateToProps, {getAllCacheMachines})(Machines);
