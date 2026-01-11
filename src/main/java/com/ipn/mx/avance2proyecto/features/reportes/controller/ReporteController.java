package com.ipn.mx.avance2proyecto.features.reportes.controller;

import com.ipn.mx.avance2proyecto.features.reportes.dto.EnvioReporteRequest;
import com.ipn.mx.avance2proyecto.features.reportes.service.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@Tag(name = "Reportes", description = "API para generación y envío de reportes en PDF")
public class ReporteController {

    private final ReporteService reporteService;

    @PostMapping("/enviar-protocolo")
    @Operation(
            summary = "Enviar reporte de protocolo por email",
            description = "Genera un PDF con la información del protocolo y lo envía por correo electrónico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte enviado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Protocolo no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error al enviar el reporte")
    })
    public ResponseEntity<Map<String, String>> enviarReporteProtocolo(
            @Valid @RequestBody EnvioReporteRequest request) {
        
        reporteService.enviarReporteProtocolo(request);
        
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Reporte enviado exitosamente a: " + request.getDestinatario());
        response.put("protocoloId", request.getProtocoloId().toString());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/descargar-protocolo/{id}")
    @Operation(
            summary = "Descargar PDF de protocolo",
            description = "Genera y descarga un PDF con la información del protocolo"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PDF generado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Protocolo no encontrado")
    })
    public ResponseEntity<byte[]> descargarPdfProtocolo(
            @Parameter(description = "ID del protocolo", required = true)
            @PathVariable Long id) {
        
        byte[] pdfBytes = reporteService.generarPdfProtocolo(id);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Protocolo_" + id + ".pdf");
        
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}