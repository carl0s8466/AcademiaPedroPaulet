package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.ApoderadoDTO;
import com.academiaspedropaulet.academia.mappers.ApoderadoMapper;
import com.academiaspedropaulet.academia.modelo.Apoderado;
import com.academiaspedropaulet.academia.servicio.ApoderadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/apoderados")
@CrossOrigin("*")
public class ApoderadoController {
    private final ApoderadoService servicekr;
    private final ApoderadoMapper mapperkr;
    @GetMapping
    public ResponseEntity<List<ApoderadoDTO>> findAll() {
        List<ApoderadoDTO> list = mapperkr.toDTOs(servicekr.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApoderadoDTO> findById(@PathVariable("id") Long id) {
        Apoderado obj = servicekr.findById(id);
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ApoderadoDTO dto) {
        Apoderado obj = servicekr.save(mapperkr.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdApoderado()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApoderadoDTO> update(@Valid @PathVariable("id") Long id, @RequestBody ApoderadoDTO dto) {
        dto.setIdApoderado(id);
        Apoderado obj = servicekr.update(id, mapperkr.toEntity(dto));
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        servicekr.delete(id);
        return ResponseEntity.noContent().build();
    }
}
