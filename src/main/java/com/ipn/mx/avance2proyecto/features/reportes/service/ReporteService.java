package com.ipn.mx.avance2proyecto.features.reportes.service;

import com.ipn.mx.avance2proyecto.features.reportes.dto.EnvioReporteRequest;

public interface ReporteService {
    void enviarReporteProtocolo(EnvioReporteRequest request);
    byte[] generarPdfProtocolo(Long protocoloId);
}