package com.ipn.mx.avance2proyecto.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

@Table(name = "asinacion", schema = "public")

public class asignacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAsistente", nullable = false)
    private Integer idAsistente;

    @NotBlank(message = "No puede estar en blanco")
    @Column(name = "numeroBoleta", length = 50,  nullable = false)
    private int numeroBoleta;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaAsignacion", nullable = false)
    private Date fechaAsignacion;

    @NotBlank(message = "No puede estar en blanco")
    @Column(name = "estado", length = 50,  nullable = false)
    private int estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private protocolo protocolo_id;
}
