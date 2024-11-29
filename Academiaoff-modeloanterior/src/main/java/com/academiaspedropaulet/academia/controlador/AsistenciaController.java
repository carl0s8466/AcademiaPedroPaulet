package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.AsistenciaDTO;
import com.academiaspedropaulet.academia.mappers.AsistenciaMapper;
import com.academiaspedropaulet.academia.modelo.Asistencia;
import com.academiaspedropaulet.academia.servicio.AsistenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/asistencias")
@CrossOrigin("*")
public class AsistenciaController {
    private final AsistenciaService krservice;
    private final AsistenciaMapper krmapper;
    @GetMapping
    public ResponseEntity<List<AsistenciaDTO>> findAll() {
        List<AsistenciaDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> findById(@PathVariable("id") Long id) {
        Asistencia obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AsistenciaDTO.AsistenciaCADto dto) {
        AsistenciaDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAsistencia()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> update(@Valid @RequestBody AsistenciaDTO.AsistenciaCADto dto, @PathVariable("id") Long id) {
        AsistenciaDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
