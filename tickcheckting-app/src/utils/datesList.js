class dateList {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
    
}

const dates = [];
dates.push(new dateList(1, "2019-10-10"));
dates.push(new dateList(2, "2019-10-11"));
dates.push(new dateList(3, "2019-10-12"));
dates.push(new dateList(4, "2019-10-13"));
dates.push(new dateList(5, "2019-10-14"));

export {dates};
