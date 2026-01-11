package com.ipn.mx.avance2proyecto.features.administrador.repository;

import com.ipn.mx.avance2proyecto.core.domain.administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<administrador, Long> {

}
