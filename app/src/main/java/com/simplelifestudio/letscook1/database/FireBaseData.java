package com.simplelifestudio.letscook1.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.simplelifestudio.letscook1.model.User;

import de.hdodenhof.circleimageview.CircleImageView;
/*Desarrollado por
        Ivan Escudero
        Richar Quiroz
        Todo los derechos reservado 2020*/

public class FireBaseData {

    private final static String TAGU  = "FIREBASEUSER";
    private FirebaseAuth mAuth;
    private FirebaseFirestore db1;
    private User currendUser;



    public FireBaseData() {

        mAuth = FirebaseAuth.getInstance();
        db1 = FirebaseFirestore.getInstance();

        currendUser = new User();




    }

    public User setUserDataTitle(FirebaseFirestore db, Context contex, CircleImageView userImgCIV, TextView userNameTV) {

        String userID = mAuth.getCurrentUser().getUid();

        Log.d(TAGU,userID);

        db.collection("users").document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currendUser = documentSnapshot.toObject(User.class);
                Glide.with(contex).load(currendUser.getUserImg()).into(userImgCIV);
                String username = currendUser.getNombre() + " " + currendUser.getApellido();
                userNameTV.setText(username);
            }
        });
        return currendUser ;
    }


    public void  getUserImg(){



    }

    public User getCurrendUser() {

        String userID = mAuth.getCurrentUser().getUid();

        Log.d(TAGU,userID);

        db1.collection("users").document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                currendUser =  documentSnapshot.toObject(User.class);

            }
        });



        return currendUser;
    }


}
