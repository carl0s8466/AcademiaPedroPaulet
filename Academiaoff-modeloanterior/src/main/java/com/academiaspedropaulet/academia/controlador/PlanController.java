package com.academiaspedropaulet.academia.controlador;

import com.academiaspedropaulet.academia.dtos.PlanDTO;
import com.academiaspedropaulet.academia.mappers.PlanMapper;
import com.academiaspedropaulet.academia.modelo.Plan;
import com.academiaspedropaulet.academia.servicio.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/planes")
@CrossOrigin("*")
public class PlanController {
    private final PlanService krservice;
    private final PlanMapper krmapper;
    @GetMapping
    public ResponseEntity<List<PlanDTO>> findAll() {
        List<PlanDTO> list = krmapper.toDTOs(krservice.findAll());
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> findById(@PathVariable("id") Long id) {
        Plan obj = krservice.findById(id);
        return ResponseEntity.ok(krmapper.toDTO(obj));
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PlanDTO.PlanCADto dto) {
        PlanDTO obj = krservice.saveD(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPlan()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlanDTO> update(@Valid @RequestBody PlanDTO.PlanCADto dto, @PathVariable("id") Long id) {
        PlanDTO obj = krservice.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        krservice.delete(id);
        return ResponseEntity.noContent().build();
    }
}
