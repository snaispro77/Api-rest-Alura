package com.foxsnais.api.voll.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable p);

    @Query("""
        select m from Medico as m where
        m.activo = true and m.especialidad =:especialidad
        and m.id not in(
            select c.medico.id from Consulta as c
            where c.fecha =:fecha
        )
        order by rand()
        limit 1
    """)
    Optional<Medico> seleccionarMedicoAleatorio(Especialidad especialidad, LocalDateTime fecha);
}
