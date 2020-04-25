package com.simplelifestudio.letscook1.model;
/*Desarrollado por
        Ivan Escudero
        Richar Quiroz
        Todo los derechos reservado 2020*/

public class User {

    private String nombre;
    private  String apellido;
    private int edad;
    private String email;
    private String userImg;
    private String userID;



    public User() {
    }

    public User(String nombre, String apellido, String email,int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;

        this.edad = edad;
    }

    public User(String nombre, String apellido, int edad, String email, String userImg, String userID) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        this.userImg = userImg;
        this.userID = userID;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
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

    public static class ingredientes {
        private String ico;
        private String porcion;
        private String producto;

        public ingredientes() {
        }

        public ingredientes(String ico, String porcion, String producto) {
            this.ico = ico;
            this.porcion = porcion;
            this.producto = producto;
        }

        public String getIco() {
            return ico;
        }

        public void setIco(String ico) {
            this.ico = ico;
        }

        public String getPorcion() {
            return porcion;
        }

        public void setPorcion(String porcion) {
            this.porcion = porcion;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }
    }
}
