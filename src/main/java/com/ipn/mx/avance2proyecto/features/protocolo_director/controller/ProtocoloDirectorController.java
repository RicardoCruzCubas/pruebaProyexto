package com.ipn.mx.avance2proyecto.features.protocolo_director.controller;

import com.ipn.mx.avance2proyecto.core.domain.protocolo_director;
import com.ipn.mx.avance2proyecto.features.protocolo_director.service.ProtocoloDirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/protocolo-director")
@RequiredArgsConstructor
public class ProtocoloDirectorController {

    private final ProtocoloDirectorService protocoloDirectorService;

    @GetMapping
    public List<protocolo_director> getAll() {
        return protocoloDirectorService.findAll();
    }

    // ✅ MÉTODO NUEVO: Este arregla el error 404
    @GetMapping("/protocolo/{id}")
    public ResponseEntity<List<protocolo_director>> getByProtocoloId(@PathVariable Long id) {
        List<protocolo_director> asignaciones = protocoloDirectorService.findByProtocoloId(id);
        return ResponseEntity.ok(asignaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<protocolo_director> getById(@PathVariable Long id) {
        protocolo_director pd = protocoloDirectorService.findById(id);
        return ResponseEntity.ok(pd);
    }

    @PostMapping
    public ResponseEntity<protocolo_director> create(@Valid @RequestBody protocolo_director pd) {
        protocolo_director created = protocoloDirectorService.create(pd);
        return ResponseEntity.created(URI.create("/api/protocolo-director/" + created.getIdRegistro())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<protocolo_director> update(@PathVariable Long id, @Valid @RequestBody protocolo_director pd) {
        protocolo_director updated = protocoloDirectorService.update(id, pd);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        protocoloDirectorService.delete(id);
    }
}