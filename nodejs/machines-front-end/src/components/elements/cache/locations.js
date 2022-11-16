import React, {Fragment, useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {getAllCacheLocations} from "../../../redux/actions/cache-elements";
import Spinner from "../../Spinner";
import ObjectCard from "../object-card";
import locationImage from '../../images/location.png';

//todo: remove cache
const Locations = ({cache, getAllCacheLocations, state: {locations, location_loading}}) => {
    useEffect(() => {
        getAllCacheLocations();
    }, [getAllCacheLocations]);


    return (<Fragment>
        {location_loading ? (<Spinner/>) : (<Fragment>
            <h1 className='large text-primary'>Locations</h1>
            <p className='lead'>
                <i className='fas fa-globe'/> Browse Location List
            </p>
            <div className="center">
                {' '}
                {locations.length > 0 ? (locations.map((location, index) => (
                    <ObjectCard key={index} object={location} image={locationImage}/>))) : (
                    <h4>No Locations found...</h4>)}{' '}
            </div>
            {' '}
        </Fragment>)}{' '}
    </Fragment>);
};

Locations.propTypes = {
    cache: PropTypes.bool.isRequired,
    getAllCacheLocations: PropTypes.func.isRequired,
    state: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    state: state.cacheElements,
});

export default connect(mapStateToProps, {getAllCacheLocations})(Locations);
