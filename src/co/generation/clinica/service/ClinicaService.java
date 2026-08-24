package co.generation.clinica.service;

import co.generation.clinica.exceptions.MedicoNoDisponibleEnEsaHoraException;
import co.generation.clinica.exceptions.MedicoNoExisteException;
import co.generation.clinica.exceptions.PacienteNoExisteException;
import co.generation.clinica.exceptions.TurnoNoExisteException;
import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.EstadoTurno;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
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
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Turno> turnos;

    @Override
    public List<Turno> listarTurnosDelDia(LocalDate fecha) {
        return List.of();
    }

    public void registrarPaciente(Paciente p) {
        p.esValido();
    }

// ----------------- Camilo---------------------------
    public void asignarTurno(Turno t) {
        //verifica que el paciente del turno exista
        boolean pacienteExiste = pacientes.stream().anyMatch(
                p -> Objects.equals(p.getCedula(), t.getPaciente().getCedula()));
    if (!pacienteExiste){
        throw new PacienteNoExisteException(t.getPaciente().getNombre());
    }
        //verifica que el medico del turno exista
        boolean medicoExiste = medicos.stream().anyMatch(m -> Objects.equals(
                m.getNombre(), t.getMedico().getNombre())
                && Objects.equals(m.getApellido(), t.getMedico().getApellido()));
        if (!medicoExiste){
            throw new MedicoNoExisteException(t.getMedico().getNombre());
        }
        //verificar que no exista ya un turno con el mismo medico en la misma hora
        if (turnos.contains(t)) {
            throw new MedicoNoDisponibleEnEsaHoraException(t.getMedico().getNombre(), t.getFechaHora());
        }
        //asignar id
        t.setId(ThreadLocalRandom.current().nextInt(1, 1000));
        //agregar a la lista
        turnos.add(t);

    }

    public void cancelarTurno(int idTurno){
        // buscar turno por id
        Turno turno = turnos.stream().filter(t -> t.getId() == idTurno)
                .findFirst()
                .orElseThrow(() -> new TurnoNoExisteException(String.valueOf(idTurno)));
        // existe pero el estado es ATENDIDO o CANCELADO, no se uede atender
        if (turno.getEstado().equals(EstadoTurno.ATENDIDO) || turno.getEstado().equals(EstadoTurno.CANCELADO) ){
            System.out.println("No se puede Cancelar");
        }else{
            turno.setEstado(EstadoTurno.CANCELADO);
        }
        System.out.println("CONFIRMACION");
    }


// ------------------------ fin Camilo --------------
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
    public List<Paciente> getPacientes() {

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

    // fin









    public List<String> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }
}
