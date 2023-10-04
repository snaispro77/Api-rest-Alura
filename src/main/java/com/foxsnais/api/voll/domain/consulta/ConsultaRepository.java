package com.foxsnais.api.voll.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Long> {

    @Query("""
    select exists (select 1 from Consulta as c where c.paciente.id =:idPaciente
    and (c.fecha between :primerHorario and :ultimoHorario))
    """)
    boolean existeUnMedicoEntre2Horarios(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndFecha(Long aLong, LocalDateTime fecha);
}
