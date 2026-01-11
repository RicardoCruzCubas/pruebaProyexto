package com.ipn.mx.avance2proyecto.features.protocolo.dto;

import com.ipn.mx.avance2proyecto.core.domain.protocolo;
import jakarta.validation.Valid;
import lombok.Data;
import java.util.List;

@Data
public class ProtocoloRequestDTO {

    @Valid // Valida las anotaciones @NotBlank y @Size de tu clase protocolo
    private protocolo protocolo;

    // Lista de numeroTrabajador para la tabla intermedia
    private List<Integer> directoresIds;
}