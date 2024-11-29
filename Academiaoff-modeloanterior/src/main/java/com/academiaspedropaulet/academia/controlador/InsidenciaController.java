package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.InsidenciaDTO;
import com.academiaspedropaulet.academia.mappers.InsidenciaMapper;
import com.academiaspedropaulet.academia.modelo.Insidencia;
import com.academiaspedropaulet.academia.servicio.InsidenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/insidencias")
@CrossOrigin("*")
public class InsidenciaController {
    private final InsidenciaService krservice;
    private final InsidenciaMapper krmapper;
    @GetMapping
    public ResponseEntity<List<InsidenciaDTO>> findAll() {
        List<InsidenciaDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InsidenciaDTO> findById(@PathVariable("id") Long id) {
        Insidencia obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody InsidenciaDTO.InsidenciaCADto dto) {
        InsidenciaDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdInsidencia()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<InsidenciaDTO> update(@Valid @RequestBody InsidenciaDTO.InsidenciaCADto dto, @PathVariable("id") Long id) {
        InsidenciaDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
