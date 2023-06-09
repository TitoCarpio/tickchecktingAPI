import React, { useState } from "react";
import img from "../../images/bg-nav-bar.png";
import Select from 'react-select';  
import { cities } from "../../utils/citiesList";
import { dates } from "../../utils/datesList";

function FilterCard() {

  const [selectedOption, setSelectedOption] = useState(null);
  const [selectedOption2, setSelectedOption2] = useState(null);

    return(
      <main className="relative">
        <img src={img} alt="logo" className="w-full max-h-44" />
        <div className="absolute top-2 lg:top-8 left-0 w-full flex justify-center h-auto ">
          <div className="text-lg lg:text-3xl  text-p-white">Let yourself be amazed by the show</div>
        </div>
        <div className="absolute bottom-2 justify-center lg:bottom-10 flex flex-row w-full">
          <Select
            className="text-center mx-2"
            placeholder="Select place..."
            defaultValue={selectedOption}
            onChange={setSelectedOption}
            options={cities}
          />
          <Select
            className="text-center mx-2"
            placeholder="Select date..."
            defaultValue={selectedOption2}
            onChange={setSelectedOption2}
            options={dates}
          />
         </div>
    </main> 
    )
}

export default FilterCard;