package com.foxsnais.api.voll.controller;

import com.foxsnais.api.voll.domain.direccion.DatosDireccion;
import com.foxsnais.api.voll.domain.medico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                                                UriComponentsBuilder uriComponentsBuilder){
        Medico medico = this.medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(
                medico.getId(), medico.getNombre(),
                medico.getEstadoCivil(),medico.getEdad(),medico.getEmail(),
                medico.getEspecialidad(), new DatosDireccion(medico.getDireccion().getCalle(),
                medico.getDireccion().getNumero(),medico.getDireccion().getHospital(),
                medico.getDireccion().getCiudad(), medico.getDireccion().getCodigo_postal())
        );
        URI uri = uriComponentsBuilder.path("/medicos/{1}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(datosRespuestaMedico);
    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 2) Pageable p){
        //return medicoRepository.findAll(p).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(p).map(DatosListadoMedico::new));
    }
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@Valid @RequestBody DatosActualizarMedico dam){
        Medico medicoActualizado = medicoRepository.getReferenceById(dam.id());
        medicoActualizado.actualizarDatos(dam);
        return ResponseEntity.ok(new DatosRespuestaMedico(medicoActualizado.getId(), medicoActualizado.getNombre(),
                medicoActualizado.getEstadoCivil(),medicoActualizado.getEdad(),medicoActualizado.getEmail(),
                medicoActualizado.getEspecialidad(), new DatosDireccion(medicoActualizado.getDireccion().getCalle(),
                medicoActualizado.getDireccion().getNumero(),medicoActualizado.getDireccion().getHospital(),
                medicoActualizado.getDireccion().getCiudad(), medicoActualizado.getDireccion().getCodigo_postal())));
    }
    /*@DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){ //tambien se puede usar una clase DTO
        medicoRepository.delete(medicoRepository.getReferenceById(id));
    }*/
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id){ //también se puede usar una clase DTO
        medicoRepository.getReferenceById(id).desactivarMedico();
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> retornaDatosMedico(@PathVariable Long id){ //también se puede usar una clase DTO
        Medico medico = medicoRepository.getReferenceById(id);

        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(),
                medico.getEstadoCivil(),medico.getEdad(),medico.getEmail(),
                medico.getEspecialidad(), new DatosDireccion(medico.getDireccion().getCalle(),
                medico.getDireccion().getNumero(),medico.getDireccion().getHospital(),
                medico.getDireccion().getCiudad(), medico.getDireccion().getCodigo_postal()));
        return ResponseEntity.ok(datosRespuestaMedico);
    }

}
