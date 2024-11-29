package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.modelo.Nivel;
import com.academiaspedropaulet.academia.repositorio.ICrudGenericoRepository;
import com.academiaspedropaulet.academia.repositorio.NivelRepository;
import com.academiaspedropaulet.academia.servicio.NivelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class NivelServiceImp extends CrudGenericoServiceImp<Nivel, Long> implements NivelService {
    private final NivelRepository kr;

    @Override
    protected ICrudGenericoRepository<Nivel, Long> getRepo() { return kr; }

}
