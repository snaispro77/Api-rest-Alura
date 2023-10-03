package com.foxsnais.api.voll.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String calle;
    private String numero;
    private String hospital;
    private String ciudad;
    private String codigo_postal;
    public Direccion(DatosDireccion dd){
        this.calle = dd.calle();
        this.numero = dd.numero();
        this.hospital = dd.hospital();
        this.ciudad = dd.ciudad();
        this.codigo_postal = dd.codigo_postal();
    }

    public void actualizarDireccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.hospital = direccion.hospital();
        this.ciudad = direccion.ciudad();
        this.codigo_postal = direccion.codigo_postal();
    }
}
