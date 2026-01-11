package com.ipn.mx.avance2proyecto.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "alumnos", schema = "public")
public class alumnos implements Serializable {

    @Id
    @Column(name = "numero_boleta", nullable = false)
    private Long numeroBoleta;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Column(name = "nombre", length = 50,  nullable = false)
    private String nombre;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 2, max = 50, message = "El apellido paterno debe tener entre 2 y 50 caracteres")
    @Column(name = "apellido_paterno", length = 50,  nullable = false)
    private String apellidoPaterno;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 2, max = 50, message = "El apellido materno debe tener entre 2 y 50 caracteres")
    @Column(name = "apellido_materno", length = 50,  nullable = false)
    private String apellidoMaterno;

    @NotBlank(message = "No puede estar en blanco")
    @Column(name = "semestre", length = 50,  nullable = false)
    private String semestre;

    @NotBlank(message = "No puede estar en blanco")
    @Column(name = "carrera", length = 50,  nullable = false)
    private String carrera;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 10, max = 15, message = "El teléfono debe tener entre 10 y 15 caracteres")
    @Column(name = "numero_telefonico", length = 15, nullable = false)
    private String numeroTelefonico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private usuarios usuario;

    //NUEVA RELACIÓN OPCIONAL CON PROTOCOLOS
    // Se establece nullable = true para que el alumno pueda no tener protocolo asignado aún
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_protocolo", nullable = true)
    private protocolo protocolo;
}