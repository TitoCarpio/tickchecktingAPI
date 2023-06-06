import { events } from "../../utils/eventsList";

  
  export default function TicketsCard() {

    return (
      <div className=" flex items-center">
        <div className="flex-none">
          <img className="h-12 w-12 rounded-full bg-gray-50" src={events.img} alt=""/>
        </div>
        <div className="basis-3/4 p-2">
          <button class="border-2 rounded-3xl border-p-orange hover:border-p-red">
          <div className="flex basis-1/4 items-center flex-wrap">
            <div className="basis-1/2 flex-initial m-2 flex-auto w-64">
              <p className="text-left text-sm font-medium text-p-brown font-black">{events.name}</p>
              <p className="text-left text-sm font-medium text-p-brown">{events.date}</p>
            </div>
            <div className="flex-initial basis-1/4 flex-auto w-32">
              <p className="text-sm font-medium text-p-yellow">{events.place}</p>
            </div>
            <div className="flex-initial basis-1/6 flex-auto w-40">
              <div className="flex items-start items-center">
                  <img className="h-12 w-12 flex-none rounded-full bg-gray-50" src="https://icon-library.com/images/ticket-vector-icon/ticket-vector-icon-8.jpg" alt=""/>
                  <p className="text-sm font-black text-p-red"> x {events.qty}</p>
              </div>
            </div>
          </div>
          </button>
        </div>
      </div>
      )
  }