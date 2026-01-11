package com.ipn.mx.avance2proyecto.features.director.repository;

import com.ipn.mx.avance2proyecto.core.domain.director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; //

@Repository
public interface DirectorRepository extends JpaRepository<director, Integer> {

    // ✅ 2. Método de búsqueda por ID de Usuario
    // Busca en la tabla 'director' el registro que tenga asociado el 'idUsuario' especificado
    Optional<director> findByUsuario_IdUsuario(Long idUsuario);

}