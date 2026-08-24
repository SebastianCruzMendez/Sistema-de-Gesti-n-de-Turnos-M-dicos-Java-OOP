import co.generation.clinica.datos.DatosCSV;
import co.generation.clinica.model.*;
import co.generation.clinica.service.ClinicaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ClinicaService servicio = new ClinicaService();
        DatosCSV.cargar(servicio);

        boolean opcion = true;

        while(opcion ){
            String nombre;
            String apellido;
            String cedula;

            int opcionUsuario;
            System.out.println("""
                    ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                    ■ CLINICAAPP — MENÚ                 ■
                    ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                    ■ 1. Registrar paciente             ■
                    ■ 2. Registrar médico               ■
                    ■ 3. Asignar turno                  ■
                    ■ 4. Listar turnos del día          ■
                    ■ 5. Cancelar turno                 ■
                    ■ 6. Ver turnos por médico          ■
                    ■ 7. Ver turnos por paciente        ■
                    ■ 8. Cambiar estado de turno        ■
                    ■ 9. Listar pacientes               ■
                    ■ 10. Listar médicos                ■
                    ■ 0. Salir                          ■
                    ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                    
                    """);

            opcionUsuario = scan.nextInt();

            switch (opcionUsuario){
                case 1 :{
                    System.out.println("Por favor ingresa los siguientes datos");
                    System.out.println("Ingresa tu Nombre");
                    nombre = scan.nextLine();
                    System.out.println("Ingresa tu Apellido");
                    apellido = scan.nextLine();
                    System.out.println("Ingresa tu cedula");
                    cedula = scan.nextLine();
                    System.out.println("Ingresa tu telefono");
                    String telefono = scan.nextLine();
                    Paciente paciente = new Paciente(0, nombre, apellido, cedula, telefono);
                    servicio.registrarPaciente(paciente);
                }
                case 2 : {
                    boolean opcionEspecialidadWhile = true;
                    int opcionEspecialidad;
                    Especialidad especialidad = null;

                    System.out.println("Por favor ingresa los siguientes datos");
                    System.out.println("Ingresa tu Nombre");
                    nombre = scan.nextLine();
                    System.out.println("Ingresa tu Apellido");
                    apellido = scan.nextLine();
                    System.out.println("Elige tu especialidad");
                    do {
                        System.out.println("""
                                ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                                ■       ESPECIALIDAD                ■
                                ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                                ■ 1. GENERAL                        ■
                                ■ 2. PEDIATRA                       ■
                                ■ 3. CARDIOOGIA                     ■
                                ■ 4. URGENCIAS                      ■
                                ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                                
                                """);
                        opcionEspecialidad = scan.nextInt();
                        switch (opcionEspecialidad){
                            case 1 -> {
                                especialidad = Especialidad.GENERAL;
                                opcionEspecialidadWhile = false;
                            }
                            case 2 -> {
                                especialidad = Especialidad.PEDIATRIA;
                                opcionEspecialidadWhile = false;
                            }
                            case 3 -> {
                                especialidad = Especialidad.CARDIOLOGIA;
                                opcionEspecialidadWhile = false;
                            }
                            case 4 -> {
                                especialidad = Especialidad.URGENCIAS;
                                opcionEspecialidadWhile = false;
                            }
                            default -> System.out.println("Por favor ingrese una opcion valida");
                        }
                    }while (opcionEspecialidadWhile);


                    Medico medico = new Medico(0, nombre, apellido, especialidad);
                    servicio.registrarMedico(medico);
                }
                case 3 :{
                    //Asignar turno
                    System.out.println("Por favor ingresa los siguientes datos");
                    System.out.println("Ingresa  cedula del paciente");
                    cedula = scan.nextLine();
                    System.out.println("Ingresa Nombre del Medico");
                    nombre = scan.nextLine();
                    System.out.println("Ingresa Apellido del Medico");
                    apellido = scan.nextLine();
                    System.out.println("Ingresa el año");
                    int anio = scan.nextInt();
                    System.out.println("Ingresa el mes");
                    int mes = scan.nextInt();
                    System.out.println("Ingresa el dia");
                    int dia = scan.nextInt();
                    System.out.println("Ingresa la hora");
                    int hora = scan.nextInt();
                    System.out.println("Ingresa el minuto");
                    int minuto = scan.nextInt();
                    LocalDateTime fecha = LocalDateTime.of(anio, mes, dia, hora, minuto) ;
                    Paciente paciente = servicio.buscarPorCedula(cedula);
                    Medico medico = servicio.buscarPorNombreApellido(nombre, apellido);

                    Turno turno = new Turno(0, paciente, medico, fecha,EstadoTurno.PENDIENTE );
                    servicio.asignarTurno(turno);
                }

                case 4 : {
                    System.out.print("Año (YYYY): ");
                    int a = Integer.parseInt(scan.nextLine());
                    System.out.print("Mes (1-12): ");
                    int m = Integer.parseInt(scan.nextLine());
                    System.out.print("Día (1-31): ");
                    int d = Integer.parseInt(scan.nextLine());
                    List<Turno> delDia = servicio.listarTurnosDelDia(LocalDate.of(a, m, d));
                    if (delDia.isEmpty()) {
                        System.out.println("No hay turnos registrados para ese día.");
                    } else {
                        delDia.forEach(System.out::println);
                    }
                    break;
                }
                case 5 : {
                    System.out.println("\n--- CANCELAR TURNO ---");
                    System.out.print("Ingrese el ID del turno a cancelar: ");
                    try {
                        int idTurno = Integer.parseInt(scan.nextLine().trim());
                        servicio.cancelarTurno(idTurno);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Debe ingresar un ID numérico entero.");
                    } catch (Exception e) {
                        System.out.println("Error al cancelar el turno: " + e.getMessage());
                    }
                }
                case 6 : {
                    System.out.println("\n--- VER TURNOS POR MÉDICO ---");
                    System.out.print("Nombre del médico: ");
                    nombre = scan.nextLine().trim();
                    System.out.print("Apellido del médico: ");
                    apellido = scan.nextLine().trim();

                    Medico medicoEncontrado = servicio.buscarPorNombreApellido(nombre, apellido);

                    if (medicoEncontrado == null) {
                        System.out.println("Error: No se encontró ningún médico con el nombre " + nombre + " " + apellido);
                    } else {
                        List<Turno> turnosMedico = servicio.buscarPorMedico(medicoEncontrado);
                        if (turnosMedico.isEmpty()) {
                            System.out.println("El medico " + medicoEncontrado.getNombre() + " " + medicoEncontrado.getApellido() + " no tiene turnos asignados.");
                        } else {
                            System.out.println("\nTurnos encontrados para el médico " + medicoEncontrado + ":");
                            turnosMedico.forEach(System.out::println);
                        }
                    }}
                case 7 :{}
                case 8 :{}
                case 9 :{}
                case 10 :{servicio.listarPacientes();}
                case 0 :{
                    System.out.println("""
                                ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                                ■          HASTA PRONTO                ■
                                ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                                    ■■■■■■■ DATOS GUARDADOS ■■■■■■■■
                                ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                                
                                """);
                    DatosCSV.guardar(servicio);
                    opcion = false;
                }
            }
        }
    }
}