package com.simplelifestudio.letscook1.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Receta implements Serializable {

    private String nombreRC;
    private String autorRC;
    private String mainImgRc;
    private float rankingRC;
    private Map<String, Boolean> likes = new HashMap<>();
    private int coments;
    private Map<String,Ingrediente> ingredientes = new HashMap<>();
    private Map<Integer,String> pasos = new HashMap<>();
    private String autorImgRC;
    private String type;
    private String idRC;
    private String tiempo;


    public Receta() {
    }

    public Receta(String nombreRC, String autorRC, String mainImgRc, float rankingRC, Map<String, Boolean> likes, int coments, Map<String, Ingrediente> ingredientes, Map<Integer, String> pasos, String autorImgRC) {
        this.nombreRC = nombreRC;
        this.autorRC = autorRC;
        this.mainImgRc = mainImgRc;
        this.rankingRC = rankingRC;
        this.likes = likes;
        this.coments = coments;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.autorImgRC = autorImgRC;
    }

    public Receta(String nombreRC, String autorRC, String mainImgRc, float rankingRC, Map<String, Boolean> likes, String autorImgRC, String tiempo) {
        this.nombreRC = nombreRC;
        this.autorRC = autorRC;
        this.mainImgRc = mainImgRc;
        this.rankingRC = rankingRC;
        this.likes = likes;
        this.autorImgRC = autorImgRC;
        this.tiempo = tiempo;
    }

    public Receta(String idRC,String nombreRc,String mainImgRc,float rankingRC){
        this.idRC = idRC;
        this.nombreRC = nombreRc;
        this.mainImgRc = mainImgRc;
        this.rankingRC = rankingRC;
    }

    public Receta(String idRC,String nombreRc,String mainImgRc){
        this.idRC = idRC;
        this.nombreRC = nombreRc;
        this.mainImgRc = mainImgRc;
    }


    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
    public String getIdRC() {
        return idRC;
    }

    public void setIdRC(String idRC) {
        this.idRC = idRC;
    }
    public String getNombreRC() {
        return nombreRC;
    }

    public void setNombreRC(String nombreRC) {
        this.nombreRC = nombreRC;
    }

    public String getAutorRC() {
        return autorRC;
    }

    public void setAutorRC(String autorRC) {
        this.autorRC = autorRC;
    }

    public String getMainImgRc() {
        return mainImgRc;
    }

    public void setMainImgRc(String mainImgRc) {
        this.mainImgRc = mainImgRc;
    }

    public float getRankingRC() {
        return rankingRC;
    }

    public void setRankingRC(float rankingRC) {
        this.rankingRC = rankingRC;
    }

    public Map<String, Boolean> getLikes() {
        return likes;
    }

    public void setLikes(Map<String, Boolean> likes) {
        this.likes = likes;
    }

    public int getComents() {
        return coments;
    }

    public void setComents(int coments) {
        this.coments = coments;
    }

    public Map<String, Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(Map<String, Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Map<Integer, String> getPasos() {
        return pasos;
    }

    public void setPasos(Map<Integer, String> pasos) {
        this.pasos = pasos;
    }

    public String getAutorImgRC() {
        return autorImgRC;
    }

    public void setAutorImgRC(String autorImgRC) {
        this.autorImgRC = autorImgRC;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
