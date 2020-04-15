package com.simplelifestudio.letscook1.model;

public class Ingrediente {
    int ico;
    String cantidad;
    String producto;

    public Ingrediente(){}

    public Ingrediente(int ico, String cantidad, String producto) {
        this.ico = ico;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getIco() {
        return ico;
    }

    public void setIco(int ico) {
        this.ico = ico;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}
