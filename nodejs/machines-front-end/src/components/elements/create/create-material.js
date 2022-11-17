import React, {useState} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {saveMaterial} from '../../../redux/actions/save-element';
import {Link} from 'react-router-dom';
import {useNavigate} from 'react-router-dom';
import MesUnit from "../../enums/mes-unit";

const CreateMaterial = ({saveMaterial}) => {
    const initialStateMaterial = {
        name: '', measuredValue: '', baseUnit: ''
    };
    const initialStateMeasuredValue = {
        unit: '', value: ''
    };
    const [material, setMaterial] = useState(initialStateMaterial);

    const [measuredValue, setMeasuredValue] = useState(initialStateMeasuredValue);

    const {
        name, baseUnit
    } = material;

    const {
        unit, value
    } = measuredValue;

    const onChangeMaterial = (e) => {
        setMaterial({...material, [e.target.name]: e.target.value})
    };
    const onChangeMeasureValue = (e) => {
        setMeasuredValue({...measuredValue, [e.target.name]: e.target.value})
    };

    const navigate = useNavigate();
    const onSubmit = (e) => {
        e.preventDefault();
        material.measuredValue = measuredValue;
        saveMaterial(material, navigate);
    };

    return (<div>
        <h1 className='large text-primary'>Create Material</h1>
        <div className="card-form">
            <form className='form' onSubmit={(e) => onSubmit(e)}>
                <small className="block">* required field</small>
                <div className='form-group'>
                    <input
                        type='text'
                        placeholder='* Write material name'
                        name='name'
                        value={name}
                        onChange={(e) => onChangeMaterial(e)}
                    />
                </div>
                <div className='form-group'>
                    <table>
                        <tbody>
                        <tr>
                            <td>
                                <input

                                    type='number'
                                    placeholder='* Write quantity'
                                    name='value'
                                    onChange={(e) => onChangeMeasureValue(e)}
                                />
                            </td>
                            <td>
                                <select name='unit' value={unit}
                                        onChange={(e) => onChangeMeasureValue(e)}>
                                    <option value='0'>* Select unit</option>
                                    {Object.keys(MesUnit).map((unit, index) => <option key={index}
                                                                                       value={MesUnit[unit]}>{unit}</option>)}
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </div>
                <div className='form-group'>
                    <select className="inline" name='baseUnit' value={baseUnit} onChange={(e) => onChangeMaterial(e)}>
                        <option value='0'>* Select base unit</option>
                        {Object.keys(MesUnit).map((unit, index) => <option key={index}
                                                                           value={MesUnit[unit]}>{unit}</option>)}
                    </select>
                </div>
                <input
                    type='submit'
                    className='btn btn-primary my-1'
                    value='Create Material'
                />
                <Link className='btn btn-light no-underline my-1' to='/'>
                    Go Back
                </Link>
            </form>
        </div>

    </div>);
};

CreateMaterial.propTypes = {
    saveMaterial: PropTypes.func.isRequired,
};

export default connect(null, {saveMaterial})(CreateMaterial);
