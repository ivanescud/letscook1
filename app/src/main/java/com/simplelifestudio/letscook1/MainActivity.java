package com.simplelifestudio.letscook1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registerBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            init();
        registerBut.setOnClickListener(this);

    }




    private void init() {
        registerBut = findViewById(R.id.loginRegisterBt);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.loginRegisterBt:

               // Intent inte = new Intent(MainActivity.this,register.class);

                startActivity(new Intent(MainActivity.this,register.class));


                break;

            case R.id.loginbut:


                break;

            default:
                break;

        }


    }
}
