import React from "react";
import logo from "../images/bg-login.png";
import LoginForm from "../components/forms/loginForm";
const LoginPage = () => {
  return (
    <main className="bg-p-gray h-screen w-screen flex items-center justify-center">
      <div className="bg-p-white rounded-lg flex w-full mx-64">
        <div className="w-1/2  justify-center">
          <div className="flex ml-24 mt-10 flex-col text-left">
            <div className="text-p-orange text-3xl">Sign In</div>
            <div className="flex flex-row gap-2 mb-20">
              <div className="text-p-brown">You don't have an account?</div>
              <div className="text-p-red">Sign Up</div> 
            </div>
            {/* Que el Sign Up te permita registrarte con google */}
            
          </div>
            <LoginForm/>
        </div>
        <img src={logo} alt="logo" className="w-1/2 h-full rounded-tr-lg rounded-br-lg" />
      </div>
    </main>
  );
};

export default LoginPage;
