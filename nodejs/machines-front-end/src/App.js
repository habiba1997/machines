import './App.css';
import React, {Fragment} from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Dashboard from "./components/dashboard";
import Landing from "./components/landing";

//Redux
import {Provider} from 'react-redux';
import store from './redux/store';

function App() {
    return (
        <Provider store={store}>
            <BrowserRouter>
                <Fragment>
                    <Dashboard/>
                    <section className='container'>
                        <Routes>
                            <Route path='/' element={<Landing/>}/>
                        </Routes>
                    </section>
                </Fragment>
            </BrowserRouter>
        </Provider>
    );
}

export default App;
