import React from "react";
import NavBar from "../components/navBar";
import { useNavigate } from "react-router";
import Drop from "../components/dropDown";
import { cities } from "../utils/citiesList";
import {dates} from "../utils/datesList";
import CardEvent from "../components/events/card";
import { events } from "../utils/eventsList";

const HomePage = ({ setSesion , roles , setRol}) => {

  const cards = Array.from({ length: events.length }, (_, index) => (
    //
    <CardEvent key={index} image={events[index].image}/>
  ));

  //convierto los roles en un array
  const rolesArray = roles.split(",");
  console.log(roles);
  // reload();
  //let rol = "";

  const navigate = useNavigate();

  const event = () => navigate("/newEvent");

  //funcion que retorna el boton de nuevo evento si alguno de los roles es admin
   const newEvent = () => {
    // e.preventDefault();
    for (let index = 0; index < rolesArray.length ; index++) {
      if (rolesArray[index] === 'admin') {
        //rol = "admin";
        return <button className="flex  bg-p-red " onClick={event}>nuevo evento</button>;
      }else{
        //rol = 'user';
        return null;


      }
      
    }
  }

  

  return (
    
    <body>
      <div>
        <NavBar setSesion={setSesion} setRol={setRol}/>
      </div>
      
      <div class="flex items-center justify-center h-20  space-x-10 ">
        <Drop opciones={cities} />
        <Drop opciones={dates}/> 
      </div>

      <body>
        <div>
          <section class="max-w-6xl mx-auto px-4 sm:px-6 lg:px-4 py-12">
            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {/* mando a llamar a la funcion que me imprime n veces la carta de los evetos */}
              {cards}
              {/* {reload()} */}
            </div>
          </section>
        </div>
        <div className="flex  justify-center">
          {newEvent()}







          {/* {rol === "admin" && (
              <button className="flex  bg-p-red " onClick={event}>nuevo evento</button>
          )} */}
        

        </div>
      </body>
    </body>
  );
};

export default HomePage;
