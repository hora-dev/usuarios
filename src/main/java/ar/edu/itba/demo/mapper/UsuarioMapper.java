package ar.edu.itba.demo.mapper;

import ar.edu.itba.demo.dto.UsuarioDTO;
import ar.edu.itba.demo.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
}
