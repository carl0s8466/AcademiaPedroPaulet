package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.NivelDTO;
import com.academiaspedropaulet.academia.mappers.NivelMapper;
import com.academiaspedropaulet.academia.modelo.Nivel;
import com.academiaspedropaulet.academia.servicio.NivelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/niveles")
@CrossOrigin("*")
public class NivelController {
    private final NivelService servicekr;
    private final NivelMapper mapperkr;
    @GetMapping
    public ResponseEntity<List<NivelDTO>> findAll() {
        List<NivelDTO> list = mapperkr.toDTOs(servicekr.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NivelDTO> findById(@PathVariable("id") Long id) {
        Nivel obj = servicekr.findById(id);
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody NivelDTO dto) {
        Nivel obj = servicekr.save(mapperkr.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdNivel()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<NivelDTO> update(@Valid @PathVariable("id") Long id, @RequestBody NivelDTO dto) {
        dto.setIdNivel(id);
        Nivel obj = servicekr.update(id, mapperkr.toEntity(dto));
        return ResponseEntity.ok(mapperkr.toDTO(obj));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        servicekr.delete(id);
        return ResponseEntity.noContent().build();
    }
}
