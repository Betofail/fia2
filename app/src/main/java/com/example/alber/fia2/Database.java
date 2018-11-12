package com.example.alber.fia2;

public class Database {
    String id;
    String nombre;  String apellido;
    String id_dueda;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Database(String id, String nombre, String apellido,String id_dueda) {
        this.id = id;
        this.id_dueda = id_dueda;
        this.nombre = nombre;
        this.apellido = apellido;
    }


}
