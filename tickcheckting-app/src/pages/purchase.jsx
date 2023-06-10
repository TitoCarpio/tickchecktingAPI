import React, { useState } from 'react';
import NavBar from "../components/navBar";


const TicketPurchase = ({setSesion}) => {
  const [ticketQuantity, setTicketQuantity] = useState(0);
  const [totalPrice, setTotalPrice] = useState(0);

  const handleTicketQuantityChange = (e) => {
    const quantity = parseInt(e.target.value, 10);
    setTicketQuantity(quantity);
    setTotalPrice(quantity * 50); // Precio por boleto
  };

  const handlePurchase = () => {
    // Lógica para realizar la compra
    console.log(`Se compraron ${ticketQuantity} boletos por un total de ${totalPrice}`);
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
    <img className="w-48 "src="https://cosasquedanplacer.com/wp-content/uploads/2022/11/the-eras-tour-el-recorrido-de-la-ico%CC%81nica-carrera-de-taylor-swift-en-un-solo-concierto.jpg" alt="" />
      <h1 className="text-2xl font-bold mb-4"></h1>
      <div className="mb-4">
      <label htmlFor="quantity" className="block font-medium mb-2">Ticket purchase:</label>
      <select id="country" name="country" className="w-full border border-gray-300 rounded px-3 py-2 border-p-orange">
        <option value="VIP">VIP</option>
        <option value="General">General</option>
      </select>
        <label htmlFor="quantity" className="block font-medium mb-2">Amount:</label>
        <input
          type="number"
          id="quantity"
          className="w-full border border-gray-300 rounded px-3 py-2 border-p-orange"
          min="0"
          max="5"
          value={ticketQuantity}
          onChange={handleTicketQuantityChange}
        />
      </div>
      <div className="mb-4">
        <p className="text-lg font-medium">Total to pay: ${totalPrice}</p>
      </div>
      <button
        className="w-full bg-blue-500 py-2 px-4 rounded bg-p-red text-p-yellow rounded-3xl p-3 font-black"
        onClick={handlePurchase}
        disabled={ticketQuantity === 0}
      >
        Buy
      </button> 
    </div>
    </div>
    </body>
    </body>
  );
};

export default TicketPurchase;
