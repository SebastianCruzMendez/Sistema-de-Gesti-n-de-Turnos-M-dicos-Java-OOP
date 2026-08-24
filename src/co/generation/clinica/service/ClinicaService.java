package co.generation.clinica.service;

import co.generation.clinica.model.Paciente;
import java.util.ArrayList;
import java.util.List;

public class ClinicaService {

    private List<Paciente> pacientes = new ArrayList<>();

    // Getter necesario para que DatosCSV pueda leer la lista
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    // 1. registrarPaciente
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
}