package com.academiaspedropaulet.academia.servicio.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.academiaspedropaulet.academia.modelo.Rol;
import com.academiaspedropaulet.academia.repositorio.ICrudGenericoRepository;
import com.academiaspedropaulet.academia.repositorio.IRolRepository;
import com.academiaspedropaulet.academia.servicio.IRolService;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RolServiceImp extends CrudGenericoServiceImp<Rol, Long> implements IRolService {

    private final IRolRepository repo;

    @Override
    protected ICrudGenericoRepository<Rol, Long> getRepo() {
        return repo;
    }

    @Override
    public Optional<Rol> getByNombre(Rol.RolNombre rolNombre) {
        return repo.findByNombre(rolNombre);
    }

    @Override
    public Optional<Rol> getById(Long id) {
        return repo.findById(id); // Implementación utilizando el método de JPA
    }
}