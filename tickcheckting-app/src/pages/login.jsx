import React, { useState } from "react";
import logo from "../images/bg-login.png";
import toast, { Toaster } from "react-hot-toast";
//import LoginForm from "../components/forms/loginForm";
import Input from "../components/inputs/textInput";
import SubmitButton from "../components/buttons/submitButton";
//import { Toaster, toast } from 'sonner'
import { usuario } from "../utils/userList";

function LoginPage({ setSesion }) {
  const [email, setEmail] = useState(""); //lo inicio como un string vacio
  const [password, setPassword] = useState(""); //lo inicio como un string vacio

  const [formValues, setFormValues] = useState({
    correo: "",
    contrasena: "",
  }); //lo inicio como un string vacio

  //creando estado de errores
  const [error, setError] = useState(false);

  const handleInputChange = (e) => {
    setFormValues({
      ...formValues,
      [e.target.name]: e.target.value, //capturo lo que se escribe en el input
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault(); //para que no se recargue la pagina
    if (formValues.correo == "" || formValues.contrasena == "") {
      setError(true);
      setSesion(false);
      toast.error("Todos los campos son obligatorios");
      return;
    } else {
      buscarUsuario();
    }
  };

  const buscarUsuario = () => {
    // console.log(usuario)
    //busco coincidencia de usuario y contraseña en la lista de usuarios
    const usuarioEncontrado = usuario.find(
      (usuario) =>
        usuario.email === formValues.correo &&
        usuario.password === formValues.contrasena
    );
    if (usuarioEncontrado) {
      localStorage.setItem("sesion", true);
      setError(false);
      setSesion(localStorage.getItem("sesion"));
    } else {
      setError(true);
      setSesion(false);
      console.log(formValues)
      toast.error("Usuario o contraseña incorrectos");
    }
  };
  return (
    <main className="bg-p-gray h-screen w-screen flex items-center justify-center">
      <Toaster />
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
          {/* <LoginForm/> */}
          <form className="flex flex-col gap-2 mx-24" onSubmit={handleSubmit}>
            <Input
              id="username"
              label={"Email"}
              name={"correo"}
              classNameLabel={"text-p-brown"}
              placeholder="Username"
              classNameDiv="w-full"
              // error={errors?.username?.message}
              className="text-primary-500 pl-6 rounded-full w-full py-1 px-3 leading-tight placeholder:font-light border-2 border-p-orange"
              // register={{
              //   ...register('username', {
              //     required: 'Username required',
              //   }),
              // }}
              onChange={handleInputChange} //capturo lo que se escribe en el input
            />
            <Input
              id="password"
              label={"Password"}
              name={"contrasena"}
              classNameLabel={"text-p-brown"}
              placeholder="Password"
              classNameDiv="w-full"
              className="text-primary-500  pl-6 rounded-full w-full py-1 px-3 leading-tight placeholder:font-light border-2 border-p-orange"
              //error={errors?.password?.message}
              // register={{
              //   ...register('password', {
              //     required: 'Password required',
              //   }),
              // }}
              type="password"
              onChange={handleInputChange} //capturo lo que se escribe en el input
            />
            <div className="flex justify-end ">
              <SubmitButton text="Sign In" />
            </div>
          </form>
          {error && <Toaster position="top-center" reverseOrder={false} />}
        </div>
        <img
          src={logo}
          alt="logo"
          className="w-1/2 h-full rounded-tr-lg rounded-br-lg"
        />
      </div>
    </main>
  );
}

export default LoginPage;
