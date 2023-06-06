import React from 'react';
import {Route, Routes, Navigate} from 'react-router-dom';
import Tickets from '../pages/tickets'


const TicketsRouter = ({setSesion}) => {
    return (
        <Routes>
            <Route path="/tickets" element={<Tickets setSesion = {setSesion} /> }/>
            <Route path="*" element={<Navigate to="/tickets"/>} /> 
        </Routes>
    )
}

export default TicketsRouter;