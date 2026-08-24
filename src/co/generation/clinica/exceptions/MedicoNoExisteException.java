package co.generation.clinica.exceptions;

public class MedicoNoExisteException extends RuntimeException {
    public MedicoNoExisteException(String medico) {
        super("El medico "+ medico+ " no existe");
    }
}
