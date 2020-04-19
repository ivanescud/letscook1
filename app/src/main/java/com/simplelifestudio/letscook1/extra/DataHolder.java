package com.simplelifestudio.letscook1.extra;

import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataHolder {

    ArrayList<Receta>recetas = new ArrayList<>();
    ArrayList<Receta> bebidas = new ArrayList<>();


    public DataHolder(ArrayList<Receta> recetas, ArrayList<Receta> bebidas) {
        this.recetas = recetas;
        this.bebidas = bebidas;

        data();
    }

    public DataHolder() {
    }

    public ArrayList<Receta> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Receta> bebidas) {
        this.bebidas = bebidas;
    }

    public DataHolder(ArrayList<Receta> recetas) {
        this.recetas = recetas;
    }

    public ArrayList<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(ArrayList<Receta> recetas) {
        this.recetas = recetas;
    }



    private void data() {

        Map<String, Boolean> likes =  new HashMap<>();
        likes.put("as5e4c5ed",true);
        likes.put("54fhjus",true);
        likes.put("as4ve",true);

        String byImg = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRcCL-1WAQCU623E3mfzm86LmhYcPrlW5A2wbE7K9yURaWW_GxV&usqp=CAU";
        String byImg2 = "https://cdn-theforkmanager.external.thefork.tech/static/styles/blog_article_header_image/public/wp-blog/3-el-tenedor-atraer-clientes-bartender-restaurante.jpg?itok=RVUU9aiQ";
        Map<String, String> ingrediete  = new HashMap<>();
        String url = "https://s1.eestatic.com/2019/09/12/cocinillas/recetas/pescado-y-marisco/Salmon-Pescado-Pescado_y_marisco_428717504_134377937_1024x576.jpg";
        String url2 = "https://okdiario.com/img/2018/07/02/receta-de-cocktail-de-martini-con-limon-1-655x368.jpg";
        String url3 = "https://images.freeimages.com/images/premium/previews/1716/17166547-cornflakes-with-pouring-milk.jpg";
        String url4 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTBQ0FnVuAGcbUAxpmdB4Q4grLaKDoaHtvEfkE5PQn1hLcxoxhJ&usqp=CAU";
        recetas.add(new Receta("Salmon Ahumado","Lets cook Team",url,4.5f,likes,byImg));
        recetas.add(new Receta("Corn Flake con leche","LetCook Team",url3,5.0f,likes,byImg));

        bebidas.add(new Receta("Martiny Frances","LetsCook Team",url2,3.7f,likes,byImg2));
        bebidas.add(new Receta("Blue Sky","Jorge Lopez",url4,4.0f,likes,byImg2));


    }
}
