package com.ipn.mx.avance2proyecto.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "Administrador", schema = "public")

public class administrador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdmin", nullable = false)
    private Long idAdmin;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 8, max = 50, message = "El valor debe de estar dentro del rango de 8 a 50 carcateres")
    @Column(name = "contrasena", length = 100, nullable = false)
    private String contrasena;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 8, max = 50, message = "El valor debe de estar dentro del rango de 8 a 50 carcateres")
    @Column(name = "nombreCompleto", length = 100, nullable = false)
    private String nombreCompleto;

}
