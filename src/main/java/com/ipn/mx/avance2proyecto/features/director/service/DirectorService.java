package com.ipn.mx.avance2proyecto.features.director.service;

import com.ipn.mx.avance2proyecto.core.domain.director;
import java.util.List;

public interface DirectorService {
    List<director> findAll();
    director findById(Integer id);
    director create(director d);
    director update(Integer id, director d);
    void delete(Integer id);

    // Contrato para buscar un director por su ID de usuario (login)
    director findByUsuario(Long idUsuario);
}