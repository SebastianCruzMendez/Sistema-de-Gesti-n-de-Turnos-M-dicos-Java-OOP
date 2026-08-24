package co.generation.clinica.service;

import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClinicaService implements Consultable {

    private final List<Paciente> pacientes = new ArrayList<>();
    private final List<Medico> medicos = new ArrayList<>();
    private final List<Turno> turnos = new ArrayList<>();

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    @Override
    public List<Turno> listarTurnosDelDia(LocalDate fecha) {
        return List.of();
    }

    @Override
    public List<Turno> buscarPorMedico(Medico medico) {
        return List.of();
    }

    @Override
    public List<Turno> buscarPorPaciente(Paciente paciente) {
        return List.of();
    }

    public void registrarPaciente(Paciente p) {
        if (!p.esValido()) {
            System.out.println("Error: Los datos del paciente no son válidos.");
            return;
        }
        if (pacientes.contains(p)) {
            System.out.println("Error: Ya existe un paciente con la cédula " + p.getCedula());
            return;
        }
        int nuevoId = pacientes.stream()
                .mapToInt(Paciente::getId)
                .max()
                .orElse(0) + 1;
        p.setId(nuevoId);
        pacientes.add(p);
        System.out.println("Paciente registrado con éxito: " + p.getDatosRegistro());
    }

    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        List<Paciente> copia = new ArrayList<>(pacientes);
        copia.sort(Comparator.comparing(Paciente::getApellido)
                .thenComparing(Paciente::getNombre));
        for (Paciente p : copia) {
            System.out.println(p);
        }
    }

    public void registrarMedico(Medico m) {
        if (!m.esValido()) {
            System.out.println("Error: Los datos del médico no son válidos.");
            return;
        }
        if (medicos.contains(m)) {
            System.out.println("Error: Ya existe un médico registrado con ese nombre y apellido.");
            return;
        }
        int nuevoId = medicos.stream()
                .mapToInt(Medico::getId)
                .max()
                .orElse(0) + 1;
        m.setId(nuevoId);
        medicos.add(m);
        System.out.println("Médico registrado con éxito: " + m.getDatosRegistro());
    }

    public Medico buscarPorNombreApellido(String nombre, String apellido) {
        if (nombre == null || apellido == null) {
            return null;
        }
        for (Medico m : medicos) {
            if (m.getNombre().equalsIgnoreCase(nombre.trim()) &&
                    m.getApellido().equalsIgnoreCase(apellido.trim())) {
                return m;
            }
        }
        return null;
    }

    public void listarMedicos() {
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
            return;
        }

        List<Medico> copia = new ArrayList<>(medicos);
        copia.sort(Comparator.comparing(Medico::getEspecialidad)
                .thenComparing(Medico::getApellido, String.CASE_INSENSITIVE_ORDER));

        for (Medico m : copia) {
            System.out.println(m);
        }
    }
}