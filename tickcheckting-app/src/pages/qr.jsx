import React, { useState } from 'react';
import NavBar from "../components/navBar";


const QR = ({setSesion}) => {
  const [ticketQuantity, setTicketQuantity] = useState(0);
  const [totalPrice, setTotalPrice] = useState(0);

  const handleTicketQuantityChange = (e) => {
    const quantity = parseInt(e.target.value, 10);
    setTicketQuantity(quantity);
    setTotalPrice(quantity * 50); // Precio por boleto
  };

  const handleQR = () => {
    
    window.location.replace("/tickets");

  };

  return (
    <body >
      <div>
        <NavBar setSesion= {setSesion}/>
      </div>
      <body>
    <div className="m-10">
     
    <div className="flex-col items-center m-15 max-w-sm mx-auto p-4 bg-white rounded-lg shadow border border-p-red">
      <center>
    <img className="w-48 "src={require('../images/QR.png')}  alt="" />
    </center>
      <h1 className="text-2xl font-bold mb-4"></h1>
      <div className="mb-4">
      
      <button
        className="w-full bg-blue-500 py-2 px-4 rounded bg-p-red text-p-yellow rounded-3xl p-3 font-black"
        onClick={handleQR}
      >
        Return
      </button> 
    </div>
    </div>
    </div>
    </body>
    </body>
  );
};

export default QR;
