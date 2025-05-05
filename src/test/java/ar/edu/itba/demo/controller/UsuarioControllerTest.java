package ar.edu.itba.demo.controller;

import ar.edu.itba.demo.dto.UsuarioDTO;
import ar.edu.itba.demo.model.Usuario;
import ar.edu.itba.demo.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioControllerTest {

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;
    private UsuarioDTO usuarioDTOUsernameNull;

    @Mock
    private BindingResult result;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;


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

        usuarioDTOUsernameNull = UsuarioDTO.builder()
                .nombre("Luis")
                .apellido("Fernandez")
                .dni("36587456")
                .email("o9OJ8@example.com")
                .password("abc")
                .build();

    }


    @Test
    void otenerUsuarioOk() {

        when( usuarioService.obtenerUsuario(1L) ).thenReturn( Optional.of( usuario ) );

        ResponseEntity<?> response = usuarioController.getUsuarioById(1L);

        assertNotNull( response );
        assertEquals(HttpStatus.OK, response.getStatusCode() );
        assertEquals(usuario.getApellido(), ((Usuario) response.getBody()).getApellido() );
        assertEquals(usuario.getNombre(), ((Usuario) response.getBody()).getNombre() );
        assertEquals(usuario.getDni(), ((Usuario) response.getBody()).getDni() );
        assertEquals(usuario.getEmail(), ((Usuario) response.getBody()).getEmail() );
        assertEquals(usuario.getUsername(), ((Usuario) response.getBody()).getUsername() );
        assertEquals(usuario.getPassword(), ((Usuario) response.getBody()).getPassword() );
        verify( usuarioService, times(1) ).obtenerUsuario(1L);
    }

    @Test
    void obtenerUsuarioNoEncontrado() {

        when( usuarioService.obtenerUsuario(1L) ).thenReturn( Optional.empty() );

        ResponseEntity<?> response = usuarioController.getUsuarioById(1L);

        assertNotNull( response );
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode() );
        verify( usuarioService, times(1) ).obtenerUsuario(1L);
    }

    @Test
    void obtenerTodosLosUsuarios() {

        when( usuarioService.obtenerTodosLosUsuarios() ).thenReturn( List.of( usuario ) );

        ResponseEntity<?> response = usuarioController.getAllUsuarios();

        assertNotNull( response );
        assertEquals(HttpStatus.OK, response.getStatusCode() );
        assertEquals(usuario.getApellido(), ((List<Usuario>) response.getBody()).getFirst().getApellido() );
        assertEquals(usuario.getNombre(), ((List<Usuario>) response.getBody()).getFirst().getNombre() );
        assertEquals(usuario.getDni(), ((List<Usuario>) response.getBody()).getFirst().getDni() );
        assertEquals(usuario.getEmail(), ((List<Usuario>) response.getBody()).getFirst().getEmail() );
        assertEquals(usuario.getUsername(), ((List<Usuario>) response.getBody()).getFirst().getUsername() );
        assertEquals(usuario.getPassword(), ((List<Usuario>) response.getBody()).getFirst().getPassword() );
        verify( usuarioService, times(1) ).obtenerTodosLosUsuarios();

    }

    @Test
    void crearUsuarioOk() throws InterruptedException, ExecutionException {

        when( usuarioService.crearUsuario( usuarioDTO ) ).thenReturn( CompletableFuture.completedFuture( usuario ) );

        ResponseEntity<?> response = usuarioController.crearUsuario( usuarioDTO, result );

        assertNotNull( response );
        assertEquals(HttpStatus.CREATED, response.getStatusCode() );
        assertEquals(usuarioDTO.getApellido(), ((CompletableFuture<Usuario>) response.getBody()).toCompletableFuture().get().getApellido() );
        assertEquals(usuarioDTO.getNombre(), ((CompletableFuture<Usuario>) response.getBody()).toCompletableFuture().get().getNombre() );
        assertEquals(usuarioDTO.getDni(), ((CompletableFuture<Usuario>) response.getBody()).toCompletableFuture().get().getDni() );
        assertEquals(usuarioDTO.getEmail(), ((CompletableFuture<Usuario>) response.getBody()).toCompletableFuture().get().getEmail() );
        assertEquals(usuarioDTO.getUsername(), ((CompletableFuture<Usuario>) response.getBody()).toCompletableFuture().get().getUsername() );
        assertEquals(usuarioDTO.getPassword(), ((CompletableFuture<Usuario>) response.getBody()).toCompletableFuture().get().getPassword() );
        verify( usuarioService, times(1) ).crearUsuario( usuarioDTO );
    }

    @Test
    void eliminarUsuarioOk() {

        doNothing().when(usuarioService).eliminarUsuario(1L);

        ResponseEntity<?> response = usuarioController.deleteUsuarioById(1L);

        assertNotNull( response );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode() );
        verify( usuarioService, times(1) ).eliminarUsuario(1L);
    }

    @Test
    void crearUsuarioNotOk_username_null() throws InterruptedException, ExecutionException {

        when( result.hasErrors() ).thenReturn( true );
        when ( result.getFieldErrors() ).thenReturn( List.of( new FieldError( "username", "username", "El campo username no debe ser nulo" ) ) );

        ResponseEntity<?> response = usuarioController.crearUsuario( usuarioDTOUsernameNull, result );

        assertNotNull( response );
        assertEquals( HttpStatus.BAD_REQUEST, response.getStatusCode() );
    }
}
