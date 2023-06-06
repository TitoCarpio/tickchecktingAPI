import React from "react";
import SearchBar from "./searchBar";
// import { set } from "react-hook-form";
import { useNavigate } from "react-router";




//fucnion que me cambia el estado de la sesion


const NavBar = ({setSesion}) => {
  const handleLogout = ( ) => {

    localStorage.setItem("sesion", false);
    window.location.href = "/login";
    localStorage.clear();
    setSesion(false);
  };

  const navigate = useNavigate();
  const tickets = () => navigate("/tickets");




  return (
    <nav class="flex items-center justify-between bg-gray-800 h-20 shadow-2xl  bg-p-brown  text-p-white">
      <div class="logo  ">
        <h1 class="text-white ml-4 cursor-pointer text-2xl ">Logo</h1>
      </div>
      
      <SearchBar/>
      <ul class="flex">
        <li>
          <a class="text-white mr-4 bg-gray-500 pt-4 p-4 pr-5 pl-5 hover:bg-gray-600 transition-all rounded" href="/home"><i class="fas fa-home"></i> Events</a>
        </li>
        <li onClick={tickets}>
          <a class="text-white  mr-4 bg-gray-500 pt-4 p-4 pr-5 pl-5 hover:bg-gray-600 transition-all rounded" href="/tickets"><i class="fas fa-question"></i> Tickets</a>
        </li>
        <li onClick={handleLogout}>
          <a class="text-white mr-4 bg-gray-500 pt-4 p-4 pr-5 pl-5 hover:bg-gray-600 transition-all rounded" href="#"><i class="fas fa-reply"></i> Logout</a>
        </li>
      </ul>
    </nav>
  );
};

export default NavBar;  
