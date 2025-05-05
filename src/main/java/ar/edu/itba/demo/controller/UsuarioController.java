package ar.edu.itba.demo.controller;

import ar.edu.itba.demo.dto.UsuarioDTO;
import ar.edu.itba.demo.model.Usuario;
import ar.edu.itba.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Tag(name = "Usuario CRUD API", description = "CREATE, READ, UPDATE, DELETE usuario")
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Crear un nuevo usuario")
    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result) throws InterruptedException, ExecutionException {
        log.info( "Ejecutando guardando usuario: {}", usuarioDTO );
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario( usuarioDTO ) );
    }

    @Operation(summary = "Obtener un usuario por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        log.info( "Obteniendo usuario por id: {}", id );
        return usuarioService.obtenerUsuario(id)
                .map( usuario -> {
                    log.info("usuario obtenido: {} ", usuario);
                    return ResponseEntity.ok( usuario );
                } )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        log.info( "Obteniendo todos los usuarios" );
        return ResponseEntity.ok( usuarioService.obtenerTodosLosUsuarios() );
    }

    @Operation(summary = "Eliminar un usuario por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuarioById(@PathVariable Long id) {
        log.info( "Eliminando usuario por id: {}", id );
        usuarioService.eliminarUsuario(id);
        log.info("Usuario eliminado");
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Modificar un usuario por id")
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result) {
        log.info( "Modificando usuario por id: {}", id );
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.ok( usuarioService.modificarUsuario(id, usuarioDTO) );
    }

    public static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}

