import React from 'react';
import {Route, Routes, Navigate} from 'react-router-dom';
//import SesionPantalla from '../paginas/SesionPantalla';
import Home from '../pages/home'


const WebRouter = ({setSesion}) => {
    return (
        <Routes>
            <Route path="/home" element={<Home setSesion = {setSesion} /> }/>
            <Route path="*" element={<Navigate to="/home"/>} /> 
        </Routes>
    )
}

export default WebRouter;