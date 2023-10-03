package com.foxsnais.api.voll.domain.medico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarMedico(@NotNull Long id, String nombre, String email) {
}
