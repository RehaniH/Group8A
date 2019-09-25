package com.example.group8a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.os.Bundle;

public class AdminProductRelatedEnquiries extends AppCompatActivity {

    private TextView ptitle,pname,pemail,pphone,pmsg;
    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("General");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_related_enquiries);

        ptitle = (TextView) findViewById(R.id.name);
        pname = (TextView) findViewById(R.id.name);
        pemail = (TextView) findViewById(R.id.email);
        pphone = (TextView) findViewById(R.id.phone);
        pmsg = (TextView) findViewById(R.id.msg);

        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    ptitle.setText(dataSnapshot.child("title").getValue().toString());
                    pname.setText(dataSnapshot.child("name").getValue().toString());
                    pemail.setText(dataSnapshot.child("phone").getValue().toString());
                    pphone.setText(dataSnapshot.child("email").getValue().toString());
                    pmsg.setText(dataSnapshot.child("msg").getValue().toString());
                }else
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
