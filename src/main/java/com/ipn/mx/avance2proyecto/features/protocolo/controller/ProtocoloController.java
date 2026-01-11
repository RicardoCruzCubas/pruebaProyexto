package com.ipn.mx.avance2proyecto.features.protocolo.controller;

import com.ipn.mx.avance2proyecto.core.domain.protocolo;
import com.ipn.mx.avance2proyecto.features.protocolo.dto.ProtocoloRequestDTO;
import com.ipn.mx.avance2proyecto.features.protocolo.service.ProtocoloService;
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
@RequestMapping("/api/protocolos")
@RequiredArgsConstructor
@Tag(name = "Protocolo", description = "API para gestión de protocolos y asignaciones")
public class ProtocoloController {

    private final ProtocoloService protocoloService;

    @GetMapping
    @Operation(summary = "Obtener todos los protocolos", description = "Retorna una lista de todos los protocolos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de protocolos obtenida exitosamente")
    public List<protocolo> getAll() {
        return protocoloService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener protocolo por ID", description = "Retorna un protocolo específico por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Protocolo encontrado"),
            @ApiResponse(responseCode = "404", description = "Protocolo no encontrado")
    })
    public ResponseEntity<protocolo> getById(
            @Parameter(description = "ID del protocolo a buscar", required = true)
            @PathVariable Long id) {
        protocolo p = protocoloService.findById(id);
        return ResponseEntity.ok(p);
    }

    /**
     * CREAR: Recibe el DTO con el protocolo y la lista de directores.
     * El servicio se encarga de persistir en la tabla 'protocolo' y 'protocolo_director'.
     */
    @PostMapping
    @Operation(summary = "Crear nuevo protocolo", description = "Crea un registro de protocolo y sus asignaciones opcionales")
    @ApiResponse(responseCode = "201", description = "Protocolo creado exitosamente")
    public ResponseEntity<protocolo> create(@Valid @RequestBody ProtocoloRequestDTO dto) {
        // Extraemos los datos del DTO
        protocolo p = dto.getProtocolo();
        List<Integer> directoresIds = dto.getDirectoresIds();

        // Llamamos al servicio transaccional
        protocolo created = protocoloService.crearConDirectores(p, directoresIds);

        return ResponseEntity.created(URI.create("/api/protocolos/" + created.getIdProtocolo())).body(created);
    }

    /**
     * ACTUALIZAR: Sincroniza los datos básicos, los 4 alumnos y la tabla intermedia de directores.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar protocolo", description = "Actualiza los datos y sincroniza directores en la tabla intermedia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Protocolo actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Protocolo no encontrado")
    })
    public ResponseEntity<protocolo> update(
            @PathVariable Long id,
            @Valid @RequestBody ProtocoloRequestDTO dto) {

        // El servicio maneja la actualización del protocolo y la limpieza/re-asignación de directores
        protocolo updated = protocoloService.actualizarConDirectores(id, dto.getProtocolo(), dto.getDirectoresIds());

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar protocolo", description = "Elimina un protocolo y sus relaciones en cascada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Protocolo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Protocolo no encontrado")
    })
    public void delete(@PathVariable Long id) {
        protocoloService.delete(id);
    }
}