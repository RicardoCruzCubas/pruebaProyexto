package com.ipn.mx.avance2proyecto.features.administrador.controller;

import com.ipn.mx.avance2proyecto.core.domain.administrador;
import com.ipn.mx.avance2proyecto.features.administrador.service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/administradores")
@RequiredArgsConstructor
@Tag(name = "Administrador", description = "API para gestión de administradores")
@CrossOrigin(origins = "http://localhost:5173") // <--- Linea para que el ip sea igual en el front
public class AdministradorController {

    private final AdministradorService administradorService;

    @GetMapping
    @Operation(summary = "Obtener todos los administradores", description = "Retorna una lista de todos los administradores registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de administradores obtenida exitosamente")
    public List<administrador> getAll() {
        return administradorService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener administrador por ID", description = "Retorna un administrador específico por su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public ResponseEntity<administrador> getById(
            @Parameter(description = "ID del administrador a buscar", required = true)
            @PathVariable Long id) {
        administrador a = administradorService.findById(id);
        return ResponseEntity.ok(a);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo administrador", description = "Crea un nuevo registro de administrador en el sistema")
    @ApiResponse(responseCode = "201", description = "Administrador creado exitosamente")
    public ResponseEntity<administrador> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del administrador a crear", required = true)
            @Valid @RequestBody administrador a) {
        administrador created = administradorService.create(a);
        return ResponseEntity.created(URI.create("/api/administradores/" + created.getIdAdmin())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar administrador", description = "Actualiza los datos de un administrador existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public ResponseEntity<administrador> update(
            @Parameter(description = "ID del administrador a actualizar", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos actualizados del administrador", required = true)
            @Valid @RequestBody administrador a) {
        administrador updated = administradorService.update(id, a);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar administrador", description = "Elimina un administrador del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Administrador eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public void delete(
            @Parameter(description = "ID del administrador a eliminar", required = true)
            @PathVariable Long id) {
        administradorService.delete(id);
    }
}
