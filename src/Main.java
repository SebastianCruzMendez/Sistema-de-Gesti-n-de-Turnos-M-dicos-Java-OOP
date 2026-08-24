import co.generation.clinica.datos.DatosCSV;
import co.generation.clinica.model.Especialidad;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.service.ClinicaService;

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
                    String cedula = scan.nextLine();
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
                case 3 :{}
                case 4 :{}
                case 5 :{}
                case 6 :{}
                case 7 :{}
                case 8 :{}
                case 9 :{}
                case 10 :{}
                case 0 :{}
            }
        }
    }
}