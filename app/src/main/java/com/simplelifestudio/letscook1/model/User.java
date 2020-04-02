package com.simplelifestudio.letscook1.model;

public class User {

    private String nombre;
    private  String apellido;
    private int edad;
    private String email;

    private String userID;



    public User() {
    }

    public User(String nombre, String apellido, String email,int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;

        this.edad = edad;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
