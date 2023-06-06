class event {
    constructor (id, title, description, date, time, category, precios, image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.category = category;
        this.precios = precios;
        this.image = image;
    }
}

const events = [];

events.push(new event(1, "Concierto de rock", "Concierto de rock en el estadio cuscatlan", "2019-10-10", "20:00", "Musica", "20, 30, 40", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nirvana_logo_yellow.svg/270px-Nirvana_logo_yellow.svg.png"));
events.push(new event(2, "Concierto de rock", "Concierto de rock en el estadio cuscatlan", "2019-10-10", "20:00", "Musica", "20, 30, 40", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Metallica_wordmark.svg/558px-Metallica_wordmark.svg.png"));
events.push(new event(3, "Concierto de rock", "Concierto de rock en el estadio cuscatlan", "2019-10-10", "20:00", "Musica", "20, 30, 40", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/The_Beatles_logo.svg/512px-The_Beatles_logo.svg.png"));

export {events}
