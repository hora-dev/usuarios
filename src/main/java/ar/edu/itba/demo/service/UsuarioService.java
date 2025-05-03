package ar.edu.itba.demo.service;

import ar.edu.itba.demo.dto.UsuarioDTO;
import ar.edu.itba.demo.mapper.UsuarioMapper;
import ar.edu.itba.demo.model.Usuario;
import ar.edu.itba.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
