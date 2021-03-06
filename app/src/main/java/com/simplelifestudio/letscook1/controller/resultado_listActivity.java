package com.simplelifestudio.letscook1.controller;

/*Desarrollado por
        Ivan Escudero
        Richar Quiroz
        Todo los derechos reservado 2020*/

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
    private ArrayList<Receta> bebidaslist = new ArrayList<>();
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
    private AlertDialog alertDialogOp;
    private FirebaseFirestore db;
    private CircleImageView useImgCIV;
    private TextView userNameTV;
    private User currendUser;
    private FrameLayout loadingLayout;
    private boolean userReady = false;
    private ArrayList<Banner> bannerList = new ArrayList<>();
    private ArrayList<Receta> bebidasList = new ArrayList<>();
    private ArrayList<Receta> toplist = new ArrayList<>();
    private BannerAdapter bannerAdapter;
    private Button vMasRecetaBT;
    private Button vMasBebidasBT;
    private Button vMasTopBT;
    ArrayList<Ingredientes> categoria = new ArrayList<>();
    private ImageView mainImgIV;
    private TextView tilteTV;
    private TextView subTitleTV;
    private TextView horaTV;
    private TextView fechaTV;
    private TextView bannerInfo;
    private Button salirBT;
    private Button notifBT;
    boolean active = false;
    private AlertDialog splashDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_list);

        init();

        //Update UI
        getBannerData();
        getRecetasData();
        getBebidasData();
        getTopData();


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

    //Inicializaciones
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
        splashdialog();

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(horizontalLayoutManager);

        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayoutManager2);

        LinearLayoutManager horizontalLayoutManager3 = new LinearLayoutManager(resultado_listActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(horizontalLayoutManager3);


        circleIndicator.animatePageSelected(2);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // loadingLayout.setVisibility(View.VISIBLE);

        vMasTopBT.setOnClickListener(this);
        vMasBebidasBT.setOnClickListener(this);
        vMasRecetaBT.setOnClickListener(this);
        floatbut.setOnClickListener(this);


    }


    //Dialog

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
        fechaTV.setText("Fecha:" + banner.getFecha());
        horaTV.setText("Hora: " + banner.getHora());
        bannerInfo.setText(banner.getInfo());

        notifBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (active) {
                    notifBT.setBackgroundResource(R.drawable.ic_notifications);
                    Toast.makeText(resultado_listActivity.this, "Notificacion Desactivada", Toast.LENGTH_SHORT).show();
                    active = false;
                } else {
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
                dialog2();
                alertDialogOp.dismiss();

            }
        });

        bebidasIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogbebidas();
                alertDialogOp.dismiss();


            }
        });


        salirBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogOp.dismiss();

            }
        });

        alertDialogOp = builder.create();
        alertDialogOp.show();
    }


    void splashdialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.lodingsplash, null, false);
        builder.setView(dialogView);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              splashDialog.dismiss();
            }
        }, 2500);

        splashDialog = builder.create();
        splashDialog.show();


    }

    void dialog2() {

        AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_categoria, null, false);
        builder.setView(dialogView);
        GridView gridView = dialogView.findViewById(R.id.categoriaGridView);

        DataHolder dataHolder = new DataHolder();
        categoria = dataHolder.getCategoria();
        CategoriaAdapter categoriaAdapter = new CategoriaAdapter(resultado_listActivity.this, categoria, R.layout.celda_categoria);
        gridView.setAdapter(categoriaAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getApplicationContext(), SeleccioneIngrediente.class).putExtra("categoria", categoria.get(i).getNombreIN()));
                alertDialog.dismiss();
            }
        });

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


    //Firebase Handle

    private void getUserDataFirebase() {

        String userID = mAuth.getCurrentUser().getUid();

        Log.d(TAGU, userID);

        db.collection("users").document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot != null) {
                    currendUser = documentSnapshot.toObject(User.class);
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
                if (!userReady) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(resultado_listActivity.this);

                    View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.lodingsplash, null, false);
                    builder.setView(dialogView);


                    alertDialog = builder.create();
                    alertDialog.show();
                    handler.removeCallbacks(this::run);

                    Integer imgTag = (Integer) useImgCIV.getTag();
                    alertDialog.dismiss();


                } else {
                    prepararUser();
                }
            }
        }, 100);

    }

    private void getBannerData() {

        db.collection("banner").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                    Banner banner = queryDocumentSnapshot.toObject(Banner.class);
                    bannerList.add(banner);
                }

                bannerAdapter = new BannerAdapter(bannerList, getApplicationContext(), new BannerAdapter.OnClickCellBanner() {
                    @Override
                    public void onClickCell(int positon) {
                        Banner banner = bannerList.get(positon);
                        if (banner.getBannerType().equals("receta")) {
                            String recetaId = banner.getRecetaID();
                            startActivity(new Intent(getApplicationContext(), receta_detailActivity.class).putExtra("recetaId", recetaId));
                        } else if (banner.getBannerType().equals("evento")) {

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("banner", banner);
                            dialogEvento(banner);

                        } else if (banner.getBannerType().equals("lista")) {
                            String style = banner.getStyle();
                            startActivity(new Intent(getApplicationContext(), RecetaList.class).putExtra("numero", 3));
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


    private void getRecetasData() {

        CollectionReference topF = db.collection("recetas");
        Query query = topF.whereEqualTo("type", "recetas");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                    Receta receta = queryDocumentSnapshot.toObject(Receta.class);
                    recetaslist.add(receta);
                }

                recycleAdapter = new BusquedaRecycleAdapter(recetaslist, resultado_listActivity.this, new BusquedaRecycleAdapter.OnClickCell2() {
                    @Override
                    public void onClickCell2(int positon) {
                        Receta recetas = recetaslist.get(positon);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("receta", recetas);

                        startActivity(new Intent(getApplicationContext(), receta_detailActivity.class).putExtras(bundle));
                    }
                }, 1);
                recyclerView1.setAdapter(recycleAdapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }


    private void getBebidasData() {

        CollectionReference topF = db.collection("recetas");
        Query query = topF.whereEqualTo("type", "bebidas");
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                    Receta receta = queryDocumentSnapshot.toObject(Receta.class);
                    bebidasList.add(receta);
                }

                recycleAdapter2 = new BusquedaRecycleAdapter(bebidasList, resultado_listActivity.this, new BusquedaRecycleAdapter.OnClickCell2() {
                    @Override
                    public void onClickCell2(int positon) {
                        Receta recetas = bebidasList.get(positon);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("receta", recetas);

                        startActivity(new Intent(getApplicationContext(), receta_detailActivity.class).putExtras(bundle));
                    }
                }, 1);
                recyclerView2.setAdapter(recycleAdapter2);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }


    private void getTopData() {
        CollectionReference topF = db.collection("recetas");
        Query query = topF.whereGreaterThanOrEqualTo("rankingRC", 4);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                    Receta receta = queryDocumentSnapshot.toObject(Receta.class);
                    toplist.add(receta);
                }

                recycleAdapter3 = new BusquedaRecycleAdapter(toplist, resultado_listActivity.this, new BusquedaRecycleAdapter.OnClickCell2() {
                    @Override
                    public void onClickCell2(int positon) {
                        Receta recetas = toplist.get(positon);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("receta", recetas);

                        startActivity(new Intent(getApplicationContext(), receta_detailActivity.class).putExtras(bundle));
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


// Onclick

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.busquedaRecetaBT:
                startActivity(new Intent(getApplicationContext(), RecetaList.class).putExtra("tipo", "recetas").putExtra("numero",2));
                break;

            case R.id.busquedaBebidasBT:
                startActivity(new Intent(getApplicationContext(), RecetaList.class).putExtra("tipo", "bebidas").putExtra("numero",2));
                break;


            case R.id.busquedaTopBT:
                startActivity(new Intent(getApplicationContext(), Top10.class));
                break;

            case R.id.busquedafloatinBut:
                dialog();
                break;
        }


    }
}



