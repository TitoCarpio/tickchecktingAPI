import React, { useState } from "react";
import { set } from "react-hook-form";
import logo from "../images/logo-tickcheckting.png";
import tinyLogo from "../images/ico-png.png";
import SearchBar from "../components/searchBar";
import {Bars4Icon} from '@heroicons/react/24/outline'
import { useMediaQuery } from 'react-responsive';

//fucnion que me cambia el estado de la sesion


const NavBar = ({setSesion}) => {

  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const isMobile = useMediaQuery({ maxWidth: 920 });

  const handleMenuToggle = () => {
    setIsMenuOpen(!isMenuOpen);
  };


  const handleLogout = ( ) => {

    localStorage.setItem("sesion", false);
    //setSesion(localStorage.getItem("sesion"));
    //cambia la ruta a la de login
    window.location.href = "/login";
    localStorage.clear();
    setSesion(false);
  };

  return (
    <nav className="flex items-center  bg-p-brown h-20 shadow-2xl">
      <div className="logo w-1/5">
        <img src={tinyLogo} alt="tinyLogo" className="max-h-20 lg:hidden" />
        <img src={logo} alt="logo" className="ml-5  max-h-20 hidden sm:block md:block :" />
      </div>
      <div className="max w-full lg:w-1/3 lg:ml-10">
        <SearchBar/>
      </div>
      <div className="block lg:hidden w-1/5">
      
        <button className="flex mx-2 px-3 py-2 border rounded  text-p-white hover:text-p-yellow hover:border-p-red " onClick={handleMenuToggle}>
          <Bars4Icon className="h-4 w-4"/>
        </button>
      </div>

      {isMobile && isMenuOpen && (
        <div className="block lg:hidden w-1/3 h-1/5 bg-p-brown border-p-white border-2 rounded-bl-3xl absolute right-0 top-20">
          <div className="w-full flex flex-col items-center">
              <a className="text-p-white mr-4 bg-gray-500 pt-4 p-4 pr-5 pl-5 hover:bg-gray-600 transition-all rounded" href="/home"><i className="fas fa-home"></i> Events</a>
              <a className="text-p-white mr-4 bg-gray-500 pt-4 p-4 pr-5 pl-5 hover:bg-gray-600 transition-all rounded" href="/tickets"><i className="fas fa-question"></i> Tickets</a>
              <a className="text-p-white mr-4 bg-gray-500 pt-4 p-4 pr-5 pl-5 hover:bg-gray-600 transition-all rounded" href="#" onClick={handleLogout}><i className="fas fa-reply"></i> Logout</a>
          </div>
        </div>
      )}

      <div className="w-full  flex-grow lg:flex lg:w-auto lg:flex-grow hidden sm:block justify-end">
            <a className="text-p-white mr-4 bg-gray-500 pt-4 p-4 pr-5 pl-5 hover:bg-gray-600 transition-all rounded" href="#"><i className="fas fa-home"></i> Events</a>
            <a className="text-p-white mr-4 bg-gray-500 pt-4 p-4 pr-5 pl-5 hover:bg-gray-600 transition-all rounded" href="#"><i className="fas fa-question"></i> Tickets</a>
            <a className="text-p-white mr-4 bg-gray-500 pt-4 p-4 pr-5 pl-5 hover:bg-gray-600 transition-all rounded" href="#" onClick={handleLogout}><i className="fas fa-reply"></i> Logout</a>
      </div>

    </nav>
  );
};

export default NavBar;  