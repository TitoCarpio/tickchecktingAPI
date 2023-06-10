import React, { useState } from 'react';
import { redirect } from 'react-router-dom';

const TicketPurchase = () => {
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
    window.location.replace("/home");

  };

  return (
    <div className="max-w-sm mx-auto p-4 bg-white rounded-lg shadow">
      <h1 className="text-2xl font-bold mb-4">Compra de Boletos</h1>
      <div className="mb-4">
        <label htmlFor="quantity" className="block font-medium mb-2">Cantidad de boletos:</label>
        <input
          type="number"
          id="quantity"
          className="w-full border border-gray-300 rounded px-3 py-2"
          min="0"
          value={ticketQuantity}
          onChange={handleTicketQuantityChange}
        />
      </div>
      <div className="mb-4">
        <p className="text-lg font-medium">Total a pagar: ${totalPrice}</p>
      </div>
      <button
        className="w-full bg-blue-500 text-white font-semibold py-2 px-4 rounded"
        onClick={handlePurchase}
        disabled={ticketQuantity === 0}
      >
        Comprar
      </button>
    </div>
  );
};

export default TicketPurchase;
