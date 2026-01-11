package com.ipn.mx.avance2proyecto.features.alumnos.repository;

import com.ipn.mx.avance2proyecto.core.domain.alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; //

@Repository
public interface AlumnosRepository extends JpaRepository<alumnos, Long> {

    // 👇 2. AGREGA ESTA LÍNEA DENTRO DE LA INTERFAZ
    // Esto le dice a Spring: "Busca en la tabla alumnos donde el campo 'usuario' tenga este 'idUsuario'"
    Optional<alumnos> findByUsuario_IdUsuario(Long idUsuario);

}