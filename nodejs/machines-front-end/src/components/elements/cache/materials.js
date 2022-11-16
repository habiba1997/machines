import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {getAllCacheMaterials} from "../../../redux/actions/cache-elements";
import Spinner from "../../Spinner";
import ObjectCard from "../object-card";
import materialImage from '../../images/material.png';

const Materials = ({cache, getAllCacheMaterials, state: {materials, material_loading}}) => {
    useEffect(() => {
        getAllCacheMaterials();
    }, [getAllCacheMaterials]);

    const createMaterialObject = (redisObj) => {
        return {
            name: redisObj.name,
            "Measure Value": redisObj.measuredValue,
            "Base Unit": redisObj.baseUnit
        };
    };

    return (<Fragment>
        {material_loading ? (<Spinner/>) : (<Fragment>
            <h1 className='large text-primary'>Materials</h1>
            <p className='lead'>
                <i className='fas fa-globe'/> Browse Material List
            </p>
            <div className="center">
                {' '}
                {materials.length > 0 ? (materials.map((material, index) => (
                    <ObjectCard key={index} object={createMaterialObject(material)} image={materialImage}/>))) : (
                    <h4>No Materials found...</h4>)}{' '}
            </div>
            {' '}
        </Fragment>)}{' '}
    </Fragment>);
};

Materials.propTypes = {
    cache: PropTypes.bool.isRequired,
    getAllCacheMaterials: PropTypes.func.isRequired,
    state: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    state: state.cacheElements,
});

export default connect(mapStateToProps, {getAllCacheMaterials})(Materials);
