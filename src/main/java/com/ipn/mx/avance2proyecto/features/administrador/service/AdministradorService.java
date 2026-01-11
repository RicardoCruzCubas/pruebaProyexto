package com.ipn.mx.avance2proyecto.features.administrador.service;

import com.ipn.mx.avance2proyecto.core.domain.administrador;
import java.util.List;

public interface AdministradorService {
    List<administrador> findAll();
    administrador findById(Long id);
    administrador create(administrador a);
    administrador update(Long id, administrador a);
    void delete(Long id);
}
