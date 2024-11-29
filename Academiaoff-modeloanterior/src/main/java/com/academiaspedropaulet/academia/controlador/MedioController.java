package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.MedioDTO;
import com.academiaspedropaulet.academia.mappers.MedioMapper;
import com.academiaspedropaulet.academia.modelo.Medio;
import com.academiaspedropaulet.academia.servicio.MedioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/medios")
@CrossOrigin("*")
public class MedioController {
    private final MedioService servicekr;
    private final MedioMapper mapperkr;
    @GetMapping
    public ResponseEntity<List<MedioDTO>> findAll() {
        List<MedioDTO> list = mapperkr.toDTOs(servicekr.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MedioDTO> findById(@PathVariable("id") Long id) {
        Medio obj = servicekr.findById(id);
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MedioDTO dto) {
        Medio obj = servicekr.save(mapperkr.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedio()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<MedioDTO> update(@Valid @PathVariable("id") Long id, @RequestBody MedioDTO dto) {
        dto.setIdMedio(id);
        Medio obj = servicekr.update(id, mapperkr.toEntity(dto));
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        servicekr.delete(id);
        return ResponseEntity.noContent().build();
    }
}
