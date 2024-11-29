package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.TrabajadorDTO;
import com.academiaspedropaulet.academia.mappers.TrabajadorMapper;
import com.academiaspedropaulet.academia.modelo.Trabajador;
import com.academiaspedropaulet.academia.servicio.TrabajadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trabajadores")
@CrossOrigin("*")
public class TrabajadorController {
    private final TrabajadorService krservice;
    private final TrabajadorMapper krmapper;
    @GetMapping
    public ResponseEntity<List<TrabajadorDTO>> findAll() {
        List<TrabajadorDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TrabajadorDTO> findById(@PathVariable("id") Long id) {
        Trabajador obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TrabajadorDTO.TrabajadorCADto dto) {
        TrabajadorDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTrabajador()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<TrabajadorDTO> update(@Valid @RequestBody TrabajadorDTO.TrabajadorCADto dto, @PathVariable("id") Long id) {
        TrabajadorDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
