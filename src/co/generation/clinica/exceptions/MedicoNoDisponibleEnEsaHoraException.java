package co.generation.clinica.exceptions;

import java.time.LocalDateTime;

public class MedicoNoDisponibleEnEsaHoraException extends RuntimeException {
    public MedicoNoDisponibleEnEsaHoraException(String nombreMedico, LocalDateTime fechaHora) {
        super("El medico "+nombreMedico+" no se encuentra disponible en la hora :"+fechaHora);
    }
}
