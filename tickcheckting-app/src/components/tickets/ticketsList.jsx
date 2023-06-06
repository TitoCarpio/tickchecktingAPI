import { tickets } from "../../utils/ticketList";

export default function Ticketslist() {
    const tkt = tickets[0].map((ticket) => (
        <div className=" flex items-center">
         <p>Tier: {ticket.tier}</p>
         <p>Qty: {ticket.qty}</p>
        </div>
    ))
    return (
        <div className=" flex items-center">
            {tkt}
        </div>
    )
}