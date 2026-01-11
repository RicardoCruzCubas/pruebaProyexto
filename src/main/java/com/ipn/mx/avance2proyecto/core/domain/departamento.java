package com.ipn.mx.avance2proyecto.core.domain;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "departamento", schema = "public")

public class departamento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDepartamento", nullable = false)
    private Integer idDepartamento;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 3, max = 100, message = "El valor debera estar entre 3 y 100")
    @Column(name = "nombreDepartamento", length = 15,  nullable = false)
    private String nombreDepartamento;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 2, max = 100, message = "El valor debera estar entre 2 y 100")
    @Column(name = "nombreAComletoJefe", length = 15,  nullable = false)
    private String nombreComletoJefe;
}
