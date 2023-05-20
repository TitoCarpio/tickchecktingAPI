import React from "react";
import NavBar from "../components/navBar";
import SearchBar from "../components/searchBar";

const HomePage = (setSesion) => {
  // const handleReturn = (e) => {
  //   e.preventDefault();
  //   setSesion(false);
  // }


  return (
    <body >
      <div>
        <NavBar/>
      </div>
      <div className="max">
        <SearchBar/>
      </div>
      <body>
        //aqui va el contenido de la pagina principal
        //las targetas de los eventos
      </body>
      
    </body>
  );
};

export default HomePage;
