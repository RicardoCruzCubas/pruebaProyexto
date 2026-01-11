package com.ipn.mx.avance2proyecto.features.reportes.service;

import com.ipn.mx.avance2proyecto.core.domain.protocolo;
import com.ipn.mx.avance2proyecto.features.protocolo.service.ProtocoloService;
import com.ipn.mx.avance2proyecto.features.reportes.dto.EnvioReporteRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReporteServiceImpl implements ReporteService {

    private final ProtocoloService protocoloService;
    private final PdfService pdfService;
    private final EmailService emailService;

    @Override
    public void enviarReporteProtocolo(EnvioReporteRequest request) {
        log.info("Iniciando envío de reporte para protocolo ID: {}", request.getProtocoloId());

        // 1. Obtener el protocolo
        protocolo p = protocoloService.findById(request.getProtocoloId());

        // 2. Generar PDF
        byte[] pdfBytes = pdfService.generarPdfProtocolo(
                p,
                request.getNombreAlumno(),
                request.getNombreProponente(),
                request.getContacto()
        );

        // 3. Preparar cuerpo del email
        String cuerpoEmail = construirCuerpoEmail(p, request);

        // 4. Enviar email con PDF adjunto
        String nombreArchivo = "Protocolo_" + p.getIdProtocolo() + ".pdf";
        emailService.enviarEmailConPdf(
                request.getDestinatario(),
                request.getAsunto(),
                cuerpoEmail,
                pdfBytes,
                nombreArchivo
        );

        log.info("Reporte enviado exitosamente a: {}", request.getDestinatario());
    }

    @Override
    public byte[] generarPdfProtocolo(Long protocoloId) {
        log.info("Generando PDF para protocolo ID: {}", protocoloId);
        protocolo p = protocoloService.findById(protocoloId);
        return pdfService.generarPdfProtocolo(p, null, null, null);
    }

    private String construirCuerpoEmail(protocolo p, EnvioReporteRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<h2>Acuse de Confirmación</h2>");
        sb.append("<p>Estimado/a,</p>");
        sb.append("<p>Se adjunta el reporte del protocolo: <strong>").append(p.getTitulo()).append("</strong></p>");
        
        if (request.getNombreAlumno() != null) {
            sb.append("<p>Alumno: ").append(request.getNombreAlumno()).append("</p>");
        }
        
        sb.append("<p>Estado: ").append(p.getEstado()).append("</p>");
        sb.append("<br>");
        sb.append("<p>Saludos cordiales,</p>");
        sb.append("<p><em>Sistema de Gestión de Protocolos</em></p>");
        sb.append("</body></html>");
        
        return sb.toString();
    }
}
