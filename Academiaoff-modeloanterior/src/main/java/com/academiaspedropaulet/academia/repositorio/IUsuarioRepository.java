package com.academiaspedropaulet.academia.repositorio;

import com.academiaspedropaulet.academia.modelo.Usuario;

import java.util.Optional;

public interface IUsuarioRepository extends ICrudGenericoRepository<Usuario, Long>{

    Optional<Usuario> findOneByUser(String user);
}
