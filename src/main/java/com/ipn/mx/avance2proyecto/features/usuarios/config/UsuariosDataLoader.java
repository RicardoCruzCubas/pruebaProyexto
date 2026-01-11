package com.ipn.mx.avance2proyecto.features.usuarios.config;

import com.ipn.mx.avance2proyecto.core.domain.usuarios;
import com.ipn.mx.avance2proyecto.features.usuarios.repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuariosDataLoader implements CommandLineRunner {

    private final UsuariosRepository usuariosRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuariosRepository.count() > 0) {
            return; // ya hay datos
        }

        List<usuarios> seeds = List.of(
                usuarios.builder()
                        .email("usuario1@example.com")
                        .contrasena("password01")
                        .rol("ROLE_USER")
                        .fechaCreacion(new Date())
                        .build(),
                usuarios.builder()
                        .email("usuario2@example.com")
                        .contrasena("password02")
                        .rol("ROLE_USER")
                        .fechaCreacion(new Date())
                        .build(),
                usuarios.builder()
                        .email("admin@example.com")
                        .contrasena("adminpass12")
                        .rol("ROLE_ADMIN")
                        .fechaCreacion(new Date())
                        .build(),
                usuarios.builder()
                        .email("director@example.com")
                        .contrasena("directoR12")
                        .rol("ROLE_DIRECTOR")
                        .fechaCreacion(new Date())
                        .build(),
                usuarios.builder()
                        .email("guest@example.com")
                        .contrasena("guestpass1")
                        .rol("ROLE_GUEST")
                        .fechaCreacion(new Date())
                        .build()
        );

        usuariosRepository.saveAll(seeds);
    }
}
