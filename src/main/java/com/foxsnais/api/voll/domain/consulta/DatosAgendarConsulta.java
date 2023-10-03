package com.foxsnais.api.voll.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.foxsnais.api.voll.domain.medico.Especialidad;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(
        Long id,

        @NotNull
        @JsonAlias(value = "id_paciente")
        Long idPaciente,

        @NotNull
        @JsonAlias(value = "id_medico")
        Long idMedico,
        @NotNull @Future
        LocalDateTime fecha,
        Especialidad especialidad
        ){
}
