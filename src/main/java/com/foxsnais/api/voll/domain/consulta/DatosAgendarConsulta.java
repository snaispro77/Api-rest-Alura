package com.foxsnais.api.voll.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.foxsnais.api.voll.domain.medico.Especialidad;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(
        Long id,

        @NotNull
        @JsonAlias(value = "id_paciente")
        Long idPaciente,

        @JsonAlias(value = "id_medico")
        Long idMedico,
        @NotNull @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fecha,
        Especialidad especialidad
        ){
}
