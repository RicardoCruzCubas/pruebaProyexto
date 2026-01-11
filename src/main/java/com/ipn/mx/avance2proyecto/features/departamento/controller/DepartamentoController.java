package com.ipn.mx.avance2proyecto.features.departamento.controller;

import com.ipn.mx.avance2proyecto.core.domain.departamento;
import com.ipn.mx.avance2proyecto.features.departamento.service.DepartamentoService;
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
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
@Tag(name = "Departamento", description = "API para gestión de departamentos")
@CrossOrigin(origins = "http://localhost:5173") // <--- Linea para que el ip sea igual en el front
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    @GetMapping
    @Operation(summary = "Obtener todos los departamentos", description = "Retorna una lista de todos los departamentos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de departamentos obtenida exitosamente")
    public List<departamento> getAll() {
        return departamentoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener departamento por ID", description = "Retorna un departamento específico por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Departamento no encontrado")
    })
    public ResponseEntity<departamento> getById(
            @Parameter(description = "ID del departamento a buscar", required = true)
            @PathVariable Integer id) {
        departamento d = departamentoService.findById(id);
        return ResponseEntity.ok(d);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo departamento", description = "Crea un nuevo registro de departamento")
    @ApiResponse(responseCode = "201", description = "Departamento creado exitosamente")
    public ResponseEntity<departamento> create(@Valid @RequestBody departamento dept) {
        departamento created = departamentoService.create(dept);
        return ResponseEntity.created(URI.create("/api/departamentos/" + created.getIdDepartamento())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar departamento", description = "Actualiza los datos de un departamento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departamento actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Departamento no encontrado")
    })
    public ResponseEntity<departamento> update(
            @Parameter(description = "ID del departamento a actualizar", required = true)
            @PathVariable Integer id,
            @Valid @RequestBody departamento dept) {
        departamento updated = departamentoService.update(id, dept);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar departamento", description = "Elimina un departamento del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Departamento eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Departamento no encontrado")
    })
    public void delete(
            @Parameter(description = "ID del departamento a eliminar", required = true)
            @PathVariable Integer id) {
        departamentoService.delete(id);
    }
}
