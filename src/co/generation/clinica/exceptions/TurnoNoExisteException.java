package co.generation.clinica.exceptions;

public class TurnoNoExisteException extends RuntimeException {
    public TurnoNoExisteException(String turno ) {
        super("El turno con id "+turno+ " no existe");
    }
}
