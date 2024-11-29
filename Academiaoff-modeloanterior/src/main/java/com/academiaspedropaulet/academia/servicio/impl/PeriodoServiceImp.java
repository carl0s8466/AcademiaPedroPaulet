package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.modelo.Periodo;
import com.academiaspedropaulet.academia.repositorio.ICrudGenericoRepository;
import com.academiaspedropaulet.academia.repositorio.PeriodoRepository;
import com.academiaspedropaulet.academia.servicio.PeriodoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PeriodoServiceImp extends CrudGenericoServiceImp<Periodo, Long> implements PeriodoService {
    private final PeriodoRepository kr;

    @Override
    protected ICrudGenericoRepository<Periodo, Long> getRepo() { return kr; }

}
