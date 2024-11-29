package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.dtos.UsuarioDTO;
import com.academiaspedropaulet.academia.modelo.Usuario;

public interface IUsuarioService extends ICrudGenericoService<Usuario, Long>{
    public UsuarioDTO login(UsuarioDTO.CredencialesDto credentialsDto);
    public UsuarioDTO register(UsuarioDTO.UsuarioCrearDto userDto);
}