package com.ipn.mx.avance2proyecto.features.administrador.service;

import com.ipn.mx.avance2proyecto.core.domain.administrador;
import com.ipn.mx.avance2proyecto.features.administrador.repository.AdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository administradorRepository;

    @Override
    public List<administrador> findAll() {
        return administradorRepository.findAll();
    }

    @Override
    public administrador findById(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador no encontrado"));
    }

    @Override
    public administrador create(administrador a) {
        return administradorRepository.save(a);
    }

    @Override
    public administrador update(Long id, administrador a) {
        administrador existing = administradorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador no encontrado"));

        existing.setContrasena(a.getContrasena());
        existing.setNombreCompleto(a.getNombreCompleto());

        return administradorRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!administradorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador no encontrado");
        }
        administradorRepository.deleteById(id);
    }
}
