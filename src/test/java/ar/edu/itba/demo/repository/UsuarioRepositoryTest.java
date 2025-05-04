package ar.edu.itba.demo.repository;

import ar.edu.itba.demo.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = Usuario.builder()
                .id(1L)
                .nombre("Luis")
                .apellido("Fernandez")
                .dni("36587456")
                .email("o9OJ8@example.com")
                .username("lfernandez")
                .password("abc")
                .build();

    }

    @Test
    void testBuscarPorIdNoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty() );

        Optional<Usuario> usuarioNotFound = usuarioRepository.findById(1L);

        assertFalse( usuarioNotFound.isPresent() );
        verify( usuarioRepository, times(1) ).findById(1L   );
    }

    @Test
    void testBuscarPorIdEncontrado() {
        when( usuarioRepository.findById(1L) ).thenReturn( Optional.of( usuario ) );

        Optional<Usuario> usuarioFound = usuarioRepository.findById(1L);

        assertTrue( usuarioFound.isPresent() );
        assertEquals( usuarioFound.get().getApellido(), usuario.getApellido() );
        assertEquals( usuarioFound.get().getNombre(), usuario.getNombre() );
        assertEquals( usuarioFound.get().getDni(), usuario.getDni() );
        assertEquals( usuarioFound.get().getEmail(), usuario.getEmail() );
        assertEquals( usuarioFound.get().getUsername(), usuario.getUsername() );
        assertEquals( usuarioFound.get().getPassword(), usuario.getPassword() );
        verify( usuarioRepository, times(1) ).findById(1L   );
    }

    @Test
    void testGuardarOk() {
        when( usuarioRepository.save( Mockito.any( Usuario.class ) ) ).thenReturn( usuario );

        Usuario usuarioGuardado = usuarioRepository.save( usuario );

        assertNotNull( usuarioGuardado );
        assertEquals( usuarioGuardado.getApellido(), usuario.getApellido() );
        assertEquals( usuarioGuardado.getNombre(), usuario.getNombre() );
        assertEquals( usuarioGuardado.getDni(), usuario.getDni() );
        assertEquals( usuarioGuardado.getEmail(), usuario.getEmail() );
        assertEquals( usuarioGuardado.getUsername(), usuario.getUsername() );
        assertEquals( usuarioGuardado.getPassword(), usuario.getPassword() );
        verify( usuarioRepository, times(1) ).save( Mockito.any( Usuario.class ) );
    }
}
