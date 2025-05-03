package ar.edu.itba.demo.controller;

import ar.edu.itba.demo.dto.UsuarioDTO;
import ar.edu.itba.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Tag(name = "Usuario CRUD API", description = "CREATE, READ usuario")
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws InterruptedException, ExecutionException {
        log.info( "Ejecutando guardando usuario: {}", usuarioDTO );
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario( usuarioDTO ) );
    }
}

