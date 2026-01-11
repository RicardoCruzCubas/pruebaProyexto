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
@Table(name = "usuarios", schema = "public")
public class usuarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ✅ Ajustado a snake_case para coincidir con la base de datos
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 10, max = 50, message = "El valor deberá estar entre 10 y 50")
    @Column(name = "email", length = 50,  nullable = false)
    private String email;

    @NotBlank(message = "No puede estar en blanco")
    //Cambiado a min = 8 para que acepte contraseñas estándar y no truene el sistema
    @Size(min = 8, max = 15, message = "El valor deberá estar entre 8 y 15")
    @Column(name = "contrasena", length = 15,  nullable = false)
    private String contrasena;

    @NotBlank(message = "No puede estar en blanco")
    @Column(name = "rol", length = 50,  nullable = false)
    private String rol;

    @Temporal(TemporalType.DATE)
    // ✅ Ajustado a snake_case para coincidir con la base de datos
    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    // ✅ Método para asignar la fecha automáticamente antes de guardar si viene nula
    @PrePersist
    protected void onCreate() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = new Date();
        }
    }
}