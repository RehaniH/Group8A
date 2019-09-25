package com.example.group8a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;


import android.os.Bundle;

public class CustomerContactUs extends AppCompatActivity {

    public TextView txtView1;
    public Button btn1;
    public  Button btn2;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_contact_us);


        txtView1=(TextView)findViewById(R.id.txtView1);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button) findViewById(R.id.btn2);
        spinner = findViewById(R.id.dropdown);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(CustomerContactUs.this,
                R.layout.custom_spinner,
                getResources().getStringArray(R.array.fragment));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                switch(i){


                    case (0):
                    {
                        Fragment fragment;
                        fragment = new Fragment1();
                        FragmentManager fragmentManager =getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.newshowfrg,fragment);
                        fragmentTransaction.commit();
                        break;
                    }
                    case (1):

                    {
                        Fragment fragment;
                        fragment = new Frag2();
                        FragmentManager fragmentManager =getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.newshowfrg,fragment);
                        fragmentTransaction.commit();
                        break;

                    }
                    case(2):

                    {
                        Fragment fragment;
                        fragment = new Frag3();
                        FragmentManager fragmentManager =getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.newshowfrg,fragment);
                        fragmentTransaction.commit();
                        break;

                    }
                }






            }






            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
