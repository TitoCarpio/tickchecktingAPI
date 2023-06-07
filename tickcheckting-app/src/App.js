import React, { useState} from "react";
import { BrowserRouter } from "react-router-dom";

import AuthRouter from "./rutas/AuthRouter";
import WebRouter from "./rutas/WebRouter";

import '../src/index.css';




function App() {
    const [sesion, setSesion] = useState(localStorage.getItem("sesion") || false);
    const [rol, setRol] = useState(localStorage.getItem("roles") || "");

    //obtengo los roles del usuario guardar en el localstorage
    //setRoles(localStorage.getItem("roles"));

    if (sesion === true) {
        console.log("sesion true");
        //setRol(localStorage.getItem("roles"));
        //console.log("roles: " + roles);
    } else {
        console.log("sesion false");
        //setRol("");
    }

    return (
        
        <BrowserRouter>
            {/* condicional de pantallas a cargar segun el email o password */}
            {sesion === "true" && <WebRouter setSesion ={setSesion} roles={rol} setRol={setRol} />}
            {!sesion  && <AuthRouter setSesion ={setSesion} />}
            
        </BrowserRouter>
    );

}

export default App;