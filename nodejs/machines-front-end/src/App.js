import './App.css';
import React, {Fragment} from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Dashboard from "./components/dashboard";
import Landing from "./components/landing";
import Alert from "./components/alert";

//Redux
import {Provider} from 'react-redux';
import store from './redux/store';
import NotFound from "./components/NotFound";
import Machines from "./components/elements/cache/machines";
import Locations from "./components/elements/cache/locations";
import Operations from "./components/elements/cache/operations";
import Materials from "./components/elements/cache/materials";
import MachineOperations from "./components/elements/cache/machine-operations";

function App() {
    return (
        <Provider store={store}>
            <BrowserRouter>
                <Fragment>
                    <Alert/>
                    <Dashboard/>
                    <section className='container'>
                        <Routes>
                            <Route path='/' element={<Landing/>}/>
                            <Route path='/cache/machines' element={<Machines cache={true}/>}/>
                            <Route path='/cache/locations' element={<Locations cache={true}/>}/>
                            <Route path='/cache/operations' element={<Operations cache={true}/>}/>
                            <Route path='/cache/materials' element={<Materials cache={true}/>}/>
                            <Route path='/cache/machine-operations' element={<MachineOperations cache={true}/>}/>
                            <Route path='/*' element={<NotFound/>}/>
                        </Routes>
                    </section>
                </Fragment>
            </BrowserRouter>
        </Provider>
    );
}

export default App;
