package com.ipn.mx.avance2proyecto.features.protocolo_director.repository;

import com.ipn.mx.avance2proyecto.core.domain.protocolo_director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProtocoloDirectorRepository extends JpaRepository<protocolo_director, Long> {

    // ✅ Búsqueda: Busca por el campo 'protocolo' y luego por su 'idProtocolo'
    List<protocolo_director> findByProtocolo_IdProtocolo(Long idProtocolo);

    // ✅ Eliminación: Necesario para que el Service pueda limpiar directores al actualizar
    void deleteByProtocolo_IdProtocolo(Long idProtocolo);
}