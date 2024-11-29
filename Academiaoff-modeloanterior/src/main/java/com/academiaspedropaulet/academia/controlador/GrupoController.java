package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.GrupoDTO;
import com.academiaspedropaulet.academia.mappers.GrupoMapper;
import com.academiaspedropaulet.academia.modelo.Grupo;
import com.academiaspedropaulet.academia.servicio.GrupoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/grupos")
@CrossOrigin("*")
public class GrupoController {
    private final GrupoService krservice;
    private final GrupoMapper krmapper;
    @GetMapping
    public ResponseEntity<List<GrupoDTO>> findAll() {
        List<GrupoDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GrupoDTO> findById(@PathVariable("id") Long id) {
        Grupo obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody GrupoDTO.GrupoCADto dto) {
        GrupoDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdGrupo()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<GrupoDTO> update(@Valid @RequestBody GrupoDTO.GrupoCADto dto, @PathVariable("id") Long id) {
        GrupoDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
