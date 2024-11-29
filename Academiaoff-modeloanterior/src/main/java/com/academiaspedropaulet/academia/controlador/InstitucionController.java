package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.InstitucionDTO;
import com.academiaspedropaulet.academia.mappers.InstitucionMapper;
import com.academiaspedropaulet.academia.modelo.Institucion;
import com.academiaspedropaulet.academia.servicio.InstitucionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/instituciones")
@CrossOrigin("*")
public class InstitucionController {
    private final InstitucionService krservice;
    private final InstitucionMapper krmapper;
    @GetMapping
    public ResponseEntity<List<InstitucionDTO>> findAll() {
        List<InstitucionDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InstitucionDTO> findById(@PathVariable("id") Long id) {
        Institucion obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody InstitucionDTO.InstitucionCADto dto) {
        InstitucionDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdInstitucion()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<InstitucionDTO> update(@Valid @RequestBody InstitucionDTO.InstitucionCADto dto, @PathVariable("id") Long id) {
        InstitucionDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
