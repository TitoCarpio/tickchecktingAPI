import React from 'react';
import {Route, Routes, Navigate} from 'react-router-dom';
import SesionPantalla from '../pages/login';


const AuthRouter = ({setSesion}) => {
    return (
        <Routes>
            <Route path="/sesion" element={<SesionPantalla setSesion = {setSesion}/> }/>
            <Route path="*" element={<Navigate to="/sesion"/>} /> 
        </Routes>
    )
}

export default AuthRouter;