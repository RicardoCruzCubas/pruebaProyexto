package com.ipn.mx.avance2proyecto.features.asignacion.controller;

import com.ipn.mx.avance2proyecto.core.domain.asignacion;
import com.ipn.mx.avance2proyecto.features.asignacion.service.AsignacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/asignaciones")
@RequiredArgsConstructor
public class AsignacionController {

    private final AsignacionService asignacionService;

    @GetMapping
    public List<asignacion> getAll() {
        return asignacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<asignacion> getById(@PathVariable Integer id) {
        asignacion a = asignacionService.findById(id);
        return ResponseEntity.ok(a);
    }

    @PostMapping
    public ResponseEntity<asignacion> create(@Valid @RequestBody asignacion a) {
        asignacion created = asignacionService.create(a);
        return ResponseEntity.created(URI.create("/api/asignaciones/" + created.getIdAsistente())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<asignacion> update(@PathVariable Integer id, @Valid @RequestBody asignacion a) {
        asignacion updated = asignacionService.update(id, a);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        asignacionService.delete(id);
    }
}