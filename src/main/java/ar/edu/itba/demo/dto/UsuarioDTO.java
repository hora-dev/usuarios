package ar.edu.itba.demo.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String username;
    private String password;
}
