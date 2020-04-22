package com.simplelifestudio.letscook1.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import static android.os.UserHandle.readFromParcel;

public class Paso implements Serializable {
    String numeroPaso;
    String intruccion;
    String ico;
    int posVideo;

    public Paso(int i, String value, int ico, Integer key) { }

    public Paso(Parcel in){
        super();
        readFromParcel(in);
    }

    public Paso() {}

    public Paso(String numeroPaso, String intruccion, String ico, int posVideo) {
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

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public int getPosVideo() {
        return posVideo;
    }

    public void setPosVideo(int posVideo) {
        this.posVideo = posVideo;
    }
}
