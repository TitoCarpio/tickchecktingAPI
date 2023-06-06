//crea una lista de tickets para consumirlos en tickets
// Definir la estructura de tickets
class Tickets {
    constructor(tier, qty, event) {
      this.tier = tier;
      this.qty = qty;
      this.event = event;
    }
  }

  //creando lista de usuarios
    const tickets = [];
    tickets.push(new Tickets("VIP", 3, 1));
    tickets.push(new Tickets("General", 4, 0));
    tickets.push(new Tickets("Mesa Platinum", 1, 2));
    tickets.push(new Tickets("Mesa Platinum", 1, 3));
    tickets.push(new Tickets("VIP", 1, 4));

    export {tickets};




