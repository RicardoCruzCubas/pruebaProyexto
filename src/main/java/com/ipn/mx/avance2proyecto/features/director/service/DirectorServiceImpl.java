package com.ipn.mx.avance2proyecto.features.director.service;

import com.ipn.mx.avance2proyecto.core.domain.director;
import com.ipn.mx.avance2proyecto.features.director.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    @Override
    public List<director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public director findById(Integer id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Director no encontrado"));
    }

    @Override
    public director create(director d) {
        return directorRepository.save(d);
    }

    @Override
    public director update(Integer id, director d) {
        director existing = directorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Director no encontrado"));

        existing.setNombre(d.getNombre());
        existing.setApellidoPaterno(d.getApellidoPaterno());
        existing.setApellidoMaterno(d.getApellidoMaterno());
        existing.setEscuelaPerteneciente(d.getEscuelaPerteneciente());
        existing.setCargo(d.getCargo());
        existing.setNumeroTelefonico(d.getNumeroTelefonico());
        existing.setDepartamento(d.getDepartamento());
        existing.setUsuario(d.getUsuario());

        return directorRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        if (!directorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Director no encontrado");
        }
        directorRepository.deleteById(id);
    }

    // Busca los datos del director usando el ID de la cuenta de usuario
    @Override
    public director findByUsuario(Long idUsuario) {
        return directorRepository.findByUsuario_IdUsuario(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró director asociado al usuario ID: " + idUsuario));
    }
}