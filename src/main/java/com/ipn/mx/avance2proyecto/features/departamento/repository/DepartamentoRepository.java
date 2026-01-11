package com.ipn.mx.avance2proyecto.features.departamento.repository;

import com.ipn.mx.avance2proyecto.core.domain.departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<departamento, Integer> {

}
