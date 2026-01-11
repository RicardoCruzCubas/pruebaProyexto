package com.ipn.mx.avance2proyecto.features.protocolo.service;

import com.ipn.mx.avance2proyecto.core.domain.director;
import com.ipn.mx.avance2proyecto.core.domain.protocolo;
import com.ipn.mx.avance2proyecto.core.domain.protocolo_director;
import com.ipn.mx.avance2proyecto.features.director.repository.DirectorRepository;
import com.ipn.mx.avance2proyecto.features.protocolo.repository.ProtocoloRepository;
import com.ipn.mx.avance2proyecto.features.protocolo_director.repository.ProtocoloDirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProtocoloServiceImpl implements ProtocoloService {

    private final ProtocoloRepository protocoloRepository;
    private final ProtocoloDirectorRepository intermediateRepo;
    private final DirectorRepository directorRepo;

    @Override
    public List<protocolo> findAll() {
        return protocoloRepository.findAll();
    }

    @Override
    public protocolo findById(Long id) {
        return protocoloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Protocolo no encontrado"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!protocoloRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Protocolo no encontrado");
        }
        protocoloRepository.deleteById(id);
    }

    // IMPLEMENTACIÓN DE CREAR
    @Override
    @Transactional
    public protocolo crearConDirectores(protocolo p, List<Integer> directoresIds) {
        if (p.getFechaRegistro() == null) p.setFechaRegistro(new Date());

        // Guarda el protocolo y sus 4 alumnos automáticamente
        protocolo guardado = protocoloRepository.save(p);

        asignarDirectores(guardado, directoresIds);
        return guardado;
    }

    // IMPLEMENTACIÓN DE ACTUALIZAR
    @Override
    @Transactional
    public protocolo actualizarConDirectores(Long id, protocolo p, List<Integer> directoresIds) {
        protocolo existing = protocoloRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Protocolo no encontrado"));

        // Sincronizamos datos básicos y los 4 alumnos opcionales
        existing.setTitulo(p.getTitulo());
        existing.setDescripcion(p.getDescripcion());
        existing.setObjetivos(p.getObjetivos());
        existing.setPerfilRequerido(p.getPerfilRequerido());
        existing.setEstado(p.getEstado());

        existing.setAlumno1(p.getAlumno1());
        existing.setAlumno2(p.getAlumno2());
        existing.setAlumno3(p.getAlumno3());
        existing.setAlumno4(p.getAlumno4());

        if (p.getFechaRegistro() != null) existing.setFechaRegistro(p.getFechaRegistro());

        protocolo actualizado = protocoloRepository.save(existing);

        // Usamos el guion bajo para Property Traversal de Spring Data JPA
        intermediateRepo.deleteByProtocolo_IdProtocolo(id);

        // Re-asignamos los directores actuales
        asignarDirectores(actualizado, directoresIds);

        return actualizado;
    }

    /**
     * Método auxiliar para insertar en la tabla intermedia protocolo_director
     */
    private void asignarDirectores(protocolo p, List<Integer> ids) {
        if (ids != null && !ids.isEmpty()) {
            for (Integer num : ids) {
                // Buscamos al director por su numeroTrabajador
                director d = directorRepo.findById(num).orElse(null);
                if (d != null) {
                    protocolo_director rel = protocolo_director.builder()
                            .protocolo(p)
                            .director(d)
                            .build();
                    intermediateRepo.save(rel);
                }
            }
        }
    }
}