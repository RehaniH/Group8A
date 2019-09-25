package com.example.group8a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class ContactUsAdmin extends AppCompatActivity {

    Button gereral2,product2,order2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_admin);

        gereral2= findViewById(R.id.general1);
        product2 = findViewById(R.id.product1);
        order2=findViewById(R.id.order1);

        gereral2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ContactUsAdmin.this,AdminGeneralEnquiries.class);
                startActivity(intent);
            }
        });

        product2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ContactUsAdmin.this,AdminProductRelatedEnquiries.class);
                startActivity(intent);

            }
        });

        order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ContactUsAdmin.this,AdminProductRelatedEnquiries.class);
                startActivity(intent);

            }
        });

    }

}
