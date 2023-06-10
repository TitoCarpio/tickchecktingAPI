import React, { useState } from 'react';

const ImagePopup = () => {
  const [isOpen, setIsOpen] = useState(false);

  const openPopup = () => {
    setIsOpen(true);
  };

  const closePopup = () => {
    setIsOpen(false);
  };

  return (
    <div>
      <button onClick={openPopup}>Abrir imagen</button>

      {isOpen && (
        <div className="popup">
          <div className="popup-content">
            <img src={require('../../images/QR.png')} alt="Imagen" />
            <button onClick={closePopup}>Cerrar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default ImagePopup;
