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

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
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
    void testNuevoUsuarioOk() throws ExecutionException, InterruptedException {
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

    @Test
    void testObtenerUsuarioOk() {

        when( usuarioRepository.findById(1L) ).thenReturn( Optional.of(usuario) );

        Optional<Usuario> usuarioPorId = usuarioService.obtenerUsuario(1L);

        assertTrue( usuarioPorId.isPresent() );
        assertEquals( usuarioPorId.get().getApellido(), usuario.getApellido() );
        assertEquals( usuarioPorId.get().getNombre(), usuario.getNombre() );
        assertEquals( usuarioPorId.get().getDni(), usuario.getDni() );
        assertEquals( usuarioPorId.get().getEmail(), usuario.getEmail() );
        assertEquals( usuarioPorId.get().getUsername(), usuario.getUsername() );
        assertEquals( usuarioPorId.get().getPassword(), usuario.getPassword() );
        verify( usuarioRepository, times(1) ).findById(1L   );
    }

    @Test
    void testObtenerUsuarioNoEncontrado() {

        when( usuarioRepository.findById(1L) ).thenReturn( Optional.empty() );

        Optional<Usuario> usuarioPorId = usuarioService.obtenerUsuario(1L);

        assertFalse( usuarioPorId.isPresent() );
        verify( usuarioRepository, times(1) ).findById(1L   );
    }
    
    @Test
    void testObtenerTodosLosUsuarios() {

        when( usuarioRepository.findAll() ).thenReturn( List.of( usuario ) );

        List<Usuario> todosLosUsuarios = usuarioService.obtenerTodosLosUsuarios();
        
        assertFalse( todosLosUsuarios.isEmpty() );
        assertEquals( todosLosUsuarios.getFirst().getApellido(), usuario.getApellido() );
        assertEquals( todosLosUsuarios.getFirst().getNombre(), usuario.getNombre() );
        assertEquals( todosLosUsuarios.getFirst().getDni(), usuario.getDni() );
        assertEquals( todosLosUsuarios.getFirst().getEmail(), usuario.getEmail() );
        assertEquals( todosLosUsuarios.getFirst().getUsername(), usuario.getUsername() );
        assertEquals( todosLosUsuarios.getFirst().getPassword(), usuario.getPassword() );
        verify( usuarioRepository, times(1) ).findAll();
    }

}
