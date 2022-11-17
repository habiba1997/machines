import './App.css';
import React, {Fragment} from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Dashboard from "./components/dashboard";
import Landing from "./components/landing";
import Alert from "./components/alert";
import {Provider} from 'react-redux';
import store from './redux/store';
import NotFound from "./components/NotFound";
import Machines from "./components/elements/cache/machines";
import Locations from "./components/elements/cache/locations";
import Operations from "./components/elements/cache/operations";
import Materials from "./components/elements/cache/materials";
import MachineOperations from "./components/elements/cache/machine-operations";
import CreateLocation from "./components/elements/create/create-location";
import CreateMaterial from "./components/elements/create/create-material";
import CreateMachine from "./components/elements/create/create-machine";
import CreateProductionOrder from "./components/elements/create/create-production-order";
import CreateOperation from "./components/elements/create/create-operation";
import ChangeOperationStatus from "./components/elements/sql/change-operation-status";
import LinkOrUnlinkMachineOperation from "./components/elements/sql/link-unlink-machine-operation";
import MachineOperationsFromSQL from "./components/elements/sql/machine-operations";

function App() {
    return (<Provider store={store}>
        <BrowserRouter>
            <Fragment>
                <Alert/>
                <Dashboard/>
                <section className='container'>
                    <Routes>
                        <Route path='/' element={<Landing/>}/>
                        <Route path='/cache/machines' element={<Machines/>}/>
                        <Route path='/cache/locations' element={<Locations/>}/>
                        <Route path='/cache/operations' element={<Operations/>}/>
                        <Route path='/cache/materials' element={<Materials/>}/>
                        <Route path='/cache/machine-operations' element={<MachineOperations/>}/>

                        <Route path='/create/location' element={<CreateLocation/>}/>
                        <Route path='/create/machine' element={<CreateMachine/>}/>
                        <Route path='/create/operation' element={<CreateOperation/>}/>
                        <Route path='/create/material' element={<CreateMaterial/>}/>
                        <Route path='/create/production-order' element={<CreateProductionOrder/>}/>

                        <Route path='/change-operation-status' element={<ChangeOperationStatus/>}/>
                        <Route path='/machine-operations' element={<MachineOperationsFromSQL/>}/>
                        <Route path='/link-machine-operation'
                               element={<LinkOrUnlinkMachineOperation link={true}/>}/>
                        <Route path='/unlink-machine-operation'
                               element={<LinkOrUnlinkMachineOperation link={false}/>}/>

                        <Route path='/*' element={<NotFound/>}/>
                    </Routes>
                </section>
            </Fragment>
        </BrowserRouter>
    </Provider>);
}

export default App;
