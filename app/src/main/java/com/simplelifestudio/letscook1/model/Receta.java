package com.simplelifestudio.letscook1.model;

import java.util.HashMap;
import java.util.Map;

public class Receta {

    private String nombreRC;
    private String autorRC;
    private String mainImgRc;
    private float rankingRC;
    private Map<String, Boolean> likes = new HashMap<>();
    private int coments;
    private Map<String,Ingrediente> ingredientes = new HashMap<>();
    private Map<Integer,String> pasos = new HashMap<>();


    public Receta() {
    }

    public Receta(String nombreRC, String autorRC, String mainImgRc, float rankingRC, Map<String, Boolean> likes, int coments, Map<String, Ingrediente> ingredientes, Map<Integer, String> pasos) {
        this.nombreRC = nombreRC;
        this.autorRC = autorRC;
        this.mainImgRc = mainImgRc;
        this.rankingRC = rankingRC;
        this.likes = likes;
        this.coments = coments;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
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
}
