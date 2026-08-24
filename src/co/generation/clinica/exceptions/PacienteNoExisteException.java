package co.generation.clinica.exceptions;

public class PacienteNoExisteException extends RuntimeException {
    public PacienteNoExisteException(String paciente) {
        super("El paciente "+paciente+ " no existe");
    }
}
