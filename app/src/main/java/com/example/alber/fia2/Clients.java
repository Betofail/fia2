package com.example.alber.fia2;

public class Clients {
    private String documentId;
    private String name;
    private String lastName;
    private int debt;

    public Clients() {

    }

    public Clients(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName(){
        return this.name;
    }

    public String getLastName(){
        return this.lastName;
    }
}
