package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.EstadotenciaDTO;
import com.academiaspedropaulet.academia.mappers.EstadotenciaMapper;
import com.academiaspedropaulet.academia.modelo.Estadotencia;
import com.academiaspedropaulet.academia.servicio.EstadotenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/estadotencias")
@CrossOrigin("*")
public class EstadotenciaController {
    private final EstadotenciaService servicekr;
    private final EstadotenciaMapper mapperkr;
    @GetMapping
    public ResponseEntity<List<EstadotenciaDTO>> findAll() {
        List<EstadotenciaDTO> list = mapperkr.toDTOs(servicekr.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstadotenciaDTO> findById(@PathVariable("id") Long id) {
        Estadotencia obj = servicekr.findById(id);
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody EstadotenciaDTO dto) {
        Estadotencia obj = servicekr.save(mapperkr.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEstadotencia()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EstadotenciaDTO> update(@Valid @PathVariable("id") Long id, @RequestBody EstadotenciaDTO dto) {
        dto.setIdEstadoAsistencia(id);
        Estadotencia obj = servicekr.update(id, mapperkr.toEntity(dto));
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        servicekr.delete(id);
        return ResponseEntity.noContent().build();
    }
}
