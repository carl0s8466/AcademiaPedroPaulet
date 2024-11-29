package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.SedeDTO;
import com.academiaspedropaulet.academia.mappers.SedeMapper;
import com.academiaspedropaulet.academia.modelo.Sede;
import com.academiaspedropaulet.academia.servicio.SedeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/sedes")
@CrossOrigin("*")
public class SedeController {
    private final SedeService servicekr;
    private final SedeMapper mapperkr;
    @GetMapping
    public ResponseEntity<List<SedeDTO>> findAll() {
        List<SedeDTO> list = mapperkr.toDTOs(servicekr.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SedeDTO> findById(@PathVariable("id") Long id) {
        Sede obj = servicekr.findById(id);
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SedeDTO dto) {
        Sede obj = servicekr.save(mapperkr.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSede()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<SedeDTO> update(@Valid @PathVariable("id") Long id, @RequestBody SedeDTO dto) {
        dto.setIdSede(id);
        Sede obj = servicekr.update(id, mapperkr.toEntity(dto));
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        servicekr.delete(id);
        return ResponseEntity.noContent().build();
    }
}
