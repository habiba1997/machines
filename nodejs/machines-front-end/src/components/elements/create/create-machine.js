import React, {useState, useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {saveMachine} from '../../../redux/actions/save-element';
import {getAllLocations} from '../../../redux/actions/fetch-elements-from-database';
import {Link} from 'react-router-dom';
import {useNavigate} from 'react-router-dom';
import Spinner from "../../Spinner";

const CreateMachine = ({saveMachine, getAllLocations, state: {locations, location_loading}}) => {

    useEffect(() => {
        getAllLocations();
    }, [getAllLocations]);

    const initialState = {
        name: '', machineType: '', locationName: ''
    };
    const [machine, setMachine] = useState(initialState);

    const {
        name, machineType, locationName
    } = machine;

    const onChange = (e) => {
        setMachine({...machine, [e.target.name]: e.target.value})
    };

    const navigate = useNavigate();
    const onSubmit = (e) => {
        e.preventDefault();
        saveMachine(machine, navigate);
    };

    return (<div>
        <h1 className='large text-primary'>Create Machine</h1>
        <div className="card-form">
            <form className='form' onSubmit={(e) => onSubmit(e)}>
                <small className="block">* required field</small>
                <div className='form-group'>
                    <input
                        type='text'
                        placeholder='* Write machine name'
                        name='name'
                        value={name}
                        onChange={(e) => onChange(e)}
                    />
                </div>
                <div className='form-group'>
                    <select name='machineType' value={machineType} onChange={(e) => onChange(e)}>
                        <option value='NONE'>* Select Machine Type</option>
                        <option value='ASSEMBLY'>Assembly</option>
                        <option value='PRESS'>Press</option>
                    </select>
                </div>
                <div className='form-group'>
                    {location_loading ? <Spinner/> :
                        <select name='locationName' value={locationName} onChange={(e) => onChange(e)}>
                            <option value='0'>* Select Location</option>
                            {Object.keys(locations).map((index) =>
                                <option key={index} value={locations[index].name}>{locations[index].name}</option>)}
                        </select>}
                </div>
                <input
                    type='submit'
                    className='btn btn-primary my-1'
                    value='Create Machine'
                />
                <Link className='btn btn-light no-underline my-1' to='/'>
                    Go Back
                </Link>
            </form>
        </div>

    </div>);
};

CreateMachine.propTypes = {
    saveMachine: PropTypes.func.isRequired,
    getAllLocations: PropTypes.func.isRequired,
    state: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    state: state.sqlElements,
});
export default connect(mapStateToProps, {saveMachine, getAllLocations})(CreateMachine);
