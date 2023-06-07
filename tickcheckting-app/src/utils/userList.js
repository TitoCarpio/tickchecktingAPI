//crea una lista de usuarios para consumirlos al hacer login
// Definir la estructura de usuario
class Usuario {
    constructor(nombre, email, password,roles) {
      this.nombre = nombre;
      this.email = email;
      this.password = password;
      this.roles = roles;
    }
  }

  //creando lista de usuarios
    const usuario = [];
    usuario.push(new Usuario("Erick", "titocarpio9@gmail.com", "123456",["admin", "user"]));
    usuario.push(new Usuario("Ariel", "ariel@gmail.com", "carpio2001",["admin"]));
    usuario.push(new Usuario("Carlos", "carlos@gmail.com", "1234",["user"]));

    export {usuario};




