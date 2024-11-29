package com.academiaspedropaulet.academia.repositorio;

import com.academiaspedropaulet.academia.modelo.Rol;

import java.util.Optional;

public interface IRolRepository extends ICrudGenericoRepository<Rol, Long>{
    Optional<Rol> findByNombre(Rol.RolNombre rolNombre);

    Optional<Rol> findByDescripcion(String nombre);

}
