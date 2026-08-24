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


    @Override
    public List<Turno> listarTurnosDelDia(LocalDate fecha) {
        return List.of();
    }

    //----------Andres -------------------------
    public void registrarPaciente(Paciente p) {
        // Step 1: Llama a p.esValido() — si retorna false, imprime error y sale
        if (p == null || !p.esValido()) {
            System.out.println("Error: Los datos del paciente no son válidos.");
            return;
        }

        // Step 2: Verifica que no exista otro paciente con la misma cédula (usa contains())
        if (pacientes.contains(p)) {
            System.out.println("Error: Ya existe un paciente registrado con la cédula " + p.getCedula());
            return;
        }

        // Step 3: Asigna el id (máximo id + 1, o 1 si la lista está vacía)
        int maxId = 0;
        for (Paciente paciente : pacientes) {
            if (paciente.getId() > maxId) {
                maxId = paciente.getId();
            }
        }
        p.setId(maxId + 1);

        // Step 4: Agrega p a la lista
        pacientes.add(p);

        // Step 5: Imprime mensaje de éxito con los datos del paciente
        System.out.println("Paciente registrado exitosamente: " + p.getDatosRegistro());
    }

    // 2. buscarPorCedula
    public Paciente buscarPorCedula(String cedula) {
        if (cedula == null) return null;

        // Recorre la lista con un for. Retorna el Paciente cuya cédula coincida exactamente.
        for (Paciente p : pacientes) {
            if (p.getCedula().equals(cedula.trim())) {
                return p;
            }
        }

        // Retorna null si no encuentra ninguno. No imprime nada.
        return null;
    }

    // --------Fin Andres ----------------
    // ----------------- Camilo---------------------------
    public void asignarTurno(Turno t) {
        //verifica que el paciente del turno exista
        boolean pacienteExiste = pacientes.stream().anyMatch(p -> Objects.equals(p.getCedula(), t.getPaciente().getCedula()));
        if (!pacienteExiste) {
            throw new PacienteNoExisteException(t.getPaciente().getNombre());
        }
        //verifica que el medico del turno exista
        boolean medicoExiste = medicos.stream().anyMatch(m -> Objects.equals(m.getNombre(), t.getMedico().getNombre()) && Objects.equals(m.getApellido(), t.getMedico().getApellido()));
        if (!medicoExiste) {
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

    public void cancelarTurno(int idTurno) {
        // buscar turno por id
        Turno turno = turnos.stream().filter(t -> t.getId() == idTurno).findFirst().orElseThrow(() -> new TurnoNoExisteException(String.valueOf(idTurno)));
        // existe pero el estado es ATENDIDO o CANCELADO, no se uede atender
        if (turno.getEstado().equals(EstadoTurno.ATENDIDO) || turno.getEstado().equals(EstadoTurno.CANCELADO)) {
            System.out.println("No se puede Cancelar");
        } else {
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


    // --- MÉTODOS DE MÉDICO Brayan---
    public Medico buscarPorNombreApellido(String nombre, String apellido) {
        if (nombre == null || apellido == null) {
            return null;
        }

        for (Medico obj : medicos) {
            if (Objects.equals(obj.getNombre(), nombre) && Objects.equals(obj.getApellido(), apellido)) {

                if (obj.getNombre().equalsIgnoreCase(nombre.trim()) && obj.getApellido().equalsIgnoreCase(apellido.trim())) {
                    return obj;
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

        copia.sort(Comparator.comparing(Medico::getEspecialidad).thenComparing(Medico::getApellido, String.CASE_INSENSITIVE_ORDER));
        for (Medico m : copia) {
            System.out.println(m);
        }

    }

    // fin---------------- Brayan ---------------------
    // inicio sebastian_______________________________________
    // --- MÉTODO 1: listarPacientes() ---
    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        // Crear copia para no alterar la lista original al ordenar
        List<Paciente> copia = new ArrayList<>(pacientes);

        // Ordenar por apellido y luego por nombre usando Comparator
        copia.sort(Comparator.comparing(Paciente::getApellido)
                .thenComparing(Paciente::getNombre));

        // Imprimir cada paciente con su toString()
        for (Paciente p : copia) {
            System.out.println(p);
        }
    }

    // --- MÉTODO 2: registrarMedico(Medico m) ---
    public void registrarMedico(Medico m) {
        // 1. Validar campos obligatorios del médico
        if (!m.esValido()) {
            System.out.println("Error: Los datos del médico no son válidos.");
            return;
        }

        // 2. Verificar duplicados (contains invoca al equals() por nombre y apellido)
        if (medicos.contains(m)) {
            System.out.println("Error: Ya existe un médico registrado con ese nombre y apellido.");
            return;
        }

        // 3. Asignar ID incremental (máximo ID actual + 1, o 1 si la lista está vacía)
        int nuevoId = medicos.stream()
                .mapToInt(Medico::getId)
                .max()
                .orElse(0) + 1;
        m.setId(nuevoId);


        // 4. Agregar a la lista e imprimir mensaje de éxito
        medicos.add(m);
        System.out.println("Médico registrado con éxito: " + m.getDatosRegistro());
    }

    //Final sebastian________________________________________________________________________
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }
}
