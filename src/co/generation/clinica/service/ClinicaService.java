package co.generation.clinica.service;

import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;

import java.time.LocalDate;
import java.util.List;

public class ClinicaService implements Consultable {
    private List<String> pacientes;
    private List<String> medicos;
    private List<String> turnos;

    @Override
    public List<Turno> listarTurnosDelDia(LocalDate fecha) {
        return List.of();
    }

    public void registrarPaciente(Paciente p) {
    p.esValido()
    }

    @Override
    public List<Turno> buscarPorMedico(Medico medico) {
        return List.of();
    }

    @Override
    public List<Turno> buscarPorPaciente(Paciente paciente) {
        return List.of();
    }

    public List<String> getPacientes() {
        return pacientes;
    }

    public List<String> getMedicos() {
        return medicos;
    }

    public List<String> getTurnos() {
        return turnos;
    }
}
