package com.ipn.mx.avance2proyecto.features.director.controller;

import com.ipn.mx.avance2proyecto.core.domain.director;
import com.ipn.mx.avance2proyecto.features.director.service.DirectorService;
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
@RequestMapping("/api/directores")
@RequiredArgsConstructor
@Tag(name = "Director", description = "API para gestión de directores")
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    @Operation(summary = "Obtener todos los directores", description = "Retorna una lista de todos los directores registrados")
    @ApiResponse(responseCode = "200", description = "Lista de directores obtenida exitosamente")
    public List<director> getAll() {
        return directorService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener director por ID", description = "Retorna un director específico por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Director encontrado"),
            @ApiResponse(responseCode = "404", description = "Director no encontrado")
    })
    public ResponseEntity<director> getById(
            @Parameter(description = "ID del director a buscar", required = true)
            @PathVariable Integer id) {
        director d = directorService.findById(id);
        return ResponseEntity.ok(d);
    }

    //ENDPOINT: Buscar por ID de Usuario (Login)
    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener director por ID de Usuario", description = "Busca los datos del director usando el ID de su cuenta de usuario")
    public ResponseEntity<director> getByUsuario(@PathVariable Long idUsuario) {
        try {
            // Asegúrate de haber agregado 'findByUsuario' en tu DirectorService e Impl
            director d = directorService.findByUsuario(idUsuario);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Crear nuevo director", description = "Crea un nuevo registro de director")
    @ApiResponse(responseCode = "201", description = "Director creado exitosamente")
    public ResponseEntity<director> create(@Valid @RequestBody director d) {
        director created = directorService.create(d);
        return ResponseEntity.created(URI.create("/api/directores/" + created.getNumeroTrabajador())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar director", description = "Actualiza los datos de un director existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Director actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Director no encontrado")
    })
    public ResponseEntity<director> update(
            @Parameter(description = "ID del director a actualizar", required = true)
            @PathVariable Integer id,
            @Valid @RequestBody director d) {
        director updated = directorService.update(id, d);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar director", description = "Elimina un director del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Director eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Director no encontrado")
    })
    public void delete(
            @Parameter(description = "ID del director a eliminar", required = true)
            @PathVariable Integer id) {
        directorService.delete(id);
    }
}