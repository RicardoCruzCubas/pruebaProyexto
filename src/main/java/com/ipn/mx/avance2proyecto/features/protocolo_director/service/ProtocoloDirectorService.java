package com.ipn.mx.avance2proyecto.features.protocolo_director.service;

import com.ipn.mx.avance2proyecto.core.domain.protocolo_director;
import java.util.List;

public interface ProtocoloDirectorService {
    List<protocolo_director> findAll();
    protocolo_director findById(Long id);
    protocolo_director create(protocolo_director pd);
    protocolo_director update(Long id, protocolo_director pd);
    void delete(Long id); // <--- ESTE ES EL QUE EL ERROR DICE QUE FALTA
    List<protocolo_director> findByProtocoloId(Long id); // <--- PARA EL FRONTEND
}