package ar.edu.itba.demo.service;

import ar.edu.itba.demo.dto.UsuarioDTO;
import ar.edu.itba.demo.mapper.UsuarioMapper;
import ar.edu.itba.demo.model.Usuario;
import ar.edu.itba.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    @Async
    public CompletableFuture<Usuario> crearUsuario(UsuarioDTO usuarioDTO) throws InterruptedException {
        log.info( "Ejecutando una tarea asincrona guardando usuario: {}", usuarioDTO );
        Usuario usuario = UsuarioMapper.INSTANCE.usuarioDTOToUsuario( usuarioDTO );
        Usuario usuarioGuardado = usuarioRepository.save( usuario );
        Thread.sleep(5000);
        log.info("usuario guardado: {} ", usuarioGuardado);
        return CompletableFuture.completedFuture(usuarioGuardado);
    }

    @Transactional
    public Optional<Usuario> obtenerUsuario(long id) {
        log.info( "Obteniendo usuario por id: {}", id );
        return usuarioRepository.findById(id);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        log.info( "Obteniendo todos los usuarios" );
        return usuarioRepository.findAll();
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        log.info( "Eliminando usuario por id: {}", id );
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Usuario id " + id + " no encontrado") );
        usuarioRepository.delete(u);
        log.info( "Usuario eliminado : {}", u );
    }

    @Transactional
    public Usuario modificarUsuario(long id, UsuarioDTO usuarioDTO) {
        log.info( "Modificando usuario por id: {}, usuarioDTO: {}", id, usuarioDTO );
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Usuario id " + id + " no encontrado") );
        u.setApellido( usuarioDTO.getApellido() );
        u.setNombre( usuarioDTO.getNombre() );
        u.setDni( usuarioDTO.getDni() );
        u.setEmail( usuarioDTO.getEmail() );
        u.setUsername( usuarioDTO.getUsername() );
        u.setPassword( usuarioDTO.getPassword() );
        log.info( "Usuario modificado : {}", u );
        return usuarioRepository.save(u);
    }
}
