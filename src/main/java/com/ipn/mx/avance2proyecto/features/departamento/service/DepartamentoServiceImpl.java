package com.ipn.mx.avance2proyecto.features.departamento.service;

import com.ipn.mx.avance2proyecto.core.domain.departamento;
import com.ipn.mx.avance2proyecto.features.departamento.repository.DepartamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    @Override
    public List<departamento> findAll() {
        return departamentoRepository.findAll();
    }

    @Override
    public departamento findById(Integer id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado"));
    }

    @Override
    public departamento create(departamento dept) {
        return departamentoRepository.save(dept);
    }

    @Override
    public departamento update(Integer id, departamento dept) {
        departamento existing = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado"));

        existing.setNombreDepartamento(dept.getNombreDepartamento());
        existing.setNombreComletoJefe(dept.getNombreComletoJefe());

        return departamentoRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        if (!departamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado");
        }
        departamentoRepository.deleteById(id);
    }
}
