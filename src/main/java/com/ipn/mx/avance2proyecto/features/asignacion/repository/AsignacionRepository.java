package com.ipn.mx.avance2proyecto.features.asignacion.repository;

import com.ipn.mx.avance2proyecto.core.domain.asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionRepository extends JpaRepository<asignacion, Integer> {
}