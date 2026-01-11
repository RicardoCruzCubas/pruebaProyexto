package com.ipn.mx.avance2proyecto.features.protocolo.repository;

import com.ipn.mx.avance2proyecto.core.domain.protocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocoloRepository extends JpaRepository<protocolo, Long> {

}
