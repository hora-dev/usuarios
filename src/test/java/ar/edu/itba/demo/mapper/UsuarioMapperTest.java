
package ar.edu.itba.demo.mapper;

import ar.edu.itba.demo.dto.UsuarioDTO;
import ar.edu.itba.demo.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UsuarioMapperTest {

    @Test
    void shouldMapUsuarioToDto() {

        Usuario usuario = Usuario.builder()
                        .id(1L)
                        .nombre("Luis")
                        .apellido("Fernandez")
                        .dni("36587456")
                        .email("o9OJ8@example.com")
                        .username("lfernandez")
                        .password("abc")
                        .build();

        UsuarioDTO usuarioDTO = UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(usuario);

        assertNotNull( usuarioDTO );
        assertEquals( usuario.getNombre(), usuarioDTO.getNombre() );
        assertEquals( usuario.getApellido(), usuarioDTO.getApellido() );
        assertEquals( usuario.getDni(), usuarioDTO.getDni() );
        assertEquals( usuario.getEmail(), usuarioDTO.getEmail() );
        assertEquals( usuario.getUsername(), usuarioDTO.getUsername() );
        assertEquals( usuario.getPassword(), usuarioDTO.getPassword() );
    }
}