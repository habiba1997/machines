import React, {useState} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {saveLocation} from '../../../redux/actions/save-element';
import {Link} from 'react-router-dom';
import {useNavigate} from 'react-router-dom';

const CreateLocation = ({saveLocation}) => {
    const initialState = {
        name: '', temp: false, type: ''
    };
    const [location, setLocation] = useState(initialState);

    const {
        name, type,
    } = location;

    const onChange = (e) => {
        setLocation({...location, [e.target.name]: e.target.value})
    };

    const onCheckboxChange = (e) => {
        setLocation({...location, [e.target.name]: e.target.checked})
    };

    const navigate = useNavigate();
    const onSubmit = (e) => {
        e.preventDefault();
        saveLocation(location, navigate);
    };

    return (<div>
        <h1 className='large text-primary'>Create Location</h1>
        <div className="card-form">
            <form className='form' onSubmit={(e) => onSubmit(e)}>
                <small className="block">* required field</small>
                <div className='form-group'>
                    <input
                        type='text'
                        placeholder='* Write location name'
                        name='name'
                        value={name}
                        onChange={(e) => onChange(e)}
                    />
                </div>
                <div className='form-group'>

                    <h2 className="inline no-bold">
                        Is the location temporary: {' '}
                    </h2>
                    <input
                        className="checkbox"
                        type="checkbox"
                        placeholder='temporary'
                        name='temp'
                        onChange={(e) => onCheckboxChange(e)}
                    />


                </div>
                <div className='form-group'>
                    <select name='type' value={type} onChange={(e) => onChange(e)}>
                        <option value='0'>* Select Location Type</option>
                        <option value='in_door'>In Door</option>
                        <option value='inventory'>Inventory</option>
                        <option value='on_door'>Outside Door</option>
                        <option value='floor'>Floor Level</option>
                    </select>
                </div>
                <input
                    type='submit'
                    className='btn btn-primary my-1'
                    value='Create Location'
                />
                <Link className='btn btn-light no-underline my-1' to='/'>
                    Go Back
                </Link>
            </form>
        </div>
    </div>);
};

CreateLocation.propTypes = {
    saveLocation: PropTypes.func.isRequired,
};

export default connect(null, {saveLocation})(CreateLocation);
