package com.ipn.mx.avance2proyecto.features.reportes.service;

import com.ipn.mx.avance2proyecto.core.domain.protocolo;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;

@Service
@Slf4j
public class PdfService {

    public byte[] generarPdfProtocolo(protocolo p, String nombreAlumno, String nombreProponente, String contacto) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Título principal
            Paragraph titulo = new Paragraph("REPORTE DE PROTOCOLO")
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(titulo);

            document.add(new Paragraph("\n"));

            // Información del protocolo
            document.add(new Paragraph("ID: " + p.getIdProtocolo()).setBold());
            document.add(new Paragraph("Título del Protocolo: " + p.getTitulo()).setBold());
            document.add(new Paragraph("\n"));

            // Información adicional
            if (nombreAlumno != null && !nombreAlumno.isEmpty()) {
                document.add(new Paragraph("Nombre del Alumno: " + nombreAlumno));
            }
            
            if (nombreProponente != null && !nombreProponente.isEmpty()) {
                document.add(new Paragraph("Nombre de quien lo propuso: " + nombreProponente));
            }
            
            if (contacto != null && !contacto.isEmpty()) {
                document.add(new Paragraph("Contacto: " + contacto));
            }
            
            document.add(new Paragraph("\n"));

            // Descripción
            document.add(new Paragraph("Descripción:").setBold());
            document.add(new Paragraph(p.getDescripcion()));
            document.add(new Paragraph("\n"));

            // Objetivos
            document.add(new Paragraph("Objetivos:").setBold());
            document.add(new Paragraph(p.getObjetivos()));
            document.add(new Paragraph("\n"));

            // Perfil requerido
            document.add(new Paragraph("Perfil Requerido:").setBold());
            document.add(new Paragraph(p.getPerfilRequerido()));
            document.add(new Paragraph("\n"));

            // Estado
            document.add(new Paragraph("Estado: " + p.getEstado()));

            // Fecha de registro
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            document.add(new Paragraph("Fecha de Registro: " + sdf.format(p.getFechaRegistro())));

            document.close();
            
            log.info("PDF generado exitosamente para protocolo ID: {}", p.getIdProtocolo());
            return baos.toByteArray();

        } catch (Exception e) {
            log.error("Error al generar PDF: {}", e.getMessage());
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }
}