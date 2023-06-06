import React from "react";
import NavBar from "../components/navBar";
import { events } from "../utils/eventsList1";
import { tickets } from "../utils/ticketList";

const tkt = tickets.map((ticket) => (
    <div className=" flex flex-row p-3 justify-between">
    <div className=" flex-none basis-1/4 m-1">
      <button>
      <img className="h-10 w-10 rounded-sm bg-gray-50" src={require('../images/intercambio.png')} alt="" />
      </button>
    </div>
    <div className=" flex-none basis-1/4 m-1">
    <button>
      <img className="h-10 w-10 rounded-sm bg-gray-50" src={require('../images/codigo-qr.png')} alt="" />
    </button>
    </div>
    <div className=" flex  basis-1/4 m-1">
      <p className="text-left text-p-brown font-black m-1">Tier:</p> 
      <p className="text-p-brown"> {ticket.tier}</p> 
    </div>
    <div className="flex basis-1/4 m-1">
      <p className="text-left text-p-brown font-black m-1">Amount: </p> 
      <p className="text-p-brown">{ticket.qty} </p>
    </div>
    </div>
))

const TicketsPage = ({setSesion}) => {
  const listEvents = events.map((events) => (
      <div className=" flex items-center">
        <div className="flex-none">
          <img className="h-12 w-12 rounded-full bg-gray-50" src={events.img} alt=""/>
        </div>
        <div className="basis-3/4 p-2">
          <button class="border-2 rounded-3xl border-p-orange hover:border-p-red" onClick={() => (alert(events.id))}>
          <div className="flex basis-1/4 items-center flex-wrap">
            <div className="basis-1/2 flex-initial m-2 flex-auto w-64">
              <p className="text-left text-sm font-medium text-p-brown font-black">{events.name}</p>
              <p className="text-left text-sm font-medium text-p-brown">{events.date}</p>
            </div>
            <div className="flex-initial basis-1/4 flex-auto w-32">
              <p className="text-sm font-medium text-p-yellow">{events.place}</p>
            </div>
            <div className="flex-initial basis-1/6 flex-auto w-40 ">
              <div className="flex items-start items-center flex-wrap">
                  <img className="h-12 w-12 flex-none rounded-full bg-gray-50" src={require('../images/boleto.png')} alt=""/>
                  <p className="text-sm font-black text-p-red text-center"> x {events.qty}</p>
              </div>
            </div>
          </div>
          </button>
        </div>
      </div>
  ))

  return (
    <body >
      <div>
        <NavBar setSesion= {setSesion}/>
      </div>
      <body>
        <div>
          <section>
          <div className="flex flex-row flex-wrap p-3 m-10 rounded-lg items-center">
            <div className=" flex flex-col rounded-lg items-center basis-2/3 ">
                {listEvents}
            </div>
            <div className="border-2 basis-1/3 border-p-red flex-col rounded-3xl p-3 flex items-center w-min-350">
              <div className="items-center">
                <p className="text-p-yellow font-black">Tickets Description</p>
              </div>
              {tkt}
              <div>
                <button className="bg-p-red text-p-yellow rounded-3xl p-3 font-black">
                Activate Tickets
                </button>
              </div>
            </div >
          </div>
          </section>
        </div>
      </body>
    </body>
  );
};


export default TicketsPage;
