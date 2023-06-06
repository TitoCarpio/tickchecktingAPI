import React, { useState} from "react";
import { BrowserRouter } from "react-router-dom";

import AuthRouter from "./rutas/AuthRouter";
import WebRouter from "./rutas/WebRouter";
import TicketsRouter from "./rutas/TicketsRouter";
import '../src/index.css';




function App() {
    const [sesion, setSesion] = useState(localStorage.getItem("sesion") || false);
    if (sesion == true) {
        console.log("sesion true");
    } else {
        console.log("sesion false");
    }

    return (
        
        <BrowserRouter>
            {/* condicional de pantallas a cargar segun el email o password */}
            {sesion === "true" && <WebRouter setSesion ={setSesion}  />}
            {!sesion  && <AuthRouter setSesion ={setSesion} />}
            {sesion === "true" && <TicketsRouter setSesion ={setSesion} />}
        </BrowserRouter>
    );

}

export default App;