package com.academiaspedropaulet.academia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.academiaspedropaulet.academia.dtos.UsuarioDTO;
import com.academiaspedropaulet.academia.modelo.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends GenericMapper<UsuarioDTO, Usuario> {

    @Mapping(target = "clave", ignore = true)
    Usuario toEntityFromCADTO(UsuarioDTO.UsuarioCrearDto usuarioCrearDto);
}
