package com.academiaspedropaulet.academia.servicio;

import com.academiaspedropaulet.academia.modelo.Rol;

import java.util.Optional;

public interface IRolService extends ICrudGenericoService<Rol, Long> {
    Optional<Rol> getByNombre(Rol.RolNombre rolNombre);
    Optional<Rol> getById(Long id); // Nuevo método para buscar por ID
}