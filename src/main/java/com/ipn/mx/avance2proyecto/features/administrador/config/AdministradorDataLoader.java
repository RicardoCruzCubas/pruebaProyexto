package com.ipn.mx.avance2proyecto.features.administrador.config;

import com.ipn.mx.avance2proyecto.core.domain.administrador;
import com.ipn.mx.avance2proyecto.features.administrador.repository.AdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdministradorDataLoader implements CommandLineRunner {

    private final AdministradorRepository administradorRepository;

    @Override
    public void run(String... args) throws Exception {
        if (administradorRepository.count() > 0) return;

        List<administrador> seeds = List.of(
                administrador.builder().contrasena("adminpass01").nombreCompleto("Laura Méndez").build(),
                administrador.builder().contrasena("adminpass02").nombreCompleto("Jorge Hernández").build(),
                administrador.builder().contrasena("securepwd03").nombreCompleto("Mariana Torres").build(),
                administrador.builder().contrasena("securepwd04").nombreCompleto("Pablo Sánchez").build(),
                administrador.builder().contrasena("adminsecure5").nombreCompleto("Elena Rojas").build()
        );

        administradorRepository.saveAll(seeds);
    }
}
