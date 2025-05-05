package ar.edu.itba.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private String email;

    @NotNull
    private String username;
    private String password;
}
