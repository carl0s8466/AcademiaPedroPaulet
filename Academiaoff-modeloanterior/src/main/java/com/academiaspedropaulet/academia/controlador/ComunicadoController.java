package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.ComunicadoDTO;
import com.academiaspedropaulet.academia.mappers.ComunicadoMapper;
import com.academiaspedropaulet.academia.modelo.Comunicado;
import com.academiaspedropaulet.academia.servicio.ComunicadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comunicados")
@CrossOrigin("*")
public class ComunicadoController {
    private final ComunicadoService krservice;
    private final ComunicadoMapper krmapper;
    @GetMapping
    public ResponseEntity<List<ComunicadoDTO>> findAll() {
        List<ComunicadoDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ComunicadoDTO> findById(@PathVariable("id") Long id) {
        Comunicado obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ComunicadoDTO.ComunicadoCADto dto) {
        ComunicadoDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdComunicado()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ComunicadoDTO> update(@Valid @RequestBody ComunicadoDTO.ComunicadoCADto dto, @PathVariable("id") Long id) {
        ComunicadoDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
