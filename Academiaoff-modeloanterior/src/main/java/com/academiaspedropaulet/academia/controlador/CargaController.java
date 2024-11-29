package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.CargaDTO;
import com.academiaspedropaulet.academia.mappers.CargaMapper;
import com.academiaspedropaulet.academia.modelo.Carga;
import com.academiaspedropaulet.academia.servicio.CargaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cargas")
@CrossOrigin("*")
public class CargaController {
    private final CargaService krservice;
    private final CargaMapper krmapper;
    @GetMapping
    public ResponseEntity<List<CargaDTO>> findAll() {
        List<CargaDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CargaDTO> findById(@PathVariable("id") Long id) {
        Carga obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CargaDTO.CargaCADto dto) {
        CargaDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCarga()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CargaDTO> update(@Valid @RequestBody CargaDTO.CargaCADto dto, @PathVariable("id") Long id) {
        CargaDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
