import React from "react";
import NavBar from "../components/navBar";
import { useNavigate } from "react-router";
import CardEvent from "../components/events/card";
import { events } from "../utils/eventsList";
import FilterCard from "../components/cards/filter";

const HomePage = ({ setSesion , roles , setRol}) => {

  const cards = Array.from({ length: events.length }, (_, index) => (
    //
    <CardEvent 
        key={index} 
        date={events[index].date}
        title={events[index].title}
        time={events[index].time}
        place={events[index].place}
        image={events[index].image}
        />
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
        return <button className=" px-8 py-2 bg-p-red font-medium text-white rounded-full 
          border-none  cursor-pointer hover:bg-primary-700 transform transition duration-300 
          ease-in-out hover:opacity-80 text-p-yellow" onClick={event}>New event</button>;
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
      
      <div>
        <FilterCard/>
      </div>

      <body>
        <div>
          <section className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-4 py-6">
            <div className="flex  justify-center mb-4">
              {newEvent()}

              {/* {rol === "admin" && (
                  <button className="flex  bg-p-red " onClick={event}>nuevo evento</button>
              )} */}

            </div>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4  gap-2">
              {/* mando a llamar a la funcion que me imprime n veces la carta de los evetos */}
              {cards}
              {/* {reload()} */}
              
            </div>
          </section>
        </div>

      </body>
    </body>
  );
};

export default HomePage;
