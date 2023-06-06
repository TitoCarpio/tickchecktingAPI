import React from "react";

const CardEvent = ({key, image}) => {

    console.log(key);
  return (
    
    <div class="w-full bg-gray-900 rounded-lg sahdow-lg p-12 flex flex-col justify-center items-center">
            <div class="mb-8">
              <img
                class="object-center object-cover h-36 w-36"
                src={image} 
                alt="photo"
              />
            </div>
            <div class="text-center">
              <p class="text-xl text-white font-bold mb-2">Jade Bradley</p>
              <p class="text-base text-gray-400 font-normal">Dev Ops</p>
            </div>
    </div> );
        
    
  
};

export default CardEvent;
