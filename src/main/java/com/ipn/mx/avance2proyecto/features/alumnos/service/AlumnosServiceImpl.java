package com.ipn.mx.avance2proyecto.features.alumnos.service;

import com.ipn.mx.avance2proyecto.core.domain.alumnos;
import com.ipn.mx.avance2proyecto.features.alumnos.repository.AlumnosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnosServiceImpl implements alumnosService {

    private final AlumnosRepository alumnosRepository;

    @Override
    public List<alumnos> findAll() {
        return alumnosRepository.findAll();
    }

    @Override
    public alumnos findById(Long id) {
        return alumnosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
    }

    @Override
    public alumnos create(alumnos a) {
        return alumnosRepository.save(a);
    }

    @Override
    public alumnos update(Long id, alumnos a) {
        alumnos existing = alumnosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));

        existing.setNombre(a.getNombre());
        existing.setApellidoPaterno(a.getApellidoPaterno());
        existing.setApellidoMaterno(a.getApellidoMaterno());
        existing.setSemestre(a.getSemestre());
        existing.setCarrera(a.getCarrera());
        existing.setNumeroTelefonico(a.getNumeroTelefonico());
        existing.setUsuario(a.getUsuario());

        return alumnosRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!alumnosRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado");
        }
        alumnosRepository.deleteById(id);
    }

    // Este método busca al alumno usando el ID de su cuenta de usuario (login)
    @Override
    public alumnos findByUsuario(Long idUsuario) {
        return alumnosRepository.findByUsuario_IdUsuario(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró un alumno asociado al usuario ID: " + idUsuario));
    }
}