package com.ipn.mx.avance2proyecto.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "director", schema = "public") // Coincide con tu captura
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class director implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ⚠️ CAMBIO: de "numeroTrabajador" a "numero_trabajador"
    @Column(name = "numero_trabajador", nullable = false)
    private Integer numeroTrabajador;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 3, max = 15)
    @Column(name = "nombre", length = 15,  nullable = false)
    private String nombre;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 2, max = 15)
    // ⚠️ CAMBIO: de "apellidoPaterno" a "apellido_paterno"
    @Column(name = "apellido_paterno", length = 15,  nullable = false)
    private String apellidoPaterno;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 2, max = 15)
    // ⚠️ CAMBIO: de "apellidoMaterno" a "apellido_materno"
    @Column(name = "apellido_materno", length = 15,  nullable = false)
    private String apellidoMaterno;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 3, max = 50)
    // ⚠️ CAMBIO: de "escuelaPerteneciente" a "escuela_perteneciente"
    @Column(name = "escuela_perteneciente", length = 50,  nullable = false)
    private String escuelaPerteneciente;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 3, max = 50)
    @Column(name = "cargo", length = 50,  nullable = false)
    private String cargo;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 10, max = 15)
    // ⚠️ CAMBIO: de "numeroTelefonico" a "numero_telefonico"
    @Column(name = "numero_telefonico", length = 15, nullable = false)
    private String numeroTelefonico;

    @ManyToOne(fetch = FetchType.LAZY)
    // ⚠️ CAMBIO: de "idDepartamento" a "id_departamento"
    @JoinColumn(name = "id_departamento", nullable = false)
    @JsonIgnoreProperties("directores")
    private departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    // ⚠️ CAMBIO: de "idUsuario" a "id_usuario"
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"password", "roles", "handler", "hibernateLazyInitializer"})
    private usuarios usuario;
}