package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.CompetenciaDTO;
import com.academiaspedropaulet.academia.mappers.CompetenciaMapper;
import com.academiaspedropaulet.academia.modelo.Competencia;
import com.academiaspedropaulet.academia.servicio.CompetenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/competencias")
@CrossOrigin("*")
public class CompetenciaController {
    private final CompetenciaService krservice;
    private final CompetenciaMapper krmapper;
    @GetMapping
    public ResponseEntity<List<CompetenciaDTO>> findAll() {
        List<CompetenciaDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaDTO> findById(@PathVariable("id") Long id) {
        Competencia obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CompetenciaDTO.CompetenciaCADto dto) {
        CompetenciaDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCompetencia()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CompetenciaDTO> update(@Valid @RequestBody CompetenciaDTO.CompetenciaCADto dto, @PathVariable("id") Long id) {
        CompetenciaDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
