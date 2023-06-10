import React from 'react';
import {Route, Routes, Navigate} from 'react-router-dom';
import Home from '../pages/home';
import NewEvent from '../pages/newEvent';
import Tickets from '../pages/tickets'
import Purchase from '../pages/purchase'
import QR from '../pages/qr'
import Transfer from '../pages/transfer'

const WebRouter = ({setSesion, roles, setRol}) => {
    return (
        <Routes>
            <Route path="/home" element={<Home setSesion = {setSesion} roles ={roles} setRol={setRol} /> }/>
            <Route path="/newEvent" element={<NewEvent setSesion = {setSesion} /> }/>
            <Route path="/tickets" element={<Tickets setSesion = {setSesion} /> }/>
            <Route path="/purchase" element={<Purchase setSesion = {setSesion} /> }/>
            <Route path="/qr" element={<QR setSesion = {setSesion} /> }/>
            <Route path="/transfer" element={<Transfer setSesion = {setSesion} /> }/>
            <Route path="*" element={<Navigate to="/home"/>} /> 
        </Routes>
    )
}

export default WebRouter;