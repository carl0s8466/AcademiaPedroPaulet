package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.MatriculaDTO;
import com.academiaspedropaulet.academia.mappers.MatriculaMapper;
import com.academiaspedropaulet.academia.modelo.Matricula;
import com.academiaspedropaulet.academia.servicio.MatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/matriculas")
@CrossOrigin("*")
public class MatriculaController {
    private final MatriculaService krservice;
    private final MatriculaMapper krmapper;
    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> findAll() {
        List<MatriculaDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> findById(@PathVariable("id") Long id) {
        Matricula obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MatriculaDTO.MatriculaCADto dto) {
        MatriculaDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMatricula()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDTO> update(@Valid @RequestBody MatriculaDTO.MatriculaCADto dto, @PathVariable("id") Long id) {
        MatriculaDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
