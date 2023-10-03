package com.foxsnais.api.voll.domain.paciente;

import jakarta.validation.constraints.NotNull;
import com.foxsnais.api.voll.domain.direccion.DatosDireccion;

public record DatosActualizacionPaciente(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion) {
}
