
### Api CRUD de usuarios

Este es un proyecto basado en Spring Boot 3.4.5, java 21.0.6, Maven 3.8.7, Docker 26.1.3 y docker-compose 1.29.2 para gestionar usuarios.
Es requisito tener instalado java 21 y Maven para ejecutar el proyecto.
Es requisite tener instalado docker y/o docker-compose para levantar el contenedor.

### Instrucciones de uso:

1. Asegurarse de tener instalado docker y docker-compose y openjdk 21 y tener configurado java 21
2. Correr `mvn clean spring-boot:run` en una nueva ventana terminal
3. Ingresar a `http://localhost:8008/swagger-ui/index.html` para ver la interfaz de swagger


### Levantar con Docker

1. Asegurarse de tener instalado docker
2. En la raiz del proyecto armar la imagen con `docker build -t usuarios-itba:latest .`
3. Levantar el contenedor con `docker run -d --rm -p 8008:8008 --name usuarios_itba_container usuarios-itba`
4. Quedará ejecutando en un contenedor llamado `usuarios_itba_container` en el puerto `8008`
5. Ingresar a `http://localhost:8008/swagger-ui/index.html` para ver la interfaz de swagger

### Levantar con Docker Compose

1. Asegurarse de tener instalado docker-compose
2. Levantar con `docker-compose up -d`
3. Quedará ejecutando en un contenedor llamado `usuarios_itba_container` en el puerto `8008`
4. Ingresar a `http://localhost:8008/swagger-ui/index.html` para ver la interfaz de swagger