package ar.edu.itba.demo.service;

import ar.edu.itba.demo.dto.UsuarioDTO;
import ar.edu.itba.demo.model.Usuario;
import ar.edu.itba.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioServiceTest {

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;


    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

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

        usuarioDTO = UsuarioDTO.builder()
                .nombre("Luis")
                .apellido("Fernandez")
                .dni("36587456")
                .email("o9OJ8@example.com")
                .username("lfernandez")
                .password("abc")
                .build();
    }

    @Test
    void testNuevoUsuario() throws ExecutionException, InterruptedException {
        when( usuarioRepository.save( Mockito.any( Usuario.class ) ) ).thenReturn( usuario );

        CompletableFuture<Usuario> usuarioGuardadoFuturo = usuarioService.crearUsuario( usuarioDTO );

        assertNotNull( usuarioGuardadoFuturo );
        assertEquals( usuarioGuardadoFuturo.get().getApellido(), usuarioDTO.getApellido() );
        assertEquals( usuarioGuardadoFuturo.get().getNombre(), usuarioDTO.getNombre() );
        assertEquals( usuarioGuardadoFuturo.get().getDni(), usuarioDTO.getDni() );
        assertEquals( usuarioGuardadoFuturo.get().getEmail(), usuarioDTO.getEmail() );
        assertEquals( usuarioGuardadoFuturo.get().getUsername(), usuarioDTO.getUsername() );
        assertEquals( usuarioGuardadoFuturo.get().getPassword(), usuarioDTO.getPassword() );
        verify( usuarioRepository, times(1) ).save( Mockito.any( Usuario.class ) );
    }
}
