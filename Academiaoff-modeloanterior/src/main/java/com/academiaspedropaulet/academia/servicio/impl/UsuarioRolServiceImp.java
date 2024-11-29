package com.academiaspedropaulet.academia.servicio.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.academiaspedropaulet.academia.modelo.UsuarioRol;
import com.academiaspedropaulet.academia.repositorio.IUsuarioRolRepository;
import com.academiaspedropaulet.academia.servicio.IUsuarioRolService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioRolServiceImp implements IUsuarioRolService {
    private final IUsuarioRolRepository repo;
    @Override
    public List<UsuarioRol> findOneByUsuarioUser(String user) {
        return repo.findOneByUsuarioUser(user);
    }
    @Override
    public UsuarioRol save(UsuarioRol ur) {
        return repo.save(ur);
    }
}
