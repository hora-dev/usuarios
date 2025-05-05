
### API para CRUD de usuarios

Este es un proyecto basado en Spring Boot 3.4.5, Java 21.0.6, Maven 3.8.7, Docker 26.1.3 y docker-compose 1.29.2 para gestionar usuarios.
Es requisito tener instalado Java 21 y Maven para ejecutar el proyecto.
Es requisito tener instalado docker y/o docker-compose para levantar el contenedor.

### Instrucciones de uso:

1. Correr `mvn clean spring-boot:run` en una nueva ventana terminal en la raíz del proyecto
2. Ingresar a `http://localhost:8008/swagger-ui/index.html` para ver la interfaz de swagger


### Levantar con Docker

1. Asegurarse de tener instalado docker
2. En la raíz del proyecto armar la imagen con `docker build -t usuarios-itba:latest .` si no fue realizada previamente
3. Levantar el contenedor con `docker run -d --rm -p 8008:8008 --name usuarios_itba_container usuarios-itba`
4. Quedará ejecutando en un contenedor llamado `usuarios_itba_container` en el puerto `8008`
5. Ingresar a `http://localhost:8008/swagger-ui/index.html` para ver la interfaz de swagger

### Levantar con Docker Compose

1. Asegurarse de tener instalado docker-compose
2. Levantar con `docker-compose up -d`
3. Quedará ejecutando en un contenedor llamado `usuarios_itba_container` en el puerto `8008`
4. Ingresar a `http://localhost:8008/swagger-ui/index.html` para ver la interfaz de swagger


### Ejemplo de uso

1. Ejecutar en una nueva terminal: 
   1. Obtener todos los usuarios
       `curl --request GET --url http://localhost:8008/usuarios` 
   2. Obtener un usuario por id
          `curl --request GET --url http://localhost:8008/usuarios/1`
   3. Guardar un usuario
         `curl --request POST \
     --url http://localhost:8008/usuarios \
     --header 'Content-Type: application/json' \
     --data '{
     "nombre": "horacio",
     "apellido": "salva",
     "dni": "25786367",
     "email": "hsalva@megawebs.com.ar",
     "username": "hsalva",
     "password": "c1234"
   }'`
   4. Eliminar un usuario por id
          `curl --request DELETE --url http://localhost:8008/usuarios/1`