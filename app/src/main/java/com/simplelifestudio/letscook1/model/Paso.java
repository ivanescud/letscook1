package com.simplelifestudio.letscook1.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import static android.os.UserHandle.readFromParcel;

public class Paso implements Serializable {
    String numeroPaso;
    String intruccion;
    int ico;
    int posVideo;

    public Paso() { }

    public Paso(Parcel in){
        super();
        readFromParcel(in);
    }

    public Paso(String numeroPaso, String intruccion, String videoUrl, int ico, int posVideo) {
        this.numeroPaso = numeroPaso;
        this.intruccion = intruccion;
        this.ico = ico;
        this.posVideo = posVideo;
    }
//Convierte la clase a una clase Parseable
   /* public static final Parcelable.Creator<Paso> CREATOR = new Parcelable.Creator<Paso>() {
        @Override
        public Paso createFromParcel(Parcel in) {
            return new Paso(in);
        }

        @Override
        public Paso[] newArray(int i) {
            return new Paso[i];
        }
    };

    public void readFromParcel(Parcel in){
        numeroPaso = in.readString();
        intruccion = in.readString();
        ico = in.readInt();
        posVideo = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numeroPaso);
        dest.writeString(intruccion);
        dest.writeInt(ico);
        dest.writeInt(posVideo);
    }
*/
    //getter y setter

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
