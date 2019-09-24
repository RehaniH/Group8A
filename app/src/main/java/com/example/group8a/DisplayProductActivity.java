package com.example.group8a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DisplayProductActivity extends AppCompatActivity {

    TextView name, color, quantity;
    ImageView imageView;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    String imageUri;

    private FirebaseDatabase database ;
    private DatabaseReference mReference ;
    private DatabaseReference childReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product);

        Intent intent = getIntent();
        String key = intent.getStringExtra("Key");

        name = findViewById(R.id.display_name);
        color = findViewById(R.id.display_color);
        quantity = findViewById(R.id.display_quantity);
        imageView = findViewById(R.id.display_item);
        final Context context = this;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Items").child(key);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    imageUri =  dataSnapshot.child("mUri").getValue().toString();
                    name.setText(dataSnapshot.child("name").getValue().toString());
                    color.setText(dataSnapshot.child("color").getValue().toString());
                    quantity.setText(dataSnapshot.child("quantity").getValue().toString());
                    Picasso.with(context).load(imageUri).placeholder(R.drawable.ic_image_black_24dp).fit().centerCrop().into(imageView);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DisplayProductActivity.this, "Cannot load product", Toast.LENGTH_SHORT).show();
            }
        });

        //Glide.with(getApplicationContext()).load(imageUri).into(imageView);

        //Picasso.with(this).load(imageUri).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);


       /* Context context = this;


        // place here
        database = FirebaseDatabase.getInstance();
        mReference = database.getReference("Items");
        childReference = mReference.child(key);
*/

    }//end of on create method

    /*@Override
    protected void onStart() {
        super.onStart();

        
    }*/


}
