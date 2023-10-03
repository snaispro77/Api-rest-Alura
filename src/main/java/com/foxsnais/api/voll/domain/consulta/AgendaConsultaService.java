package com.foxsnais.api.voll.domain.consulta;

import com.foxsnais.api.voll.domain.medico.Medico;
import com.foxsnais.api.voll.domain.medico.MedicoRepository;
import com.foxsnais.api.voll.domain.paciente.Paciente;
import com.foxsnais.api.voll.domain.paciente.PacienteRepository;
import com.foxsnais.api.voll.infra.errors.ValidacionIntegridadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendaConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DatosAgendarConsulta datos){
        Optional<Paciente> paciente = pacienteRepository.findById(datos.idPaciente());
        Optional<Medico> medico = medicoRepository.findById(datos.idMedico());

        if(paciente.isEmpty()){
            throw new ValidacionIntegridadException("No se encontro este paciente");
        }
        if(medico.isEmpty()){
            medico = seleccionarMedico(datos);
        }
        
        consultaRepository.save(new Consulta(null,medico.get(),paciente.get(), datos.fecha()));
    }
    private Optional<Medico> seleccionarMedico(DatosAgendarConsulta dac){
        if(dac.idMedico() != null){
            return medicoRepository.findById(dac.idMedico());
        }
        if(dac.especialidad() == null){
            throw new ValidacionIntegridadException("Debe introducir la especialidad del medico");
        }
        return medicoRepository.seleccionarMedicoAleatorio(dac.especialidad(),dac.fecha());
    }
}
