package com.foxsnais.api.voll.domain.medico;

import com.foxsnais.api.voll.domain.direccion.Direccion;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String estadoCivil;
    private Integer edad;
    private String email;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;
    public Medico(DatosRegistroMedico drm){
        this.nombre = drm.nombre();
        this.estadoCivil = drm.estado_civil();
        this.edad = drm.edad();
        this.email = drm.email();
        this.especialidad = drm.especialidad();
        this.direccion = new Direccion(drm.direccion());
        this.activo = true;
    }
    public void actualizarDatos(DatosActualizarMedico dam){
        this.nombre = dam.nombre();
        this.email = dam.email();
    }
    public void desactivarMedico(){
        this.activo = false;
    }


}
