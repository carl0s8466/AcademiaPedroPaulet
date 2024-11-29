package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.LogroDTO;
import com.academiaspedropaulet.academia.mappers.LogroMapper;
import com.academiaspedropaulet.academia.modelo.Logro;
import com.academiaspedropaulet.academia.servicio.LogroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/logros")
@CrossOrigin("*")
public class LogroController {
    private final LogroService krservice;
    private final LogroMapper krmapper;
    @GetMapping
    public ResponseEntity<List<LogroDTO>> findAll() {
        List<LogroDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LogroDTO> findById(@PathVariable("id") Long id) {
        Logro obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody LogroDTO.LogroCADto dto) {
        LogroDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdLogro()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<LogroDTO> update(@Valid @RequestBody LogroDTO.LogroCADto dto, @PathVariable("id") Long id) {
        LogroDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
