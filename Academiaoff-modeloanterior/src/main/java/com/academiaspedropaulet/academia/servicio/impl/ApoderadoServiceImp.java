package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.modelo.Apoderado;
import com.academiaspedropaulet.academia.repositorio.ApoderadoRepository;
import com.academiaspedropaulet.academia.repositorio.ICrudGenericoRepository;
import com.academiaspedropaulet.academia.servicio.ApoderadoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ApoderadoServiceImp extends CrudGenericoServiceImp<Apoderado, Long> implements ApoderadoService {

    private final ApoderadoRepository kr;

    @Override
    protected ICrudGenericoRepository<Apoderado, Long> getRepo() { return kr; }
}
