import co.generation.clinica.datos.DatosCSV;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;
import co.generation.clinica.service.ClinicaService;

import java.time.LocalDate;
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

            int opcionUsuario;
            System.out.println("""
                    ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                    ■ CLINICAAPP — MENÚ                 ■
                    ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
                    ■ 1. Registrar paciente             ■
                    ■ 2. Registrar médico               ■
                    ■ 3. Asignar turn                  ■
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
                    String cedula = scan.nextLine();
                    System.out.println("Ingresa tu telefono");
                    String telefono = scan.nextLine();
                    Paciente paciente = new Paciente(0, nombre, apellido, cedula, telefono);
                    servicio.registrarPaciente(paciente);
                }
                case 2 : {
                    boolean opcionEspecialidadWhile = true;
                    int opcionEspecialidad;
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
                        if (opcionEspecialidad <= 5 && opcionEspecialidad >= 1) {
                            System.out.println("Por favor ingrese una opcion valida");
                        }else {
                            opcionEspecialidadWhile = false;
                        }
                    }while (opcionEspecialidadWhile);
                    switch (opcionEspecialidad){
                        case 1 ->
                    }
                    Medico medico = new Medico(0, nombre, apellido, );
                    servicio.registrarPaciente(paciente);
                }
                case 3 :{}
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
                case 10 :{}
                case 0 :{}
            }
        }
    }
}