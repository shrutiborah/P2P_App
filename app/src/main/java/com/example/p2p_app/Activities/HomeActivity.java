package com.example.p2p_app.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.p2p_app.Activities.NavFragment.DashboardFragment;
import com.example.p2p_app.Activities.NavFragment.ProfileFragment;

//imp
import com.example.p2p_app.Activities.NavFragment.UserFragment;
import com.example.p2p_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {


    NavigationView navigationView;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadFragment(new DashboardFragment());



        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);





        bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();



                switch (item.getItemId()) {
                    case R.id.dashboard:
                        fragmentTransaction.replace(R.id.relativelayout, new DashboardFragment());
                        break;



                    case R.id.users:
                        fragmentTransaction.replace(R.id.relativelayout, new UserFragment());

                        break;
                    case R.id.profile:
                        fragmentTransaction.replace(R.id.relativelayout, new ProfileFragment());
                        break;
                }
                fragmentTransaction.commit();



                return true;
            }
        });


    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout,fragment).commit();
    }
}