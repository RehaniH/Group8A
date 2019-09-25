package com.example.group8a;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;


public class FirebaseHandler {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    public void addGeneralEnquiries(String fname,String fphone,String email,String fmsg) {


        String[] gid = email.split("@");

        firebaseDatabase.getReference().child("General").child(gid[0]).child("name").setValue(fname);
        firebaseDatabase.getReference().child("General").child(gid[0]).child("phone").setValue(fphone);
        firebaseDatabase.getReference().child("General").child(gid[0]).child("email").setValue(email);
        firebaseDatabase.getReference().child("General").child(gid[0]).child("message").setValue(fmsg);


    }

    public  void addProductRelated(String ftitle,String fname,String email,String fphone,String fmsg){

        String[] pid = email.split("@");

        firebaseDatabase.getReference().child("Product Related").child(pid[0]).child("title").setValue(ftitle);
        firebaseDatabase.getReference().child("Product Related").child(pid[0]).child("name").setValue(fname);
        firebaseDatabase.getReference().child("Product Related").child(pid[0]).child("email").setValue(email);
        firebaseDatabase.getReference().child("Product Related").child(pid[0]).child("phone").setValue(fphone);
        firebaseDatabase.getReference().child("Product Related").child(pid[0]).child("message").setValue(fmsg);

    }

    public  void addOrderRelated(String oid,String fname,String fphone,String femail,String fmsg){

        //String[] pid = email.split("@");

        firebaseDatabase.getReference().child("Order Related").child(oid).child("orderId").setValue(oid);
        firebaseDatabase.getReference().child("Order Related").child(oid).child("name").setValue(fname);
        firebaseDatabase.getReference().child("Order Related").child(oid).child("phone").setValue(fphone);
        firebaseDatabase.getReference().child("Order Related").child(oid).child("email").setValue(femail);
        firebaseDatabase.getReference().child("Order Related").child(oid).child("message").setValue(fmsg);

    }


}
