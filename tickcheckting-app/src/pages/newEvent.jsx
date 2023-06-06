import React, { useState } from "react";
import Input from "../components/inputs/textInput";
import SubmitButton from "../components/buttons/submitButton";

function NewEvent() {
  const [formValues, setFormValues] = useState({
    title: "",
    description: "",
    date: "",
    time: "",
    Image: "",
  }); //lo inicio como un string vacio

  const handleInputChange = (e) => {
    setFormValues({
      ...formValues,
      [e.target.name]: e.target.value, //capturo lo que se escribe en el input
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault(); //para que no se recargue la pagina
  };

  //estado para el contador
  const [count, setCount] = useState(1);
  //funcion que incrementa el contador
  const increment = () => {
    setCount(count + 1);
    console.log(count);
  };

  //funcion que decrementa el contador
  const decrement = () => {
    setCount(count - 1);
    console.log(count);
  };

  //funcion que crea la cantidad de inputs dependiendo del contador
  const createInputs = Array.from({ length: count }, (_, i) => (
    <div>
      <Input
        label={"Precio" + (i + 1).toString()}
        name={"precio"}
        classNameLabel={"text-p-brown"}
        classNameDiv="w-full"
        type="number"
        className="text-primary-500 pl-6 rounded-full w-full py-1 px-3 leading-tight placeholder:font-light border-2 border-p-orange"
        onChange={handleInputChange}
      />
      <button onClick={decrement}>eliminar</button>
    </div>
  ));

  return (
    <div>
      <form
        className="flex flex-col gap-2 lg:mx-24 mx-10 lg:mt-0 mt-4"
        onSubmit={handleSubmit}
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
          <select name="categoria" id="">
            <option value="music">Music</option>
            <option value="sports">Sports</option>
            <option value="art">Art</option>
            <option value="food">Food</option>
          </select>
        </div>

        {/* <div id="precios"> */}
          <button id="add" onClick={increment} className=" bg-p-yellow">
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
        







      </form>
    </div>
  );
}

export default NewEvent;
