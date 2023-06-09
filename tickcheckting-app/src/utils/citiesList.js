class city{

    constructor(id, value, label){
        this.id = id;
        this.value = value;
        this.label = label;
        
    }
}

// crea la lista de ciudades
const cities = [];
cities.push(new city(1, "San Salvador", "San Salvador"));
cities.push(new city(2, "New York","New York" ));
cities.push(new city(3, "Cali","Cali"));
cities.push(new city(4, "Santa Ana","Santa Ana"));
cities.push(new city(5, "Ciudad de Mexico","Ciudad de Mexico"));

export {cities};