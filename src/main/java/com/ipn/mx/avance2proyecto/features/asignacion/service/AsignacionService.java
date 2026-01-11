package com.ipn.mx.avance2proyecto.features.asignacion.service;

import com.ipn.mx.avance2proyecto.core.domain.asignacion;
import java.util.List;

public interface AsignacionService {
    List<asignacion> findAll();
    asignacion findById(Integer id);
    asignacion create(asignacion a);
    asignacion update(Integer id, asignacion a);
    void delete(Integer id);
}