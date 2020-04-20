package com.simplelifestudio.letscook1.model;

public class Ingredientes {


    private String nombreIN;
    private int MainImg;


    public Ingredientes() {
    }

    public Ingredientes(String nombreIN, int mainImg) {
        this.nombreIN = nombreIN;
        MainImg = mainImg;
    }


    public String getNombreIN() {
        return nombreIN;
    }

    public void setNombreIN(String nombreIN) {
        this.nombreIN = nombreIN;
    }

    public int getMainImg() {
        return MainImg;
    }

    public void setMainImg(int mainImg) {
        MainImg = mainImg;
    }
}
