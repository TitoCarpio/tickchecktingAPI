import React from "react";
import { MapPinIcon } from "@heroicons/react/24/outline";
import imgHolder from "../../images/images.jpg"
const CardEvent = ({}) => {

  //Variables que sera necesario pasar por prop:
  var day = "12"
  var month = "July"
  var eventName = "Mago de Oz: Quinto aniversario"
  //picture
  var timeDate = "20:00"
  var place = "Estadio Cuscatlan"
  const routeHandler = (e) => {

  };
  
  return (
    <div className="w-full bg-gray-900 rounded-lg  p-5 flex flex-col justify-center items-center mb-8 relative">
           
              <img
                className="object-center object-cover h-64 w-44 rounded-3xl shadow-2xl"
                src={imgHolder}
                alt="photo"
              />
              <div className="absolute h-12 w-12 right-28 bottom-9 lg:right-16 md:right-20   rounded-full bg-p-red text-p-white text-center text-xs font-bold uppercase justify-center flex flex-col">
                <span>{month}</span>
                <span>{day}</span>
              </div>
              {/* En la referencia debe ir el evento al que se va a hacer referencia una vez mas pasado por prop */}
              <a className="absolute h-64 w-44 bg-p-brown bg-opacity-60 text-p-white rounded-3xl items-center flex flex-col text-center gap-3 justify-center uppercase px-5 font-black opacity-0 hover:opacity-100" onClick={routeHandler} >
                  <span>{eventName}</span>
                  <span className="capitalize">{day} {month}</span>
                  <span className="normal-case">{timeDate} hrs.</span>
                  <div className="flex flex-row items-center">
                    <MapPinIcon className="w-6 h-6"/>
                    <span>{place}</span>
                  </div>
              </a>
            </div>
    );
        
    
  
};

export default CardEvent;