package com.example.group8a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AdminGeneralEnquiries extends AppCompatActivity {


   public TextView aname,aphone,aemail,amsg;
   // String Strname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_general_enquiries);

       // Intent intent = getIntent();
        //String key = intent.getStringExtra("Key");
         //  Fragment1 f1= new Fragment1();
           //String gid=f1.semail;
           //Log.i("email",gid);
           //String[] Stremail = gid.split("@");



       aname=  findViewById(R.id.aname);
       aphone=  findViewById(R.id.aphone);
       aemail= findViewById(R.id.aemail);
       amsg=  findViewById(R.id.amsg);
       // Strname = aname.getText().toString();
        //Log.i("hashini", aname.getText().toString());
        //"https://group8a-d14ad/firebaseio/General"

         DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
         DatabaseReference userRef = rootRef.child("General");
           // String id="shanikahashini96";
       // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("General");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{
                   //if(dataSnapshot.hasChildren()){
                    for(DataSnapshot data : dataSnapshot.getChildren()) {
                        DataSnapshot Cusname = data.child("name");
                        DataSnapshot Cusphone = data.child("phone");
                        DataSnapshot Cusemail = data.child("email");
                        DataSnapshot Cusmsg = data.child("message");


                           // aname1.getValue().toString();
                            //aphone1.getValue().toString();
                            //aemail1.getValue().toString();
                            //amsg1.getValue().toString();

                       Log.d("mytest",Cusname.getValue().toString()+""+Cusphone.getValue().toString()+""+Cusemail.getValue().toString()+""+Cusmsg.getValue().toString()+"");
                     //  Log.d("mytest", data.getValue().toString());

                        aname.setText(Cusname.getValue().toString());
                        aphone.setText(Cusphone.getValue().toString());
                        aemail.setText(Cusemail.getValue().toString());
                        amsg.setText(Cusmsg.getValue().toString());

                        aname.setText(Cusname.getValue().toString());
                        aphone.setText(Cusphone.getValue().toString());
                        aemail.setText(Cusemail.getValue().toString());
                        amsg.setText(Cusmsg.getValue().toString());
                        //Log.d("mytest", data.getValue().toString());

                    }
                        //  Log.i("hashini", aname.getText().toString());
                    }catch(Exception e){

                    }

                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AdminGeneralEnquiries.this, "Cannot load General Enquiries", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
