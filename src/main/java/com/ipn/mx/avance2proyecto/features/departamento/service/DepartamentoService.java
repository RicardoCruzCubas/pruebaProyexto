package com.ipn.mx.avance2proyecto.features.departamento.service;

import com.ipn.mx.avance2proyecto.core.domain.departamento;
import java.util.List;

public interface DepartamentoService {
    List<departamento> findAll();
    departamento findById(Integer id);
    departamento create(departamento dept);
    departamento update(Integer id, departamento dept);
    void delete(Integer id);
}
