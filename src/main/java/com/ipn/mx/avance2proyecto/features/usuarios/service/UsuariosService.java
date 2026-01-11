package com.ipn.mx.avance2proyecto.features.usuarios.service;

import com.ipn.mx.avance2proyecto.core.domain.usuarios;
import java.util.List;

public interface UsuariosService {
    List<usuarios> findAll();
    usuarios findById(Long id);
    usuarios create(usuarios usuario);
    usuarios update(Long id, usuarios usuario);
    void delete(Long id);
}
