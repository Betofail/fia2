package com.example.alber.fia2;

public class Deuda {
    private String Nombre;
    private String Apellido;
    private String Id_Negocio;
    private String Email;


    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.Apellido = apellido;
    }

    public void setId_negocio(String id_negocio) {
        this.Id_Negocio = id_negocio;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public Deuda(String nombre, String apellido, String id_negocio, String email) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Id_Negocio = id_negocio;
        this.Email = email;
    }

    public Deuda(){

    }

    public String getApellido() {
        return Apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getId_negocio() {
        return Id_Negocio;
    }

    public String getEmail() {
        return Email;
    }
}
