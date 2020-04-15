package com.simplelifestudio.letscook1.model;

public class ingredientes {
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
