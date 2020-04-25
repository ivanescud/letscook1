package com.simplelifestudio.letscook1.extra;
/*Desarrollado por
        Ivan Escudero
        Richar Quiroz
        Todo los derechos reservado 2020*/

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.model.Banner;
import com.simplelifestudio.letscook1.model.Ingrediente;
import com.simplelifestudio.letscook1.model.Ingredientes;
import com.simplelifestudio.letscook1.model.Receta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataHolder {

    ArrayList<Receta> bebidas = new ArrayList<>();
    ArrayList<Receta>recetas = new ArrayList<>();
    ArrayList<Ingredientes> ingredientes = new ArrayList<>();
    ArrayList<Ingredientes> categoria = new ArrayList<>();
    ArrayList<Banner> bannerlist = new ArrayList<Banner>();

    FirebaseFirestore db;

    public DataHolder() {
        data();
        db = FirebaseFirestore.getInstance();
    }

    public ArrayList<Receta> getBebidas() {
        return bebidas;
    }

    public ArrayList<Receta> getRecetas() {
        return recetas;
    }

    public ArrayList<Ingredientes> getCategoria(){return categoria;}

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
        String url5 = "https://www.superama.com.mx/views/micrositio/recetas/images/fiestaspatrias/tequilasunrise/Web_fotoreceta.jpg";
        String url6 = "https://latrattoriadealmeria.es/wp-content/uploads/2017/07/comida-italiana-restaurantes-italianos-almeria.png";
        String url7 = "https://maxbrownhotels.com/wp-content/uploads/2019/05/3-Eyal-Shani3-300x200.jpg";

        this.bebidas.add(new Receta("Martiny Frances","LetsCook Team",url2,3.7f,likes,byImg2,"3 min"));
        this.bebidas.add(new Receta("Blue Sky","Jorge Lopez",url4,4.0f,likes,byImg2, "5min"));
        this.bebidas.add(new Receta("Tequila Sunrise","Letcook team",url5,3.5f,likes,byImg,"3 min"));
        this.recetas.add(new Receta("Salmon Ahumado","Lets cook Team",url,4.5f,likes,byImg,"5 min"));
        this.recetas.add(new Receta("Corn Flake con leche","LetCook Team",url3,5.0f,likes,byImg,"3 min"));
        this.recetas.add(new Receta("Blue Sky","Jorge Lopez",url4,4.0f,likes,byImg2, "5min"));
        this.recetas.add(new Receta("Martiny Frances","LetsCook Team",url2,3.7f,likes,byImg2,"3 min"));
        this.recetas.add(new Receta("Martiny Frances","LetsCook Team",url2,3.7f,likes,byImg2,"3 min"));
        this.recetas.add(new Receta("Blue Sky","Jorge Lopez",url4,4.0f,likes,byImg2, "5min"));
        this.recetas.add(new Receta("Salmon Ahumado","Lets cook Team",url,4.5f,likes,byImg,"5 min"));
        this.recetas.add(new Receta("Corn Flake con leche","LetCook Team",url3,5.0f,likes,byImg,"3 min"));



        this.ingredientes.add(new Ingredientes("Pollo", R.drawable.carnepollo));
        this.ingredientes.add(new Ingredientes("Carne de Res", R.drawable.carneres));
        this.ingredientes.add(new Ingredientes("Carne de Cerdo", R.drawable.carnecerdo));
        this.ingredientes.add(new Ingredientes("Pescado", R.drawable.carnepescado));
        this.ingredientes.add(new Ingredientes("Vegetariana", R.drawable.carnevegeta));

        this.categoria.add(new Ingredientes("Desayuno",R.drawable.desayuno_categoria));
        this.categoria.add(new Ingredientes("Snack",R.drawable.snack_categoria));
        this.categoria.add(new Ingredientes("Almuerzo",R.drawable.almuerzo_categoria));
        this.categoria.add(new Ingredientes("Cena",R.drawable.cena_categoria));
        this.categoria.add(new Ingredientes("Postre",R.drawable.postre_categoria));
        this.categoria.add(new Ingredientes("Ensalada",R.drawable.ensalada_categoria));
        this.categoria.add(new Ingredientes("Acompañamiento",R.drawable.acompaniamento_categoria));




        bannerlist.add(new Banner("Receta del Mes","receta","Cerial con Leche",url3));
        bannerlist.add(new Banner("Mes de comida Italina","lista","Experimente los saboders de italia",url6));
        bannerlist.add(new Banner("Noche de Apendizaje ","evento","Live con el Chef Carlos Lopez",url7));


    }

    public void addrecetas() {


        for (int i = 0; i < ingredientes.size() ; i++) {

            Receta rc = recetas.get(i);
            String id = db.collection("users").document().getId();
            db.collection("recetas").document(id).set(rc).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    Log.d("Data","Datos agregados");

                }
            });
        }




    }


    public void addBanner() {


        for (int i = 0; i < bannerlist.size() ; i++) {

            Banner rc = bannerlist.get(i);
            String id = db.collection("users").document().getId();
            db.collection("banner").document(id).set(rc).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    Log.d("Data","Datos agregados");

                }
            });
        }




    }




}
