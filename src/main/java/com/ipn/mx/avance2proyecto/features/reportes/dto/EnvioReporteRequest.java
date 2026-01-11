package com.ipn.mx.avance2proyecto.features.reportes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnvioReporteRequest {
    
    @NotNull(message = "El ID del protocolo es obligatorio")
    private Long protocoloId;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String destinatario;
    
    private String asunto = "Acuse de confirmación - Protocolo";
    
    private String nombreAlumno;
    
    private String nombreProponente;
    
    private String contacto;
}