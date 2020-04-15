package com.simplelifestudio.letscook1.model;

public class Paso {
    String numeroPaso;
    String intruccion;
    int ico;
    int posVideo;

    public Paso() { }

    public Paso(String numeroPaso, String intruccion, int ico, int posVideo) {
        this.numeroPaso = numeroPaso;
        this.intruccion = intruccion;
        this.ico = ico;
        this.posVideo = posVideo;
    }

    public String getNumeroPaso() {
        return numeroPaso;
    }

    public void setNumeroPaso(String numeroPaso) {
        this.numeroPaso = numeroPaso;
    }

    public String getIntruccion() {
        return intruccion;
    }

    public void setIntruccion(String intruccion) {
        this.intruccion = intruccion;
    }

    public int getIco() {
        return ico;
    }

    public void setIco(int ico) {
        this.ico = ico;
    }

    public int getPosVideo() {
        return posVideo;
    }

    public void setPosVideo(int posVideo) {
        this.posVideo = posVideo;
    }
}
