package com.example.group8a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            bottomNav = findViewById(R.id.bottom_nav);
            bottomNav.setOnNavigationItemSelectedListener(navlisnter);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }catch(NullPointerException e){
            e.printStackTrace();
            System.out.println("Null pointer exception occurred");
        }



    }//end of onCreate()

    private BottomNavigationView.OnNavigationItemSelectedListener navlisnter =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null;
                try{


                    switch(menuItem.getItemId()){
                        case R.id.Home : fragment = new HomeFragment();
                            break;
                        case R.id.category : fragment = new CategoriesFragment();
                            break;
                        case R.id.Like : fragment = new WishlistFragment();
                            break;
                        case R.id.Cart : fragment = new CartFragment();
                            break;
                        case R.id.Profile : fragment = new ProfileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();


                    return true;

                }catch (NullPointerException e){
                    e.printStackTrace();
                    System.out.println("Fragment selected is null");
                    return false;
                }

            }
        };

    public void toAdmin(View view){
        intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }//end of method




}
