import React from 'react';
import {Route, Routes, Navigate} from 'react-router-dom';
import Home from '../pages/home';
import NewEvent from '../pages/newEvent';
import Tickets from '../pages/tickets'


const WebRouter = ({setSesion, roles, setRol}) => {
    return (
        <Routes>
            <Route path="/home" element={<Home setSesion = {setSesion} roles ={roles} setRol={setRol} /> }/>
            <Route path="/newEvent" element={<NewEvent setSesion = {setSesion} /> }/>
            <Route path="/tickets" element={<Tickets setSesion = {setSesion} /> }/>
            <Route path="*" element={<Navigate to="/home"/>} /> 
        </Routes>
    )
}

export default WebRouter;