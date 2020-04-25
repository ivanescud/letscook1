package com.simplelifestudio.letscook1.model;
/*Desarrollado por
        Ivan Escudero
        Richar Quiroz
        Todo los derechos reservado 2020*/

import java.io.Serializable;

public class Ingrediente implements Serializable {
    String ico;
    String cantidad;
    String producto;

    public Ingrediente(){}

    public Ingrediente(String ico, String cantidad, String producto) {
        this.ico = ico;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
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
