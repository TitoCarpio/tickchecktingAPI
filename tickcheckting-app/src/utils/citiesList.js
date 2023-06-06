class city{

    constructor(id, name){
        this.id = id;
        this.name = name;
    }
}

// crea la lista de ciudades
const cities = [];
cities.push(new city(1, "San Salvador"));
cities.push(new city(2, "New York"));
cities.push(new city(3, "Cali"));
cities.push(new city(4, "Santa Ana"));
cities.push(new city(5, "Ciudad de Mexico"));

export {cities};