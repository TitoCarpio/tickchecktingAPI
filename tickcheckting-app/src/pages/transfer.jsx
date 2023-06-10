import React, { useState } from 'react';
import NavBar from "../components/navBar";


const Transfer = ({setSesion}) => {
  const [ticketQuantity, setTicketQuantity] = useState(0);
  const [totalPrice, setTotalPrice] = useState(0);

  const handlePurchase = () => {
    // Lógica para realizar la compra
    console.log(`Se mando un email al correo`);
    // Redireccionar al usuario a otra página
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
    <h1 className="text-2xl font-bold mb-4 text-p-brown">Transfer</h1>
    <form >
      <div className="mb-4">
      
      <label htmlFor="email" className="block font-medium mb-2">Email:</label>
        <input
          type="email"
          id="email"
          className="w-full border border-gray-300 rounded px-3 py-2 border-p-orange"
          hint='carlos@gmail.com'
          required
        />
      </div>
      <input onClick={handlePurchase} className="w-full bg-blue-500 py-2 px-4 rounded bg-p-red text-p-yellow rounded-3xl p-3 font-black" type="submit" value="Send Request" />
      </form>
    </div>
    </div>
    </body>
    </body>
  );
};

export default Transfer;
