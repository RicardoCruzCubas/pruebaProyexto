package com.ipn.mx.avance2proyecto.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // ✅ Importación necesaria
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "protocolo", schema = "public")
public class protocolo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_protocolo", nullable = false)
    private Long idProtocolo;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 10, max = 300, message = "El valor deberá estar entre 10 y 300")
    @Column(name = "titulo", length = 300,  nullable = false)
    private String titulo;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 10, max = 300, message = "El valor deberá estar entre 10 y 300")
    @Column(name = "descripcion", length = 300,  nullable = false)
    private String descripcion;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 10, max = 300, message = "El valor deberá estar entre 10 y 300")
    @Column(name = "objetivos", length = 300,  nullable = false)
    private String objetivos;

    @NotBlank(message = "No puede estar en blanco")
    @Size(min = 10, max = 300, message = "El valor deberá estar entre 10 y 300")
    @Column(name = "perfil_requerido", length = 300,  nullable = false)
    private String perfilRequerido;

    @NotBlank(message = "No puede estar en blanco")
    @Column(name = "estado", length = 20,  nullable = false)
    private String estado;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_registro", nullable = false)
    private Date fechaRegistro;

    // --- RELACIONES CON ALUMNOS ---
    // (Se recomienda Lazy para evitar cargar objetos pesados innecesariamente)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno1_boleta", nullable = true)
    private alumnos alumno1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno2_boleta", nullable = true)
    private alumnos alumno2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno3_boleta", nullable = true)
    private alumnos alumno3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno4_boleta", nullable = true)
    private alumnos alumno4;

    // --- RELACIÓN CON DIRECTORES (VÍA TABLA INTERMEDIA) ---

    @OneToMany(mappedBy = "protocolo", cascade = CascadeType.ALL, orphanRemoval = true)
    // ✅ CORRECCIÓN: Evita que Jackson entre en un bucle infinito al serializar
    // Le decimos: "No vuelvas a mapear la propiedad 'protocolo' que está dentro de protocolo_director"
    @JsonIgnoreProperties("protocolo")
    private List<protocolo_director> directoresAsignados;

    @PrePersist
    protected void onCreate() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = new Date();
        }
    }
}