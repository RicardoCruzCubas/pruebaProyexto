package com.ipn.mx.avance2proyecto.features.alumnos.service;

import com.ipn.mx.avance2proyecto.core.domain.alumnos;
import java.util.List;

public interface alumnosService {
    List<alumnos> findAll();
    alumnos findById(Long id);
    alumnos create(alumnos a);
    alumnos update(Long id, alumnos a);
    void delete(Long id);


    // Definimos el contrato para buscar un alumno por su ID de usuario (login)
    alumnos findByUsuario(Long idUsuario);
}