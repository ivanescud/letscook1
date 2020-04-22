package com.simplelifestudio.letscook1.controller;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.simplelifestudio.letscook1.R;
import com.simplelifestudio.letscook1.adapters.BannerAdapter;

import com.simplelifestudio.letscook1.adapters.BusquedaRecycleAdapter;
import com.simplelifestudio.letscook1.adapters.CategoriaAdapter;
import com.simplelifestudio.letscook1.adapters.GridIngredientesAdapter;
import com.simplelifestudio.letscook1.adapters.HomeRecetaAdapter;
import com.simplelifestudio.letscook1.database.FireBaseData;
import com.simplelifestudio.letscook1.extra.DataHolder;
import com.simplelifestudio.letscook1.model.Banner;
import com.simplelifestudio.letscook1.model.Ingredientes;
import com.simplelifestudio.letscook1.model.Receta;
import com.simplelifestudio.letscook1.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator3;


public class resultado_listActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAGU = "FIREBASEUSER";


    private ViewPager2 mainPager;
    private HomeRecetaAdapter adapter;
    private ArrayList<Receta> recetaslist = new ArrayList<>();
    private ArrayList<Receta> bebidaslist = new ArrayList<Receta>();
    private CircleIndicator3 circleIndicator;
    BusquedaRecycleAdapter recycleAdapter;
    BusquedaRecycleAdapter recycleAdapter2;
    BusquedaRecycleAdapter recycleAdapter3;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private FirebaseAuth mAuth;
    private FloatingActionButton floatbut;
    private AlertDialog alertDialog;
    private FirebaseFirestore db;
    private CircleImageView useImgCIV;
    private TextView userNameTV;
    private User currendUser;
    private FrameLayout loadingLayout;
    private boolean userReady = false;
    private ArrayList<Banner> bannerList = new ArrayList<>();
    private ArrayList<Receta> recetasList = new ArrayList<>();
    private ArrayList<Receta> bebidasList = new ArrayList<>();
    private ArrayList<Receta>toplist = new ArrayList<>();
    private BannerAdapter bannerAdapter;
    private Button vMasRecetaBT;
    private Button vMasBebidasBT;
    private Button vMasTopBT;

    private ImageView mainImgIV;
    private TextView tilteTV;
    private TextView subTitleTV;
    private TextView horaTV;
    private TextView fechaTV;
    private TextView bannerInfo;
    private Button salirBT;
    private Button notifBT;
    boolean active = false;


    private TabLayout tabLayout;
    private GridView gridview;
    private TextView textViewlabeL;
    private GridIngredientesAdapter gridIngredientesAdapter;
    private Button  floatingActionButton;
    private ArrayList<Ingredientes> ingredientesVegetales;
    private ArrayList<Ingredientes> ingredientesCarnes;
    private ArrayList<Ingredientes> ingredientesFrutas;
    private ArrayList<Ingredientes> ingredientesGranos;
    private ArrayList<Ingredientes> ingredientesHierbas;
    private ArrayList<Ingredientes> ingredientesLacteos;
    private Map<String,Boolean> label;
    private int tipoIngrediente;
    private int datos =0;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_list);

        init();



        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(horizontalLayoutManager);

        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayoutManager2);

        LinearLayoutManager horizontalLayoutManager3 = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(horizontalLayoutManager3);





        getBannerData();
        getRecetasData();
        getBebidasData();
        getTopData();



        // circleIndicator.setViewPager(mainPager);

        floatbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();

        prepararUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);

        MenuItem searchItem = menu.findItem(R.id.searchbar);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.logoutmenuBT:
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;


        }

        return super.onOptionsItemSelected(item);

    }

    private void init() {
        mainPager = findViewById(R.id.busquedaAutoScrollPager);
        circleIndicator = findViewById(R.id.busquedaCircleIndicator);
        recyclerView1 = findViewById(R.id.busquedaPrimeRV);
        recyclerView2 = findViewById(R.id.busquedaSegunRV);
        recyclerView3 = findViewById(R.id.busquedaTreRV);
        floatbut = findViewById(R.id.busquedafloatinBut);
        userNameTV = findViewById(R.id.busquedaUserNameTV);
        useImgCIV = findViewById(R.id.busquedaUserImgCIV);
        vMasRecetaBT = findViewById(R.id.busquedaRecetaBT);
        vMasBebidasBT = findViewById(R.id.busquedaBebidasBT);
        vMasTopBT = findViewById(R.id.busquedaTopBT);
        //loadingLayout = findViewById(R.id.busquedaMainLoadLayoutFL);
        circleIndicator.animatePageSelected(2);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

       // loadingLayout.setVisibility(View.VISIBLE);

        vMasTopBT.setOnClickListener(this);
        vMasBebidasBT.setOnClickListener(this);
        vMasRecetaBT.setOnClickListener(this);

    }


    private void dialogEvento(Banner banner) {

        AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_banner_detail, null, false);
        builder.setView(dialogView);
        mainImgIV = dialogView.findViewById(R.id.bannerdetailMainImgIV);
        tilteTV = dialogView.findViewById(R.id.bannerdetailTitleTV);
        subTitleTV = dialogView.findViewById(R.id.bannerdetailSubTitleTV);
        fechaTV = dialogView.findViewById(R.id.bannerdetailfechaTV);
        horaTV = dialogView.findViewById(R.id.bannerdetailhoraTV);
        bannerInfo = dialogView.findViewById(R.id.bannerdetailDatosTV);
        salirBT = dialogView.findViewById(R.id.bannerdetailSalirBT);
        notifBT = dialogView.findViewById(R.id.bannerdetailNotiBT);



        Glide.with(getApplicationContext()).load(banner.getBannerMainImg()).into(mainImgIV);
        tilteTV.setText(banner.getBannerTitle());
        subTitleTV.setText(banner.getBannersubTile());
        fechaTV.setText("Fecha:"+banner.getFecha());
        horaTV.setText("Hora: "+banner.getHora());
        bannerInfo.setText(banner.getInfo());

        notifBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (active){
                    notifBT.setBackgroundResource(R.drawable.ic_notifications);
                    Toast.makeText(resultado_listActivity.this, "Notificacion Desactivada", Toast.LENGTH_SHORT).show();
                    active = false;
                }else{
                    notifBT.setBackgroundResource(R.drawable.ic_notifications_active);
                    Toast.makeText(resultado_listActivity.this, "Notificacion Activada", Toast.LENGTH_SHORT).show();
                    active = true;
                }
            }
        });

        salirBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        alertDialog = builder.create();
        alertDialog.show();
    }


    private void dialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.seleccion, null, false);
        builder.setView(dialogView);
        ImageButton recetaIB = dialogView.findViewById(R.id.homedialogRcBT);
        ImageButton bebidasIB = dialogView.findViewById(R.id.homedialogBDBT);
        Button salirBT = dialogView.findViewById(R.id.homedialogsalirBT);

        recetaIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               startActivity(new Intent(getApplicationContext(),SeleccioneIngrediente.class));
            //    dialogListaIngre();
            }
        });

        bebidasIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogbebidas();

            }
        });


        salirBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }


    void dialog2() {

        AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_categoria, null, false);
        builder.setView(dialogView);
        GridView gridView = dialogView.findViewById(R.id.categoriaGridView);

        DataHolder dataHolder = new DataHolder();
        ArrayList<Ingredientes> categoria = new ArrayList<>();
        categoria = dataHolder.getCategoria();
        CategoriaAdapter categoriaAdapter = new CategoriaAdapter(resultado_listActivity.this, categoria, R.layout.celda_categoria);
        gridView.setAdapter(categoriaAdapter);


        alertDialog = builder.create();
        alertDialog.show();
    }


    private void dialogbebidas() {

        AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.seleccion, null, false);
        builder.setView(dialogView);
        ImageButton bebidasIB = dialogView.findViewById(R.id.homedialogRcBT);
        ImageButton recetaIB = dialogView.findViewById(R.id.homedialogBDBT);
        TextView ftitleTV = dialogView.findViewById(R.id.homedialFTV);
        TextView secondTV = dialogView.findViewById(R.id.homedialSTV);
        Button salirBT = dialogView.findViewById(R.id.homedialogsalirBT);
        salirBT.setVisibility(View.GONE);

        recetaIB.setImageResource(R.drawable.bebidasalc);
        secondTV.setText("Con Alchol");
        ftitleTV.setText("Sin Alchol");
        bebidasIB.setImageResource(R.drawable.bebidasnoalc);


        recetaIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        bebidasIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(getApplicationContext(),Categoria.class).putExtra("Dato","bebidas"));

            }
        });


        salirBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }



    private void dialogListaIngre() {

        AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.seleccion_ingredientes, null, false);
        builder.setView(dialogView);

         tabLayout = dialogView.findViewById(R.id.seleccionIngredientesTabLayout);
         gridview = dialogView.findViewById(R.id.seleccion_ingredientesGridView);
        textViewlabeL = dialogView.findViewById(R.id.seleccionIngredientesLabelTV);
         floatingActionButton = dialogView.findViewById(R.id.selecciomIngredientesFlotingButtom);


        ingredientesCarnes = new ArrayList<>();
        ingredientesVegetales = new ArrayList<>();
        ingredientesFrutas = new ArrayList<>();
        ingredientesGranos = new ArrayList<>();
        ingredientesHierbas = new ArrayList<>();
        ingredientesLacteos = new ArrayList<>();
        label = new HashMap<>();
        obtenerDatos();
        tablayoutListener();
        gridviewItemListener();
        flotingbuttomActionListener();




        alertDialog = builder.create();
        alertDialog.show();
    }

    public void obtenerDatos(){
        ingredientesCarnes.add(new Ingredientes("Res",R.drawable.carneres));
        ingredientesCarnes.add(new Ingredientes("Pollo",R.drawable.carnepollo));
        ingredientesCarnes.add(new Ingredientes("Carne vegana",R.drawable.carnevegeta));
        ingredientesCarnes.add(new Ingredientes("Cerdo",R.drawable.carnecerdo));
        ingredientesCarnes.add(new Ingredientes("Pescado",R.drawable.carnepescado));


        ingredientesVegetales.add(new Ingredientes("Apio",R.drawable.ingre_apio));
        ingredientesVegetales.add(new Ingredientes("Brocoli",R.drawable.ingre_brocoli));
        ingredientesVegetales.add(new Ingredientes("Coliflor",R.drawable.ingre_colifor));
        ingredientesVegetales.add(new Ingredientes("Zanahoria",R.drawable.ingre_zanahoria));


        ingredientesFrutas.add(new Ingredientes("Banana",R.drawable.ingre_banana));
        ingredientesFrutas.add(new Ingredientes("Uvas",R.drawable.ingre_uva));
        ingredientesFrutas.add(new Ingredientes("Cerezas",R.drawable.ingre_cerezas));
        ingredientesFrutas.add(new Ingredientes("Kiwi",R.drawable.ingre_kiwi));

        ingredientesGranos.add(new Ingredientes("Arroz",R.drawable.ingre_arroz));
        ingredientesGranos.add(new Ingredientes("Mijo",R.drawable.ingre_mijo));
        ingredientesGranos.add(new Ingredientes("Arroz integral",R.drawable.ingre_arrozintegral));
        ingredientesGranos.add(new Ingredientes("Cereal",R.drawable.ingre_cereal));

        ingredientesHierbas.add(new Ingredientes("Perejil",R.drawable.ingre_perejil));
        ingredientesHierbas.add(new Ingredientes("Romero",R.drawable.ingre_romero));
        ingredientesHierbas.add(new Ingredientes("Clavo de olor",R.drawable.ingre_clavoolor));
        ingredientesHierbas.add(new Ingredientes("Vainilla",R.drawable.ingre_vainilla));

        ingredientesLacteos.add(new Ingredientes("Leche",R.drawable.ingre_leche));
        ingredientesLacteos.add(new Ingredientes("Yogurt",R.drawable.ingre_yogurth));
        ingredientesLacteos.add(new Ingredientes("Mantequilla",R.drawable.ingre_matequilla));
        ingredientesLacteos.add(new Ingredientes("Queso",R.drawable.ingre_queso));

        gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesCarnes,R.layout.grid_ingredientes);
        gridview.setAdapter(gridIngredientesAdapter);
    }

    public void tablayoutListener(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tipoIngrediente = tab.getPosition();
                switch (tab.getPosition()){
                    case 0:
                        gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesVegetales,R.layout.grid_ingredientes);
                        gridview.setAdapter(gridIngredientesAdapter);
                        break;
                    case 2:
                        gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesFrutas,R.layout.grid_ingredientes);
                        gridview.setAdapter(gridIngredientesAdapter);
                        break;
                    case 3:
                        gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesGranos,R.layout.grid_ingredientes);
                        gridview.setAdapter(gridIngredientesAdapter);
                        break;
                    case 4:
                        gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesHierbas,R.layout.grid_ingredientes);
                        gridview.setAdapter(gridIngredientesAdapter);
                        break;
                    case 5:
                        gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesLacteos,R.layout.grid_ingredientes);
                        gridview.setAdapter(gridIngredientesAdapter);
                        break;
                    default:
                        gridIngredientesAdapter = new GridIngredientesAdapter(getApplicationContext(),ingredientesCarnes,R.layout.grid_ingredientes);
                        gridview.setAdapter(gridIngredientesAdapter);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    public void gridviewItemListener(){
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                boolean add=true;
                switch (tipoIngrediente){
                    case 0:
                        if(label.get((ingredientesCarnes.get(i).getNombreIN()))!=null){
                            label.remove(ingredientesCarnes.get(i).getNombreIN());
                        }
                        else {
                            label.put(ingredientesCarnes.get(i).getNombreIN(),true);
                        }
                        break;
                    case 1:
                        if(label.get(ingredientesVegetales.get(i).getNombreIN())!=null){
                            label.remove(ingredientesVegetales.get(i).getNombreIN());
                        }
                        else {
                            label.put(ingredientesVegetales.get(i).getNombreIN(),true);
                        }
                        break;
                    case 2:
                        if(label.get(ingredientesFrutas.get(i).getNombreIN())!=null){
                            label.remove(ingredientesFrutas.get(i).getNombreIN());
                        }
                        else {
                            label.put(ingredientesFrutas.get(i).getNombreIN(),true);
                        }
                        break;
                    case 3:
                        if(label.get(ingredientesGranos.get(i).getNombreIN())!=null){
                            label.remove(ingredientesGranos.get(i).getNombreIN());
                        }
                        else {
                            label.put(ingredientesGranos.get(i).getNombreIN(),true);
                        }
                        break;
                    case 4:
                        if(label.get(ingredientesGranos.get(i).getNombreIN())!=null){
                            label.remove(ingredientesGranos.get(i).getNombreIN());
                        }
                        else {
                            label.put(ingredientesGranos.get(i).getNombreIN(),true);
                        }
                        break;
                    case 5:
                        if(label.get(ingredientesGranos.get(i).getNombreIN())!=null){
                            label.remove(ingredientesGranos.get(i).getNombreIN());
                        }
                        else {
                            label.put(ingredientesGranos.get(i).getNombreIN(),true);
                        }
                        break;
                    default:
                        if(label.get(ingredientesCarnes.get(i).getNombreIN())!=null){
                            label.remove(ingredientesCarnes.get(i).getNombreIN());
                        }
                        else {
                            label.put(ingredientesCarnes.get(i).getNombreIN(),true);
                        }
                        break;

                }
                textolabelSet();
            }
        });
    }

    public void textolabelSet(){
        textViewlabeL.setText("");
        datos = 0;
        for(String key : label.keySet()) {
            datos++;
            if (textViewlabeL.getText().toString().isEmpty()) {
                textViewlabeL.setText(key);
            } else {
                textViewlabeL.setText(textViewlabeL.getText() + "," + key);
            }
        }
    }

    public void flotingbuttomActionListener(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] listalabel = new String[datos];
                int i = 0;
                if(label.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"No tienes ingredientes sufucientes",Toast.LENGTH_SHORT).show();
                }
                else{
                    for(String key : label.keySet()) {
                        listalabel[i] = key;
                        i++;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putStringArray("listalabel",listalabel);
                    Intent intent = new Intent(getApplicationContext(), ResultadoBusqueda.class).putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }




    private void getUserDataFirebase() {

        String userID = mAuth.getCurrentUser().getUid();

        Log.d(TAGU,userID);

        db.collection("users").document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot != null){
                    currendUser =  documentSnapshot.toObject(User.class);
                    userReady = true;
                    Glide.with(getApplicationContext()).load(currendUser.getUserImg()).placeholder(R.drawable.proplaceholder).into(useImgCIV);
                    String username = currendUser.getNombre() + " " + currendUser.getApellido();
                    userNameTV.setText(username);
                }



            }
        });

    }

    public void prepararUser() {
        getUserDataFirebase();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!userReady){


                    AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

                    View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.lodingsplash, null, false);
                    builder.setView(dialogView);


                    alertDialog = builder.create();
                    alertDialog.show();
                    handler.removeCallbacks(this::run);

                    Integer imgTag = (Integer) useImgCIV.getTag();
                    alertDialog.dismiss();


                }
                else { prepararUser();}
            }
        }, 100);

    }

    private void getBannerData(){

        db.collection("banner").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                   Banner banner = queryDocumentSnapshot.toObject(Banner.class);
                    bannerList.add(banner);
                }

                bannerAdapter = new BannerAdapter(bannerList, getApplicationContext(), new BannerAdapter.OnClickCellBanner() {
                    @Override
                    public void onClickCell(int positon) {
                        Banner banner = bannerList.get(positon);
                        if(banner.getBannerType().equals("receta")){
                           String recetaId = banner.getRecetaID();
                            startActivity(new Intent(getApplicationContext(),receta_detailActivity.class).putExtra("recetaId",recetaId));
                        }else if (banner.getBannerType().equals("evento")){

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("banner",banner);
                            dialogEvento(banner);

                        }else if(banner.getBannerType().equals("lista")){
                            String style = banner.getStyle();
                            startActivity(new Intent(getApplicationContext(),RecetaList.class).putExtra("style",style));
                        }
                    }
                });

                mainPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(mainPager);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }


    private void getRecetasData(){

        CollectionReference topF = db.collection("recetas");
        Query query = topF.whereEqualTo("type","recetas");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                    Receta receta = queryDocumentSnapshot.toObject(Receta.class);
                    recetaslist.add(receta);
                }

                recycleAdapter = new BusquedaRecycleAdapter(recetaslist, resultado_listActivity.this, new BusquedaRecycleAdapter.OnClickCell2() {
                    @Override
                    public void onClickCell2(int positon) {
                        Receta recetas = recetaslist.get(positon);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("receta",recetas);

                        startActivity(new Intent(getApplicationContext(),receta_detailActivity.class).putExtras(bundle));
                    }
                },1);
                recyclerView1.setAdapter(recycleAdapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });




    }


    private void getBebidasData(){

        CollectionReference topF = db.collection("recetas");
        Query query = topF.whereEqualTo("type","bebidas");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                    Receta receta = queryDocumentSnapshot.toObject(Receta.class);
                    bebidasList.add(receta);
                }

                recycleAdapter2 = new BusquedaRecycleAdapter(bebidasList, resultado_listActivity.this, new BusquedaRecycleAdapter.OnClickCell2() {
                    @Override
                    public void onClickCell2(int positon) {
                        Receta recetas = recetaslist.get(positon);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("receta",recetas);

                        startActivity(new Intent(getApplicationContext(),receta_detailActivity.class).putExtras(bundle));
                    }
                },1);
                recyclerView2.setAdapter(recycleAdapter2);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }



    private void getTopData(){
        CollectionReference topF = db.collection("recetas");
        Query query = topF.whereGreaterThanOrEqualTo("rankingRC",4);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                    Receta receta = queryDocumentSnapshot.toObject(Receta.class);
                    toplist.add(receta);
                }

                recycleAdapter3 = new BusquedaRecycleAdapter(toplist, resultado_listActivity.this, new BusquedaRecycleAdapter.OnClickCell2() {
                    @Override
                    public void onClickCell2(int positon) {
                        Receta recetas = recetaslist.get(positon);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("receta",recetas);

                        startActivity(new Intent(getApplicationContext(),receta_detailActivity.class).putExtras(bundle));
                    }
                }, 3);
                recyclerView3.setAdapter(recycleAdapter3);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }








    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.busquedaRecetaBT:
            startActivity(new Intent(getApplicationContext(),RecetaList.class).putExtra("tipo","recetas"));
            break;

            case R.id.busquedaBebidasBT:
                startActivity(new Intent(getApplicationContext(),RecetaList.class).putExtra("tipo","bebidas"));
                break;


            case R.id.busquedaTopBT:
                startActivity(new Intent(getApplicationContext(),Top10.class));
                break;


        }


    }
}



