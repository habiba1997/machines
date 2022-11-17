import React, {useState, useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {saveOperation} from '../../../redux/actions/save-element';
import {getAllProductionOrders} from '../../../redux/actions/fetch-elements-from-database';
import {getAllMaterials} from '../../../redux/actions/fetch-elements-from-database';
import {Link} from 'react-router-dom';
import {useNavigate} from 'react-router-dom';
import Spinner from "../../Spinner";
import OperationStatus from "../../enums/operation-status";
import MesUnit from "../../enums/mes-unit";

const CreateOperation = ({
                             saveOperation,
                             getAllMaterials,
                             getAllProductionOrders,
                             state: {materials, production_orders, po_loading, material_loading}
                         }) => {

    useEffect(() => {
        getAllMaterials();
        getAllProductionOrders();
    }, [getAllMaterials, getAllProductionOrders]);

    const initialState = {
        name: '', materialName: '', productionOrderName: '', status: ''
    };
    const [operation, setOperation] = useState(initialState);
    const {
        name, materialName, productionOrderName, status
    } = operation;

    const onChange = (e) => {
        setOperation({...operation, [e.target.name]: e.target.value})
    };

    const navigate = useNavigate();
    const onSubmit = (e) => {
        e.preventDefault();
        saveOperation(operation, navigate);
    };

    return (<div>
        <h1 className='large text-primary'>Create Operation</h1>
        <div className="card-form">
            <form className='form' onSubmit={(e) => onSubmit(e)}>
                <small className="block">* required field</small>
                <div className='form-group'>
                    <input
                        type='text'
                        placeholder='* Write operation name'
                        name='name'
                        value={name}
                        onChange={(e) => onChange(e)}
                    />
                </div>
                <div className='form-group'>
                    <select className="inline" name='status' value={status} onChange={(e) => onChange(e)}>
                        <option value='0'>* Select operation status</option>
                        {Object.keys(OperationStatus).map((status, index) => <option key={index}
                                                                                     value={OperationStatus[status]}>{status}</option>)}
                    </select>
                </div>
                <div className='form-group'>
                    {material_loading ? <Spinner/> :
                        <select name='materialName' value={materialName} onChange={(e) => onChange(e)}>
                            <option value='0'>* Select Material</option>
                            {Object.keys(materials).map((index) => <option key={index}
                                                                           value={materials[index].name}>{materials[index].name}</option>)}
                        </select>}
                </div>
                <div className='form-group'>
                    {po_loading ? <Spinner/> :
                        <select name='productionOrderName' value={productionOrderName} onChange={(e) => onChange(e)}>
                            <option value='0'>* Select Production Order</option>
                            {Object.keys(production_orders).map((index) => <option key={index}
                                                                           value={production_orders[index].name}>{production_orders[index].name}</option>)}
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

CreateOperation.propTypes = {
    saveOperation: PropTypes.func.isRequired,
    getAllMaterials: PropTypes.func.isRequired,
    getAllProductionOrders: PropTypes.func.isRequired,
    state: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    state: state.sqlElements,
});
export default connect(mapStateToProps, {saveOperation, getAllMaterials, getAllProductionOrders})(CreateOperation);
