package com.ipn.mx.avance2proyecto.features.protocolo.service;

import com.ipn.mx.avance2proyecto.core.domain.protocolo;
import java.util.List;

public interface ProtocoloService {
    List<protocolo> findAll();
    protocolo findById(Long id);
    void delete(Long id);

    // Estas firmas deben ser idénticas en la implementación
    protocolo crearConDirectores(protocolo p, List<Integer> directoresIds);
    protocolo actualizarConDirectores(Long id, protocolo p, List<Integer> directoresIds);
}