import React, {useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import Spinner from "../../Spinner";
import {connect} from "react-redux";
import {changeOperationStatus} from "../../../redux/actions/kafka-actions";
import {getAllCacheOperations} from "../../../redux/actions/cache-elements";
import {Link, useNavigate} from "react-router-dom";
import OperationStatus from "../../enums/operation-status";

const ChangeOperationStatus = ({
                                   changeOperationStatus, getAllCacheOperations, cache: {operations, operation_loading}
                               }) => {
    useEffect(() => {
        getAllCacheOperations();
    }, [getAllCacheOperations]);

    const initialName = '';
    const initialStatus = '';

    const [operationName, setOperationName] = useState(initialName);
    const [operationStatus, setOperationStatus] = useState(initialStatus);


    const navigate = useNavigate();

    const onSubmit = (e) => {
        e.preventDefault();
        changeOperationStatus(operationName, operationStatus, navigate);
    };

    return (<div>
        <h1 className='large text-primary'>Change Operation Status</h1>
        <div className="card-form">
            <form className='form' onSubmit={(e) => onSubmit(e)}>
                <small className="block">* required field</small>
                <div className='form-group'>
                    {operation_loading ? <Spinner/> :
                        <select name='operationName' value={operationName} onChange={(e) => setOperationName(e.target.value)}>
                            <option value='0'>* Select Operation to change its status</option>
                            {Object.keys(operations).map(( index) => <option key={index}
                                                                                       value={operations[index].name}>{operations[index].name}</option>)}
                        </select>}
                </div>
                <div className='form-group'>
                    <select name='operationStatus' value={operationStatus} onChange={(e) => setOperationStatus(e.target.value)}>
                        <option value='0'>* Select operation new status</option>
                        {Object.keys(OperationStatus).map((status, index) => <option key={index}
                                                                                     value={OperationStatus[status]}>{status}</option>)}
                    </select>
                </div>
                <input
                    type='submit'
                    className='btn btn-primary my-1'
                    value='Change'
                />
                <Link className='btn btn-light no-underline my-1' to='/'>
                    Go Back
                </Link>
            </form>
        </div>

    </div>);
};

ChangeOperationStatus.propTypes = {
    changeOperationStatus: PropTypes.func.isRequired,
    getAllCacheOperations: PropTypes.func.isRequired,
    cache: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    cache: state.cacheElements,
});
export default connect(mapStateToProps, {
    changeOperationStatus, getAllCacheOperations
})(ChangeOperationStatus);
