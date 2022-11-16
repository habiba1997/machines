import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {getAllCacheMachineOperations} from "../../../redux/actions/cache-elements";
import Spinner from "../../Spinner";
import ObjectCard from "../object-card";
import machineOperationImage from '../../images/machine-operation.webp';

const MachineOperations = ({cache, getAllCacheMachineOperations, state: {machine_operations, mor_loading}}) => {
    useEffect(() => {
        getAllCacheMachineOperations();
    }, [getAllCacheMachineOperations]);

    const createMachineOperationObject = (redisObj) => {
        return {
            "Machine Name": redisObj.machine.name,
            "Operation Name": redisObj.operation.name,
        };
    }

    return (<Fragment>
        {mor_loading ? (<Spinner/>) : (<Fragment>
            <h1 className='large text-primary'>MachineOperations</h1>
            <p className='lead'>
                <i className='fas fa-globe'/> Browse Machine Operation List
            </p>
            <div className="center">
                {' '}
                {machine_operations.length > 0 ? (machine_operations.map((mor, index) => (
                    <ObjectCard key={index} object={createMachineOperationObject(mor)}
                                image={machineOperationImage}/>))) : (
                    <h4>No MachineOperations found...</h4>)}{' '}
            </div>
            {' '}
        </Fragment>)}{' '}
    </Fragment>);
};

MachineOperations.propTypes = {
    cache: PropTypes.bool.isRequired,
    getAllCacheMachineOperations: PropTypes.func.isRequired,
    state: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    state: state.cacheElements,
});

export default connect(mapStateToProps, {getAllCacheMachineOperations})(MachineOperations);
