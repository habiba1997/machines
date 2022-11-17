import React, {useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import Spinner from "../../Spinner";
import {connect} from "react-redux";
import {linkMachineOperation} from "../../../redux/actions/kafka-actions";
import {unlinkMachineOperation} from "../../../redux/actions/kafka-actions";
import {getAllCacheOperations} from "../../../redux/actions/cache-elements";
import {getAllCacheMachines} from "../../../redux/actions/cache-elements";
import {Link, useNavigate} from "react-router-dom";

const LinkOrUnlinkMachineOperation = ({
                                          link,
                                          linkMachineOperation,
                                          unlinkMachineOperation,
                                          getAllCacheOperations,
                                          getAllCacheMachines,
                                          cache: {operations, operation_loading, machines, machine_loading}
                                      }) => {
    useEffect(() => {
        if (machines.length === 0 && machine_loading) {
            getAllCacheMachines();
        }
        if (operations.length === 0 && operation_loading) {
            getAllCacheOperations();
        }
    }, [getAllCacheOperations, getAllCacheMachines, machine_loading, operation_loading, machines, operations]);

    const initialMachine = '';
    const initialOperation = '';

    const [operationName, setOperationName] = useState(initialOperation);
    const [machineName, setMachineName] = useState(initialMachine);


    const navigate = useNavigate();

    const onSubmit = (e) => {
        e.preventDefault();
        if (link) {
            linkMachineOperation(machineName, operationName, navigate);
        } else {
            unlinkMachineOperation(machineName, operationName, navigate);
        }
    };

    return (<div>
        <h1 className='large text-primary'>{link ? 'Link ' : 'Unlink '} Machine Operation</h1>
        <div className="card-form">
            <form className='form' onSubmit={(e) => onSubmit(e)}>
                <small className="block">* required field</small>
                <div className='form-group'>
                    {machine_loading ? <Spinner/> : <select name='machineName' value={machineName}
                                                            onChange={(e) => setMachineName(e.target.value)}>
                        <option value='0'>* Select machine</option>
                        {Object.keys(machines).map((index) => <option key={index}
                                                                      value={machines[index].name}>{machines[index].name}</option>)}
                    </select>}
                </div>
                <div className='form-group'>
                    {operation_loading ? <Spinner/> : <select name='operationName' value={operationName}
                                                              onChange={(e) => setOperationName(e.target.value)}>
                        <option value='0'>* Select Operation</option>
                        {Object.keys(operations).map((index) => <option key={index}
                                                                        value={operations[index].name}>{operations[index].name}</option>)}
                    </select>}
                </div>
                <input
                    type='submit'
                    className='btn btn-primary my-1'
                    value={link ? 'Link ' : 'Unlink '}
                />
                <Link className='btn btn-light no-underline my-1' to='/'>
                    Go Back
                </Link>
            </form>
        </div>

    </div>);
};

LinkOrUnlinkMachineOperation.propTypes = {
    linkMachineOperation: PropTypes.func.isRequired,
    unlinkMachineOperation: PropTypes.func.isRequired,
    getAllCacheOperations: PropTypes.func.isRequired,
    getAllCacheMachines: PropTypes.func.isRequired,
    cache: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    cache: state.cacheElements,
});
export default connect(mapStateToProps, {
    linkMachineOperation, getAllCacheOperations, getAllCacheMachines, unlinkMachineOperation
})(LinkOrUnlinkMachineOperation);
