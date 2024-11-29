package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.NotaDTO;
import com.academiaspedropaulet.academia.mappers.NotaMapper;
import com.academiaspedropaulet.academia.modelo.Nota;
import com.academiaspedropaulet.academia.servicio.NotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notas")
@CrossOrigin("*")
public class NotaController {
    private final NotaService krservice;
    private final NotaMapper krmapper;
    @GetMapping
    public ResponseEntity<List<NotaDTO>> findAll() {
        List<NotaDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> findById(@PathVariable("id") Long id) {
        Nota obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody NotaDTO.NotaCADto dto) {
        NotaDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdNota()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> update(@Valid @RequestBody NotaDTO.NotaCADto dto, @PathVariable("id") Long id) {
        NotaDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
