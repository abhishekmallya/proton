package com.example.proton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends AppCompatActivity {

/*------------------Home navigation---------------------*/
    BottomNavigationView home_bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().beginTransaction().replace(R.id.home_frame_container,new HomeFragment()).commit();

        home_bnv = (BottomNavigationView)findViewById(R.id.bottom_navigation_home);
        home_bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected (MenuItem item){
                Fragment temp = null;
                switch(item.getItemId())
                {
                    case R.id.dashboard_menu_dash: temp = new HomeFragment();
                        break;
                    case R.id.dashboard_menu_reminder: temp = new ReminderFragment();
                        break;
                    case R.id.dashboard_menu_store: temp = new StoreFragment();
                        break;
                    case R.id.dashboard_menu_profile: temp = new ProfileFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.home_frame_container,temp).commit();
                return true;
            }
        });
    }
}