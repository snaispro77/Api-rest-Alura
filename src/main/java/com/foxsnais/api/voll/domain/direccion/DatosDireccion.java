package com.foxsnais.api.voll.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String numero,
        @NotBlank
        String hospital,
        @NotBlank
        String ciudad,
        @NotBlank
        String codigo_postal) {

}
