import React, { useState } from "react";
import { useNavigate } from "react-router";

import Input from "../components/inputs/textInput";
import SubmitButton from "../components/buttons/submitButton";
import { events } from "../utils/eventsList";
// import SubmitButton from "../components/buttons/submitButton";


function NewEvent() {
  const navigate = useNavigate();




  const [formValues, setFormValues] = useState({
    title: "",
    description: "",
    date: "",
    time: "",
    // categoria: "",
    image: "",
    
    
  }); //lo inicio como un string vacio
    //estado para el contador
  const [count, setCount] = useState(1);
  

  const handleInputChange = (e) => {
    setFormValues({
      ...formValues,
      [e.target.name]: e.target.value, //capturo lo que se escribe en el input
    });
  };



  // //estado para el contado
  // const [count, setCount] = useState(1);
  //funcion que incrementa el contador
  const increment = () => {
    console.log(count);
    setCount(count + 1);
    console.log(count);
  };

  //funcion que decrementa el contador
  const decrement = () => {
    console.log(count);
    setCount(count - 1);
    console.log(count);
  };

  //funcion que crea la cantidad de inputs dependiendo del contador
  const createInputs = Array.from({ length:count }, (_, i) => (
    <div  onChange={handleInputChange}>
      <Input
        label={"Precio" + (i + 1).toString()}
        name={"precio"+ (i + 1).toString()}
        classNameLabel={"text-p-brown"}
        classNameDiv="w-full"
        type="number"
        className="text-primary-500 pl-6 rounded-full w-full py-1 px-3 leading-tight placeholder:font-light border-2 border-p-orange"
        onChange={handleInputChange}
      />
      <button onClick={decrement} type="button">eliminar</button>
    </div>
  ));

  //funcion que muestra en consola los valores del formulario
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formValues);
    //obtengo el valor del select
    const select = document.getElementById("categoria");
    const categoria = select.options[select.selectedIndex].value;
    console.log(categoria);

    //obtengo el valor de los inputs de precio dependiendo del contador
    const precios = [];
    for (let i = 0; i < count; i++) {
      precios.push(formValues["precio" + (i + 1).toString()]);
    }
    console.log(precios);

    //agrego los valores al objeto evento y lo agrego a la lista de eventos
    const evento = {
      id: events.length + 1,
      title: formValues.title,
      description: formValues.description,
      date: formValues.date,
      time: formValues.time,
      categoria: categoria,
      precios: precios,
      image: formValues.image
    };
    console.log(evento);
    events.push(evento);
    console.log(events);
    //limpio los valores del formulario
    setFormValues({
      title: "",
      description: "",
      date: "",
      time: "",
      // categoria: "",
      image: "",
    });

    //utiliza la funcion navigate para ir a la pagina de inicio
    navigate("/home");

    
  };

  return (
    <div>
      <form
        className="flex flex-col gap-2 lg:mx-24 mx-10 lg:mt-0 mt-4"
        onSubmit={handleSubmit}
        onChange={handleInputChange}
      >
        <Input
          id="title"
          label={"Title"}
          name={"title"}
          classNameLabel={"text-p-brown"}
          placeholder="Title of the event"
          classNameDiv="w-full"
          className="text-primary-500 pl-6 rounded-full w-full py-1 px-3 leading-tight placeholder:font-light border-2 border-p-orange"
          onChange={handleInputChange} //capturo lo que se escribe en el input
        />
        <Input
          id="description"
          label={"Description"}
          name={"description"}
          classNameLabel={"text-p-brown"}
          placeholder="Title of the event"
          classNameDiv="w-full"
          className="text-primary-500 pl-6 rounded-full w-full py-1 px-3 leading-tight placeholder:font-light border-2 border-p-orange"
          onChange={handleInputChange} //capturo lo que se escribe en el input
        />

        <Input
          id="date"
          label={"Date"}
          name={"date"}
          classNameLabel={"text-p-brown"}
          // placeholder="Title of the event"
          classNameDiv="w-full"
          type="date"
          className="text-primary-500 pl-6 rounded-full w-full py-1 px-3 leading-tight placeholder:font-light border-2 border-p-orange"
          onChange={handleInputChange} //capturo lo que se escribe en el input
        />
        <Input
          id="time"
          label={"Start at"}
          name={"time"}
          classNameLabel={"text-p-brown"}
          classNameDiv="w-full"
          type="time"
          className="text-primary-500 pl-6 rounded-full w-full py-1 px-3 leading-tight placeholder:font-light border-2 border-p-orange"
          onChange={handleInputChange} //capturo lo que se escribe en el input
        />

        <div>
          <label htmlFor="">Category</label>
          <select name="categoria" id="categoria">
            <option value="music">Music</option>
            <option value="sports">Sports</option>
            <option value="art">Art</option>
            <option value="food">Food</option>
          </select>
        </div>

        {/* <div id="precios"> */}
        <button id="add" onClick={increment} className=" bg-p-yellow" type="button">
          add
        </button>
        {createInputs}
        {/* </div> */}

        <Input
          id="image"
          label={"Image"}
          name={"image"}
          classNameLabel={"text-p-brown"}
          classNameDiv="w-full"
          type="text"
          className="text-primary-500 pl-6 rounded-full w-full py-1 px-3 leading-tight placeholder:font-light border-2 border-p-orange"
          onChange={handleInputChange} //capturo lo que se escribe en el input
        />
        <div className="flex lg:justify-end justify-center mb-10 ">
          <SubmitButton text="Crear evento" />
        </div>
      </form>
    </div>
  );
}

export default NewEvent;
