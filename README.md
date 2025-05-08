
### API para CRUD de usuarios protegida con JWT

Este es un proyecto basado en Spring Boot 3.4.5, Java 21.0.6, Maven 3.8.7, Docker 26.1.3 y docker-compose 1.29.2 para gestionar usuarios.
Es requisito tener instalado Java 21 y Maven para ejecutar el proyecto.
Es requisito tener instalado docker y/o docker-compose para levantar el contenedor.

### Instrucciones de uso:

1. Correr `mvn clean spring-boot:run` en una nueva ventana terminal en la raíz del proyecto
2. Ingresar a `http://localhost:8008/swagger-ui/index.html` para ver la interfaz de swagger
3. Ingresar al endpoint /api/auth/login para obtener el token de autenticación JWT utilizando alguna de las credenciales disponibles
en DBInitializer.java en la carpeta src/ar/edu/itba/demo/config, por ejemplo: `{"username": "jperez", "password": "Clave1234"}`
4. Una vez obtenido el token, utilizar el header `Authorization: Bearer <token>` en las solicitudes posteriores 


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


### Ejemplo de uso con un JWT token ( eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqcGVyZXoiLCJpYXQiOjE3NDY3MTcxMjAsImV4cCI6MTc0Njc1MzEyMH0.qHiWpCCSjjWk4nFCyXv_GM_xTpwAk2COITP20HbXVvs)

1. Ejecutar en una nueva terminal: 
   1. Obtener todos los usuarios
      `curl -X 'GET' 'http://localhost:8008/api/usuarios' -H 'accept: */*' \
 -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqcGVyZXoiLCJpYXQiOjE3NDY3MTcxMjAsImV4cCI6MTc0Njc1MzEyMH0.qHiWpCCSjjWk4nFCyXv_GM_xTpwAk2COITP20HbXVvs'`
   2. Obtener un usuario por id 
        `curl -X 'GET' \
  'http://localhost:8008/api/usuarios/1' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqcGVyZXoiLCJpYXQiOjE3NDY3MTcxMjAsImV4cCI6MTc0Njc1MzEyMH0.qHiWpCCSjjWk4nFCyXv_GM_xTpwAk2COITP20HbXVvs'`
   3. Guardar un usuario
         `curl -X 'POST' \
  'http://localhost:8008/api/usuarios' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqcGVyZXoiLCJpYXQiOjE3NDY3MTcxMjAsImV4cCI6MTc0Njc1MzEyMH0.qHiWpCCSjjWk4nFCyXv_GM_xTpwAk2COITP20HbXVvs' \
  -H 'Content-Type: application/json' \
  -d '{
  "nombre": "Horacio",
  "apellido": "Salva",
  "dni": "28456789",
  "email": "horacio@megawebs.com.ar",
  "username": "hsalva",
  "password": "12345"
}'`
   4. Eliminar un usuario por id
          `curl -X 'DELETE' \
  'http://localhost:8008/api/usuarios/3' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqcGVyZXoiLCJpYXQiOjE3NDY3MTcxMjAsImV4cCI6MTc0Njc1MzEyMH0.qHiWpCCSjjWk4nFCyXv_GM_xTpwAk2COITP20HbXVvs'`
   5. Modificar un usuario por id
   `curl -X 'PUT' \
  'http://localhost:8008/api/usuarios/5' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqcGVyZXoiLCJpYXQiOjE3NDY3MTcxMjAsImV4cCI6MTc0Njc1MzEyMH0.qHiWpCCSjjWk4nFCyXv_GM_xTpwAk2COITP20HbXVvs' \
  -H 'Content-Type: application/json' \
  -d '{
  "nombre": "Horacio",
  "apellido": "Salva",
  "dni": "28456789",
  "email": "horacio@megawebs.com.ar",
  "username": "hsalva",
  "password": "98765"
}'`