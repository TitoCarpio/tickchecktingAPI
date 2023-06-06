import React from "react";

const SubmitButton = ({ text }) => {
    return (
      <button
        type="submit"
        className='px-8 py-2 bg-p-red font-medium text-white rounded-full
        border-none  cursor-pointer hover:bg-primary-700 transform transition duration-300 
        ease-in-out hover:opacity-80 text-p-yellow' >
        {text}
      </button>
    );
  };
  
  export default SubmitButton;