package com.example.proton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
/* --------------------------------------------------- buttons ----------------------------------------------*/

        Button start_signIn, start_register;

/* -------------------------------------------------------------------------------------------------------------*/
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_start);
/* --------------------------------------------------- Variables ----------------------------------------------*/


        start_signIn = findViewById(R.id.start_signIn);
        start_register = findViewById(R.id.start_register);


/* -------------------------------------------------------------------------------------------------------------*/


        start_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_to_login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(start_to_login);
            }
        });

        start_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_to_register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(start_to_register);
            }
        });
    }
}