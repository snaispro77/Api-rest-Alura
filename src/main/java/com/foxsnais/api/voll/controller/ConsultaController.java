package com.foxsnais.api.voll.controller;

import com.foxsnais.api.voll.domain.consulta.AgendaConsultaService;
import com.foxsnais.api.voll.domain.consulta.DatosAgendarConsulta;
import com.foxsnais.api.voll.domain.consulta.DatosDetalleConsulta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private AgendaConsultaService agendaConsultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleConsulta> agendar(@RequestBody @Valid DatosAgendarConsulta dac){
        agendaConsultaService.agendar(dac);
        return ResponseEntity.ok(new DatosDetalleConsulta(null,null,null,null));
    }
}
