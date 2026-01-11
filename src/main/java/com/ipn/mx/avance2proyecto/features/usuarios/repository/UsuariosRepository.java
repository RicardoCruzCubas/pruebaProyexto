package com.ipn.mx.avance2proyecto.features.usuarios.repository;

import com.ipn.mx.avance2proyecto.core.domain.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; // <--- Importante

@Repository
public interface UsuariosRepository extends JpaRepository<usuarios, Long> {
    // ESTA ES LA LÍNEA QUE TE FALTA PARA EL LOGIN:
    Optional<usuarios> findByEmail(String email);
}