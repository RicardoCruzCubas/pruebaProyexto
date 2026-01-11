package com.ipn.mx.avance2proyecto.features.usuarios.controller;

import com.ipn.mx.avance2proyecto.core.domain.usuarios;
import com.ipn.mx.avance2proyecto.features.usuarios.service.UsuariosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuariosController {

    private final UsuariosService usuariosService;

    @GetMapping
    public List<usuarios> getAll() {
        return usuariosService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<usuarios> getById(@PathVariable Long id) {
        usuarios u = usuariosService.findById(id);
        return ResponseEntity.ok(u);
    }

    @PostMapping
    public ResponseEntity<usuarios> create(@Valid @RequestBody usuarios usuario) {
        usuarios created = usuariosService.create(usuario);
        return ResponseEntity.created(URI.create("/api/usuarios/" + created.getIdUsuario())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<usuarios> update(@PathVariable Long id, @Valid @RequestBody usuarios usuario) {
        usuarios updated = usuariosService.update(id, usuario);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usuariosService.delete(id);
    }
}
