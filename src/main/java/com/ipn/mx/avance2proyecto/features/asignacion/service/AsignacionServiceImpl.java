package com.ipn.mx.avance2proyecto.features.asignacion.service;

import com.ipn.mx.avance2proyecto.core.domain.asignacion;
import com.ipn.mx.avance2proyecto.features.asignacion.repository.AsignacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsignacionServiceImpl implements AsignacionService {

    private final AsignacionRepository asignacionRepository;

    @Override
    public List<asignacion> findAll() {
        return asignacionRepository.findAll();
    }

    @Override
    public asignacion findById(Integer id) {
        return asignacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignación no encontrada"));
    }

    @Override
    public asignacion create(asignacion a) {
        return asignacionRepository.save(a);
    }

    @Override
    public asignacion update(Integer id, asignacion a) {
        asignacion existing = asignacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignación no encontrada"));

        existing.setNumeroBoleta(a.getNumeroBoleta());
        existing.setFechaAsignacion(a.getFechaAsignacion());
        existing.setEstado(a.getEstado());
        existing.setProtocolo_id(a.getProtocolo_id());

        return asignacionRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        if (!asignacionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignación no encontrada");
        }
        asignacionRepository.deleteById(id);
    }
}