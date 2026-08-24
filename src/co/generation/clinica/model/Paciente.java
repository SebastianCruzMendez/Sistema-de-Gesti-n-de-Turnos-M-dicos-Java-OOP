package co.generation.clinica.model;

import co.generation.clinica.interfaces.Registrable;

public class Paciente implements Registrable {

    int id;
    private String  nombre;
    private String cedula;
    private String apellido;
    private String telefono;

    public Paciente(int id, String nombre, String cedula, String apellido, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.apellido = apellido;
        this.telefono = telefono;
    }
    @Override
    public String getDatosRegistro() {
        return "";
    }

    @Override
    public boolean esValido() {
        return false;
    }

public Paciente registrar(String nombre, String cedula, String apellido, String telefono) {

        return new Paciente(0, nombre, cedula, apellido, telefono);
}

public Paciente reconstruir(int id, String nombre, String cedula, String apellido, String telefono) {
        return new Paciente(id, nombre, cedula, apellido, telefono);
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



}
