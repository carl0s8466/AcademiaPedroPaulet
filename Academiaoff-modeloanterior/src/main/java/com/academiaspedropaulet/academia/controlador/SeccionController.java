package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.SeccionDTO;
import com.academiaspedropaulet.academia.mappers.SeccionMapper;
import com.academiaspedropaulet.academia.modelo.Seccion;
import com.academiaspedropaulet.academia.servicio.SeccionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/secciones")
@CrossOrigin("*")
public class SeccionController {
    private final SeccionService krservice;
    private final SeccionMapper krmapper;
    @GetMapping
    public ResponseEntity<List<SeccionDTO>> findAll() {
        List<SeccionDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SeccionDTO> findById(@PathVariable("id") Long id) {
        Seccion obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SeccionDTO.SeccionCADto dto) {
        SeccionDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSeccion()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<SeccionDTO> update(@Valid @RequestBody SeccionDTO.SeccionCADto dto, @PathVariable("id") Long id) {
        SeccionDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
