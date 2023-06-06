import React from 'react';
import {Route, Routes, Navigate} from 'react-router-dom';
import Home from '../pages/home';
import NewEvent from '../pages/newEvent';


const WebRouter = ({setSesion}) => {
    return (
        <Routes>
            <Route path="/home" element={<Home setSesion = {setSesion} /> }/>
            <Route path="/newEvent" element={<NewEvent setSesion = {setSesion} /> }/>
            <Route path="*" element={<Navigate to="/home"/>} /> 
        </Routes>
    )
}

export default WebRouter;