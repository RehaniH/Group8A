package com.example.group8a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    Button inventory, contactUs, Products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        inventory = findViewById(R.id.to_inventory);
        contactUs = findViewById(R.id.to_ContactUs);
        Products = findViewById(R.id.to_product);



        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminProductActivity.class);
                startActivity(intent);
            }
        });

        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Hashini Add yours
                Intent intent = new Intent(AdminActivity.this, ContactUsAdmin.class);
                startActivity(intent);
            }
        });

        Products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
    }
}
