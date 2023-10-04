package com.foxsnais.api.voll.domain.consulta;

import com.foxsnais.api.voll.domain.consulta.validaciones.ValidadorConsultas;
import com.foxsnais.api.voll.domain.medico.Medico;
import com.foxsnais.api.voll.domain.medico.MedicoRepository;
import com.foxsnais.api.voll.domain.paciente.Paciente;
import com.foxsnais.api.voll.domain.paciente.PacienteRepository;
import com.foxsnais.api.voll.infra.errors.ValidacionIntegridadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired//principio de patron de diseño strategy, en esta lista se insertan todas las clases que impl VC
    private List<ValidadorConsultas> validadorConsultas;

    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos){

        Optional<Paciente> paciente = pacienteRepository.findById(datos.idPaciente());

        if(datos.idMedico() == null && datos.especialidad() == null){
            throw new ValidacionIntegridadException("El id del medico no debe ser nulo");
        }
        assert datos.idMedico() != null;
        Optional<Medico> medico = medicoRepository.findById(datos.idMedico());

        if(paciente.isEmpty()){
            throw new ValidacionIntegridadException("No se encontro este paciente");
        }
        if(medico.isEmpty()){
            medico = seleccionarMedico(datos);
            if(medico.isEmpty()) throw new ValidacionIntegridadException("No se encontro un medico disponible");
        }


        validadorConsultas.forEach(validator -> validator.validar(datos));


        Consulta consulta = consultaRepository.save(new Consulta(null,medico.get(),paciente.get(), datos.fecha()));
        return new DatosDetalleConsulta(consulta);
    }
    private Optional<Medico> seleccionarMedico(DatosAgendarConsulta dac){
        if(dac.especialidad() == null){
            throw new ValidacionIntegridadException("Debe introducir la especialidad del medico");
        }
        return medicoRepository.seleccionarMedicoAleatorio(dac.especialidad(),dac.fecha());
    }
}
