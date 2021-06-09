package com.example.proton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {
/* --------------------------------------------------- Variables & Buttons ----------------------------------------------*/

        Button start_signIn, start_register;
        EditText login_email,login_password;

/* -------------------------------------------------------------------------------------------------------------*/
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_start);
/* --------------------------------------------------- Assignment & Fetching Values  ----------------------------------------------*/


        start_signIn = findViewById(R.id.start_signIn);
        start_register = findViewById(R.id.start_register);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);


/* -------------------------------------------------------------------------------------------------------------*/

/* ---------------------------------------------------- Redirections --------------------------------------------------*/

        start_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_to_login = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(start_to_login);
            }
        });

        start_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_to_register = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(start_to_register);
            }
        });


    }
}