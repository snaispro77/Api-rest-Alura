package com.foxsnais.api.voll.domain.medico;

import com.foxsnais.api.voll.domain.direccion.DatosDireccion;

public record DatosRespuestaMedico(Long id, String nombre, String estado_civil,
                                   Integer edad, String email, Especialidad especialidad,
                                   DatosDireccion direccion) {
}
