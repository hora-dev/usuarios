package ar.edu.itba.demo.config;

import ar.edu.itba.demo.model.Usuario;
import ar.edu.itba.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DBInitializer implements CommandLineRunner{

    private final JdbcTemplate jdbcTemplate;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        log.info("creando tabla usuario...");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS usuarios (id int primary key "
                + "auto_increment, nombre varchar(30), apellido varchar(30), dni varchar(10), email varchar(35), " +
                "username varchar(30), password varchar(30))");


        log.info("insertando usuarios...");
        jdbcTemplate.execute("""
                INSERT INTO usuarios (nombre, apellido, dni, email, username, password)
                VALUES ('Juan', 'Pérez', '28456789A', 'juan.perez@email.com', 'jperez', 'Clave1234');""");

        jdbcTemplate.execute("""
                INSERT INTO usuarios (nombre, apellido, dni, email, username, password)
                VALUES ('María', 'Rodríguez', '35782914B', 'maria.rodriguez@email.com', 'mrodriguez', 'Maria2025');""");

        jdbcTemplate.execute("""
                INSERT INTO usuarios (nombre, apellido, dni, email, username, password)
                VALUES ('Carlos', 'González', '42198367C', 'carlos.gonzalez@email.com', 'cgonzalez', 'Segura456');""");

        Usuario usuario = Usuario.builder()
                .nombre("Luis")
                .apellido("Fernandez")
                .dni("12345678D")
                .email("luis.fernandez@email.com")
                .username("lfernandez")
                .password("Luis1234")
                    .build();
        usuarioRepository.save(usuario);
    }
}