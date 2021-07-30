package com.example.proton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mFire;
    Button home_loc,home_signout,home_rem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_rem = findViewById(R.id.home_reminder);
        home_signout = findViewById(R.id.home_signout);
        home_loc = findViewById(R.id.home_location);

        home_rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home_to_rem = new Intent(HomeActivity.this, ReminderActivity.class);
                startActivity(home_to_rem);
            }
        });

        home_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home_to_loc = new Intent(HomeActivity.this, LocationActivity.class);
                startActivity(home_to_loc);
            }
        });

        home_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, StartActivity.class));
            }
        });
    }
}