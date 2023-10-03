package com.foxsnais.api.voll.domain.medico;

public record DatosListadoMedico(Long id, String nombre, Especialidad especialidad, String email, Integer edad) {
    public DatosListadoMedico(Medico m) {
        this(m.getId(), m.getNombre(),m.getEspecialidad(), m.getEmail(), m.getEdad());
    }

}
