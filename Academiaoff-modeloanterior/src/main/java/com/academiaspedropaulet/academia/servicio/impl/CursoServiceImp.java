package com.academiaspedropaulet.academia.servicio.impl;

import com.academiaspedropaulet.academia.modelo.Curso;
import com.academiaspedropaulet.academia.repositorio.CursoRepository;
import com.academiaspedropaulet.academia.repositorio.ICrudGenericoRepository;
import com.academiaspedropaulet.academia.servicio.CursoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CursoServiceImp extends CrudGenericoServiceImp<Curso, Long> implements CursoService {
    private final CursoRepository kr;

    @Override
    protected ICrudGenericoRepository<Curso, Long> getRepo() { return kr; }

}
