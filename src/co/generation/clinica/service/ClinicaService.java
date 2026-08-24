package co.generation.clinica.service;

import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class ClinicaService implements Consultable {
    private final List<Paciente> pacientes = new ArrayList<>();
    private final List<Medico> medicos = new ArrayList<>();
    private final List<Turno> turnos = new ArrayList<>();


    @Override
    public List<Turno> listarTurnosDelDia(LocalDate fecha) {
        return List.of();
    }

    public void registrarPaciente(Paciente p) {
    p.esValido();
    }

    @Override
    public List<Turno> buscarPorMedico(Medico medico) {
        return List.of();
    }

    @Override
    public List<Turno> buscarPorPaciente(Paciente paciente) {
        return List.of();
    }


    // --- MÉTODOS DE MÉDICO ---
    public Medico buscarPorNombreApellido(String nombre, String apellido) {
        if (nombre == null || apellido == null) {
            return null;
        }
        for (Object obj : medicos) {
            if (obj instanceof Medico) {
                Medico m = (Medico) obj;
                if (m.getNombre().equalsIgnoreCase(nombre.trim()) &&
                        m.getApellido().equalsIgnoreCase(apellido.trim())) {
                    return m;
                }
            }
        }
        return null;
    }

    public void listarMedicos() {
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
            return;
        }

        List<Medico> copia = new ArrayList<>();
        for (Object obj : medicos) {
            if (obj instanceof Medico) {
                copia.add((Medico) obj);
            }
        }

        copia.sort(Comparator.comparing(Medico::getEspecialidad)
                .thenComparing(Medico::getApellido, String.CASE_INSENSITIVE_ORDER));
        for (Medico m : copia) {
            System.out.println(m);
        }

    // fin









    public List<String> getPacientes() {
        return pacientes;
    }

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
    public List<Paciente> getPacientes() { return pacientes; }
    public List<Medico> getMedicos() { return medicos; }
    public List<Turno> getTurnos() { return turnos; }

}
