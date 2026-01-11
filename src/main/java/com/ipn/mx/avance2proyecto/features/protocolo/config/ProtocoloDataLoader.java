package com.ipn.mx.avance2proyecto.features.protocolo.config;

import com.ipn.mx.avance2proyecto.core.domain.protocolo;
import com.ipn.mx.avance2proyecto.features.protocolo.repository.ProtocoloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProtocoloDataLoader implements CommandLineRunner {

    private final ProtocoloRepository protocoloRepository;

    private String longText(String base, int minLength) {
        StringBuilder sb = new StringBuilder(base);
        while (sb.length() < minLength) sb.append(" ").append(base);
        return sb.toString();
    }

    @Override
    public void run(String... args) throws Exception {
        if (protocoloRepository.count() > 0) return;

        List<protocolo> seeds = List.of(
                protocolo.builder()
                        .titulo(longText("Protocolo de seguridad y procedimientos operativos", 120))
                        .descripcion(longText("Descripción detallada del protocolo de seguridad", 60))
                        .objetivos(longText("Garantizar la seguridad y continuidad de operaciones.", 60))
                        .perfilRequerido(longText("Personal con formación en seguridad y operaciones.", 60))
                        .estado("ACTIVO")
                        .fechaRegistro(new Date())
                        .build(),
                protocolo.builder()
                        .titulo(longText("Protocolo de atención al cliente y escalación de incidencias", 120))
                        .descripcion(longText("Describe los pasos para atención y escalación de incidencias.", 60))
                        .objetivos(longText("Mejorar satisfacción y tiempos de resolución.", 60))
                        .perfilRequerido(longText("Agentes de soporte con experiencia en atención.", 60))
                        .estado("ACTIVO")
                        .fechaRegistro(new Date())
                        .build(),
                protocolo.builder()
                        .titulo(longText("Protocolo de respaldo y recuperación ante desastres", 120))
                        .descripcion(longText("Procedimientos para backup y recuperación de sistemas.", 60))
                        .objetivos(longText("Asegurar restauración de servicios en tiempos aceptables.", 60))
                        .perfilRequerido(longText("Equipo de TI con experiencia en backup y recuperación.", 60))
                        .estado("INACTIVO")
                        .fechaRegistro(new Date())
                        .build(),
                protocolo.builder()
                        .titulo(longText("Protocolo de privacidad y manejo de datos personales", 120))
                        .descripcion(longText("Normas de manejo y protección de datos personales.", 60))
                        .objetivos(longText("Cumplir normativas y proteger la privacidad.", 60))
                        .perfilRequerido(longText("Personal de cumplimiento y seguridad de la información.", 60))
                        .estado("ACTIVO")
                        .fechaRegistro(new Date())
                        .build(),
                protocolo.builder()
                        .titulo(longText("Protocolo de onboarding y capacitación de personal", 120))
                        .descripcion(longText("Proceso de incorporación y entrenamiento de nuevos empleados.", 60))
                        .objetivos(longText("Reducir tiempo de productividad y mejorar adaptación.", 60))
                        .perfilRequerido(longText("Recursos Humanos y formadores internos.", 60))
                        .estado("ACTIVO")
                        .fechaRegistro(new Date())
                        .build()
        );

        protocoloRepository.saveAll(seeds);
    }
}
