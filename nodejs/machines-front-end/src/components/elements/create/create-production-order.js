import React, {useState, useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {saveProductionOrder} from '../../../redux/actions/save-element';
import {getAllMaterials} from '../../../redux/actions/fetch-elements-from-database';
import {Link} from 'react-router-dom';
import {useNavigate} from 'react-router-dom';
import Spinner from "../../Spinner";
import MesUnit from "../../enums/mes-unit";

const CreateProductionOrder = ({saveProductionOrder, getAllMaterials, state: {materials, material_loading}}) => {

    useEffect(() => {
        getAllMaterials();
    }, [getAllMaterials]);

    const initialState = {
        name: '', materialName: '', plannedQuantity: ''
    };
    const initialStatePlannedQuantity = {
        unit: '', value: ''
    };
    const [productionOrder, setProductionOrder] = useState(initialState);

    const [plannedQuantity, setPlannedQuantity] = useState(initialStatePlannedQuantity);

    const {
        name, materialName
    } = productionOrder;

    const {
        unit, value
    } = plannedQuantity;

    const onChange = (e) => {
        setProductionOrder({...productionOrder, [e.target.name]: e.target.value})
    };
    const onChangePlannedQuantity = (e) => {
        setPlannedQuantity({...plannedQuantity, [e.target.name]: e.target.value})
    };

    const navigate = useNavigate();
    const onSubmit = (e) => {
        e.preventDefault();
        productionOrder.plannedQuantity = plannedQuantity;
        saveProductionOrder(productionOrder, navigate);
    };

    return (<div>
        <h1 className='large text-primary'>Create Production Order</h1>
        <div className="card-form">
            <form className='form' onSubmit={(e) => onSubmit(e)}>
                <small className="block">* required field</small>
                <div className='form-group'>
                    <input
                        type='text'
                        placeholder='* Write production order name'
                        name='name'
                        value={name}
                        onChange={(e) => onChange(e)}
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
                                    onChange={(e) => onChangePlannedQuantity(e)}
                                />
                            </td>
                            <td>
                                <select name='unit' value={unit}
                                        onChange={(e) => onChangePlannedQuantity(e)}>
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
                    {material_loading ? <Spinner/> :
                        <select name='materialName' value={materialName} onChange={(e) => onChange(e)}>
                            <option value='0'>* Select Material</option>
                            {Object.keys(materials).map((index) => <option key={index}
                                                                           value={materials[index].name}>{materials[index].name}</option>)}
                        </select>}
                </div>
                <input
                    type='submit'
                    className='btn btn-primary my-1'
                    value='Create Production Order'
                />
                <Link className='btn btn-light no-underline my-1' to='/'>
                    Go Back
                </Link>
            </form>
        </div>

    </div>);
};

CreateProductionOrder.propTypes = {
    saveProductionOrder: PropTypes.func.isRequired,
    getAllMaterials: PropTypes.func.isRequired,
    state: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    state: state.sqlElements,
});
export default connect(mapStateToProps, {saveProductionOrder, getAllMaterials})(CreateProductionOrder);
