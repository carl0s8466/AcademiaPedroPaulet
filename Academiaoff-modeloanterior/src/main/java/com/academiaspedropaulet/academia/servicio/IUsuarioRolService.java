package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.modelo.UsuarioRol;

import java.util.List;

public interface IUsuarioRolService {
    List<UsuarioRol> findOneByUsuarioUser(String user);
    UsuarioRol save(UsuarioRol ur);
    //Usuario login(String usuario, String password);

}