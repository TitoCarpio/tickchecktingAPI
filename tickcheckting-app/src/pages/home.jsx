import React from "react";
import NavBar from "../components/navBar";
import Drop from "../components/dropDown";
import CardEvent from "../components/events/card";

const HomePage = ({ setSesion }) => {
  //funcion que imprime n veces la carta
  const n = 10;
  const cards = Array.from({ length: n }, (_, index) => (
    <CardEvent key={index} />
  ));

  return (
    <body>
      <div>
        <NavBar setSesion={setSesion} />
      </div>
      {/* <div className="max">
        <SearchBar/>
      </div> */}
      <div class="flex items-center justify-center h-20  space-x-10 ">
        <Drop option1 = {"Place or City"}/>
        <Drop option1 = {"Dates"}/>
      </div>

      <body>
        <div>
          <section class="max-w-6xl mx-auto px-4 sm:px-6 lg:px-4 py-12">
            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {/* mando a llamar a la funcion que me imprime n veces la carta de los evetos */}
              {cards}
            </div>
          </section>
        </div>
      </body>
    </body>
  );
};

export default HomePage;
