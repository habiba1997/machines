import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {getAllCacheOperations} from "../../../redux/actions/cache-elements";
import Spinner from "../../Spinner";
import ObjectCard from "../object-card";
import operationImage from '../../images/operation.png';

const Operations = ({getAllCacheOperations, state: {operations, operation_loading}}) => {
    useEffect(() => {
        getAllCacheOperations();
    }, [getAllCacheOperations]);

    const createOperationObject = (redisObj) => {
        return {
            name: redisObj.name,
            status: redisObj.status,
            "Production Order": redisObj.productionOrder ? redisObj.productionOrder.name : null,
            material: redisObj.material.name ? redisObj.material.name : null
        };
    }

    return (<Fragment>
        {operation_loading ? (<Spinner/>) : (<Fragment>
            <h1 className='large text-primary'>Operations</h1>
            <p className='lead'>
                <i className='fas fa-globe'/> Browse Operation List
            </p>
            <div className="center">
                {' '}
                {operations.length > 0 ? (operations.map((operation, index) => (
                    <ObjectCard key={index} object={createOperationObject(operation)} image={operationImage}/>))) : (
                    <h4>No Operations found...</h4>)}{' '}
            </div>
            {' '}
        </Fragment>)}{' '}
    </Fragment>);
};

Operations.propTypes = {
    getAllCacheOperations: PropTypes.func.isRequired,
    state: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    state: state.cacheElements,
});

export default connect(mapStateToProps, {getAllCacheOperations})(Operations);
