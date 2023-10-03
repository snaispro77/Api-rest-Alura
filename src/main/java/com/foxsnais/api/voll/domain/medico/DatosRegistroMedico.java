package com.foxsnais.api.voll.domain.medico;

import com.foxsnais.api.voll.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record DatosRegistroMedico(
        @NotBlank(message = "{nombre.obligatorio}")
        String nombre,
        //tambien existen las validaciones para email @Email
        @NotBlank
        String estado_civil,
        @NotNull
        //@Pattern(regexp = "\\d{2}")
        @Min(value = 15, message = "El valor mínimo permitido es 15")
        @Max(value = 80, message = "El valor máximo permitido es 80")
        Integer edad,
        @NotBlank
        @Email
        String email,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion direccion) {
}
