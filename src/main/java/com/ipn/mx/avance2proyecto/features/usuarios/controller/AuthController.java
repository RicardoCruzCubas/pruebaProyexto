package com.ipn.mx.avance2proyecto.features.usuarios.controller;

import com.ipn.mx.avance2proyecto.core.domain.usuarios;
import com.ipn.mx.avance2proyecto.features.usuarios.repository.UsuariosRepository;
import com.ipn.mx.avance2proyecto.features.usuarios.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth") // Ruta separada para autenticación
@RequiredArgsConstructor
public class AuthController {

    private final UsuariosRepository usuariosRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. Usamos el método nuevo del repositorio para buscar por email
        Optional<usuarios> usuarioOpt = usuariosRepository.findByEmail(request.getEmail());

        // 2. Si el usuario existe
        if (usuarioOpt.isPresent()) {
            usuarios u = usuarioOpt.get();

            // 3. Verificamos que la contraseña coincida (Texto plano según tu base de datos)
            if (u.getContrasena().equals(request.getPassword())) {

                // IMPORTANTE: Borramos la contraseña del objeto antes de enviarlo al front por seguridad
                u.setContrasena(null);

                // Login exitoso: Devolvemos los datos del usuario (Rol, Email, etc.)
                return ResponseEntity.ok(u);
            }
        }

        // Si el email no existe o la contraseña está mal
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}