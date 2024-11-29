package com.academiaspedropaulet.academia.servicio.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.academiaspedropaulet.academia.modelo.Acceso;
import com.academiaspedropaulet.academia.repositorio.IAccesoRepository;
import com.academiaspedropaulet.academia.repositorio.ICrudGenericoRepository;
import com.academiaspedropaulet.academia.servicio.IAccesoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccesoServiceImp extends CrudGenericoServiceImp<Acceso, Long> implements IAccesoService {
    private final IAccesoRepository repo;
    @Override
    protected ICrudGenericoRepository<Acceso, Long> getRepo() {
        return repo;
    }
    @Override
    public List<Acceso> getAccesoByUser(String username) {
        return repo.getAccesoByUser(username);
    }
}
