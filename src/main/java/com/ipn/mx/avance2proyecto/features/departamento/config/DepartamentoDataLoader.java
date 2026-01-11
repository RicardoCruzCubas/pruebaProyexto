package com.ipn.mx.avance2proyecto.features.departamento.config;

import com.ipn.mx.avance2proyecto.core.domain.departamento;
import com.ipn.mx.avance2proyecto.features.departamento.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartamentoDataLoader implements CommandLineRunner {

    private final DepartamentoRepository departamentoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (departamentoRepository.count() > 0) return;

        List<departamento> seeds = List.of(
            departamento.builder().nombreDepartamento("Recursos").nombreComletoJefe("Ana Pérez").build(),
            departamento.builder().nombreDepartamento("Finanzas").nombreComletoJefe("Luis Gómez").build(),
            departamento.builder().nombreDepartamento("TIC").nombreComletoJefe("María López").build(),
            departamento.builder().nombreDepartamento("Administración").nombreComletoJefe("Carlos Ruiz").build(),
            departamento.builder().nombreDepartamento("Operaciones").nombreComletoJefe("Sofía Díaz").build()
        );

        departamentoRepository.saveAll(seeds);
    }
}
