package com.ipn.mx.avance2proyecto.features.protocolo_director.service;

import com.ipn.mx.avance2proyecto.core.domain.protocolo_director;
import com.ipn.mx.avance2proyecto.features.protocolo_director.repository.ProtocoloDirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProtocoloDirectorServiceImpl implements ProtocoloDirectorService {

    private final ProtocoloDirectorRepository protocoloDirectorRepository;

    @Override
    public List<protocolo_director> findAll() {
        return protocoloDirectorRepository.findAll();
    }

    @Override
    public protocolo_director findById(Long id) {
        return protocoloDirectorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro no encontrado"));
    }

    @Override
    public protocolo_director create(protocolo_director pd) {
        return protocoloDirectorRepository.save(pd);
    }

    @Override
    public protocolo_director update(Long id, protocolo_director pd) {
        if (!protocoloDirectorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede actualizar");
        }
        pd.setIdRegistro(id);
        return protocoloDirectorRepository.save(pd);
    }

    @Override // ✅ ESTE ES EL MÉTODO QUE TE PIDE EL ERROR
    public void delete(Long id) {
        if (!protocoloDirectorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe");
        }
        protocoloDirectorRepository.deleteById(id);
    }

    @Override // ✅ ESTE ES EL QUE NECESITA EL CONTROLLER
    public List<protocolo_director> findByProtocoloId(Long id) {
        return protocoloDirectorRepository.findByProtocolo_IdProtocolo(id);
    }
}