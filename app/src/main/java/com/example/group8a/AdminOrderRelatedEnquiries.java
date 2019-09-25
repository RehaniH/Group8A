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

public class AdminOrderRelatedEnquiries extends AppCompatActivity {

    private TextView hoid,hname,hphone,hemail,hmsg;
    DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("General");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_related_enquiries);

        hoid = (TextView) findViewById(R.id.oid);
        hname = (TextView) findViewById(R.id.name);
        hphone = (TextView) findViewById(R.id.phone);
        hemail = (TextView) findViewById(R.id.email);
        hmsg = (TextView) findViewById(R.id.msg);

        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    hoid.setText(dataSnapshot.child("oid").getValue().toString());
                    hname.setText(dataSnapshot.child("name").getValue().toString());
                    hphone.setText(dataSnapshot.child("phone").getValue().toString());
                    hemail.setText(dataSnapshot.child("email").getValue().toString());
                    hmsg.setText(dataSnapshot.child("msg").getValue().toString());
                }else
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
