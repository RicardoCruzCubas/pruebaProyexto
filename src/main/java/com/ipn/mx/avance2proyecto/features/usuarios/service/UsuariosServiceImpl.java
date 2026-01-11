package com.ipn.mx.avance2proyecto.features.usuarios.service;

import com.ipn.mx.avance2proyecto.core.domain.usuarios;
import com.ipn.mx.avance2proyecto.features.usuarios.repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosRepository usuariosRepository;

    @Override
    public List<usuarios> findAll() {
        return usuariosRepository.findAll();
    }

    @Override
    public usuarios findById(Long id) {
        return usuariosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    }

    @Override
    public usuarios create(usuarios usuario) {
        if (usuario.getFechaCreacion() == null) {
            usuario.setFechaCreacion(new Date());
        }
        return usuariosRepository.save(usuario);
    }

    @Override
    public usuarios update(Long id, usuarios usuario) {
        usuarios existing = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        existing.setEmail(usuario.getEmail());
        existing.setContrasena(usuario.getContrasena());
        existing.setRol(usuario.getRol());
        // keep fechaCreacion unless provided explicitly
        if (usuario.getFechaCreacion() != null) {
            existing.setFechaCreacion(usuario.getFechaCreacion());
        }

        return usuariosRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!usuariosRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        usuariosRepository.deleteById(id);
    }
}
