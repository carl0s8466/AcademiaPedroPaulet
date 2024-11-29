package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.PeriodoDTO;
import com.academiaspedropaulet.academia.mappers.PeriodoMapper;
import com.academiaspedropaulet.academia.modelo.Periodo;
import com.academiaspedropaulet.academia.servicio.PeriodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/periodos")
@CrossOrigin("*")
public class PeriodoController {
    private final PeriodoService servicekr;
    private final PeriodoMapper mapperkr;
    @GetMapping
    public ResponseEntity<List<PeriodoDTO>> findAll() {
        List<PeriodoDTO> list = mapperkr.toDTOs(servicekr.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PeriodoDTO> findById(@PathVariable("id") Long id) {
        Periodo obj = servicekr.findById(id);
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PeriodoDTO dto) {
        Periodo obj = servicekr.save(mapperkr.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPeriodo()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PeriodoDTO> update(@Valid @PathVariable("id") Long id, @RequestBody PeriodoDTO dto) {
        dto.setIdPeriodo(id);
        Periodo obj = servicekr.update(id, mapperkr.toEntity(dto));
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        servicekr.delete(id);
        return ResponseEntity.noContent().build();
    }
}
