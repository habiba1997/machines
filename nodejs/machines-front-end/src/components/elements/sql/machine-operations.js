import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {getAllMachineOperations} from "../../../redux/actions/fetch-elements-from-database";
import Spinner from "../../Spinner";
import ObjectCard from "../object-card";
import machineOperationImage from '../../images/machine-operation.webp';

const MachineOperationsFromSQL = ({getAllMachineOperations, state: {machine_operations, mor_loading}}) => {
    useEffect(() => {
        getAllMachineOperations();
    }, [getAllMachineOperations]);

    const createMachineOperationObject = (redisObj) => {
        return {
            "Machine Name": redisObj.machine.name, "Operation Name": redisObj.operation.name,
        };
    }

    return (<Fragment>
        {mor_loading ? (<Spinner/>) : (<Fragment>
            <h1 className='large text-primary'>Machine Operations form SQL</h1>
            <p className='lead'>
                <i className='fas fa-globe'/> Browse Machine Operation List
            </p>
            <div className="center">
                {' '}
                {machine_operations.length > 0 ? (machine_operations.map((mor, index) => (
                    <ObjectCard key={index} object={createMachineOperationObject(mor)}
                                image={machineOperationImage}/>))) : (<h4>No MachineOperations found...</h4>)}{' '}
            </div>
            {' '}
        </Fragment>)}{' '}
    </Fragment>);
};

MachineOperationsFromSQL.propTypes = {
    getAllMachineOperations: PropTypes.func.isRequired, state: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    state: state.sqlElements,
});

export default connect(mapStateToProps, {getAllMachineOperations})(MachineOperationsFromSQL);
