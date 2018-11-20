package com.example.alber.fia2;

public class Deuda {
    private String Nombre;
    private Integer Monto;
    private String Id_Negocio;
    private String Email;
    private String Rut;

    public void setRut(String rut){this.Rut = rut;}

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public void setId_negocio(String id_negocio) {
        this.Id_Negocio = id_negocio;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setMonto(int monto){this.Monto = monto;}

    public Deuda(String nombre, Integer monto, String id_negocio, String email, String rut) {
        this.Nombre = nombre;
        this.Monto = monto;
        this.Id_Negocio = id_negocio;
        this.Email = email;
        this.Rut = rut;
    }

    public Deuda(){

    }

    public Integer getMonto() {
        return Monto;
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

    public String getRut() {
        return Rut;
    }

}
