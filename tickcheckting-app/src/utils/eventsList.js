class event {
    constructor (id, title, description, date, time, category, precios, place, image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.category = category;
        this.precios = precios;
        this.place = place;
        this.image = image;
    }
}

const monthNames = [
    'January', 'February', 'March', 'April', 'May', 'June', 'July',
    'August', 'September', 'October', 'November', 'December'
  ];

const events = [];

events.push(new event(1, "Mago de oz: quinto aniversario", "Concierto de rock en el estadio cuscatlan", "2019/10/08", "20:00", "Musica", "20, 30, 40","Estadio Cuscatlan", "https://resources.tidal.com/images/ff1527a3/c90e/4ca9/ba71/ec4a7c337919/320x320.jpg"));
events.push(new event(2, "Hamilton An American Musical", "Obra de teatro", "2020/09/21", "20:00", "Musica", "20, 30, 40","Teatro Luis Poma" , 'https://allthingsliberty.com/wp-content/uploads/2015/11/hamilton-570x381.jpg'));
events.push(new event(3, "The Eras Tour", "Concierto de rock ", "2021/01/30", "20:00", "Musica", "20, 30, 40","Estadio Magico Gonzales", 'https://cosasquedanplacer.com/wp-content/uploads/2022/11/the-eras-tour-el-recorrido-de-la-ico%CC%81nica-carrera-de-taylor-swift-en-un-solo-concierto.jpg'));
events.push(new event(3, "The Eras Tour", "Concierto de rock ", "2021/01/30", "20:00", "Musica", "20, 30, 40","Estadio Magico Gonzales", 'https://cosasquedanplacer.com/wp-content/uploads/2022/11/the-eras-tour-el-recorrido-de-la-ico%CC%81nica-carrera-de-taylor-swift-en-un-solo-concierto.jpg'));
events.push(new event(3, "The Eras Tour", "Concierto de rock ", "2021/01/30", "20:00", "Musica", "20, 30, 40","Estadio Magico Gonzales", 'https://cosasquedanplacer.com/wp-content/uploads/2022/11/the-eras-tour-el-recorrido-de-la-ico%CC%81nica-carrera-de-taylor-swift-en-un-solo-concierto.jpg'));
events.push(new event(3, "The Eras Tour", "Concierto de rock ", "2021/01/30", "20:00", "Musica", "20, 30, 40","Estadio Magico Gonzales", 'https://cosasquedanplacer.com/wp-content/uploads/2022/11/the-eras-tour-el-recorrido-de-la-ico%CC%81nica-carrera-de-taylor-swift-en-un-solo-concierto.jpg'));

export {events, monthNames}
