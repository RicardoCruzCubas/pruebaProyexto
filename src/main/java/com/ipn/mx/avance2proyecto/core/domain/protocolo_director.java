package com.ipn.mx.avance2proyecto.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // ✅ Importación necesaria
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "protocolo_director", schema = "public")
public class protocolo_director implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro", nullable = false)
    private Long idRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_protocolo", nullable = false)
    // ✅ CORRECCIÓN 1: Cuando serializamos esta relación, ignoramos la lista de directores
    // que está dentro de la clase 'protocolo' para no volver a entrar aquí.
    @JsonIgnoreProperties("directoresAsignados")
    private protocolo protocolo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_trabajador", nullable = false)
    // ✅ CORRECCIÓN 2: Si tu clase 'director' tiene una lista de asignaciones o protocolos,
    // debemos ignorarla aquí para evitar otro posible bucle.
    @JsonIgnoreProperties({"protocolos", "asignaciones", "hibernateLazyInitializer", "handler"})
    private director director;
}