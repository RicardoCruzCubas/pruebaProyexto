package com.ipn.mx.avance2proyecto.features.alumnos.controller;

import com.ipn.mx.avance2proyecto.core.domain.alumnos;
import com.ipn.mx.avance2proyecto.features.alumnos.service.alumnosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
@Tag(name = "Alumnos", description = "API para gestión de alumnos")
public class AlumnosController {

    private final alumnosService alumnosService;

    @GetMapping
    @Operation(summary = "Obtener todos los alumnos", description = "Retorna una lista de todos los alumnos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de alumnos obtenida exitosamente")
    public List<alumnos> getAll() {
        return alumnosService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener alumno por ID", description = "Retorna un alumno específico por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno encontrado"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    public ResponseEntity<alumnos> getById(
            @Parameter(description = "ID del alumno a buscar", required = true)
            @PathVariable Long id) {
        alumnos a = alumnosService.findById(id);
        return ResponseEntity.ok(a);
    }

    //ENDPOINT: Buscar por ID de Usuario (Login)
    // Este es el que busca tu Dashboard.jsx
    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener alumno por ID de Usuario", description = "Busca los datos del alumno usando el ID de su cuenta de usuario")
    public ResponseEntity<alumnos> getByUsuario(@PathVariable Long idUsuario) {
        try {
            alumnos a = alumnosService.findByUsuario(idUsuario);
            return ResponseEntity.ok(a);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Crear nuevo alumno", description = "Crea un nuevo registro de alumno")
    @ApiResponse(responseCode = "201", description = "Alumno creado exitosamente")
    public ResponseEntity<alumnos> create(@Valid @RequestBody alumnos a) {
        alumnos created = alumnosService.create(a);
        return ResponseEntity.created(URI.create("/api/alumnos/" + created.getNumeroBoleta())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar alumno", description = "Actualiza los datos de un alumno existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    public ResponseEntity<alumnos> update(
            @Parameter(description = "ID del alumno a actualizar", required = true)
            @PathVariable Long id,
            @Valid @RequestBody alumnos a) {
        alumnos updated = alumnosService.update(id, a);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar alumno", description = "Elimina un alumno del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alumno eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado")
    })
    public void delete(
            @Parameter(description = "ID del alumno a eliminar", required = true)
            @PathVariable Long id) {
        alumnosService.delete(id);
    }
}