package com.example.group8a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private ProgressBar progress_circle;

    private FirebaseStorage storage;
    private DatabaseReference databaseReference;
    private ValueEventListener DBListener;
    private List<Product> uploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);



        recyclerView = findViewById(R.id.recycler_layout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progress_circle = findViewById(R.id.progress_circle);

        uploads = new ArrayList<>();
        itemAdapter = new ItemAdapter(ProductActivity.this, uploads);
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.setOnClickItemListener(ProductActivity.this);

        storage = FirebaseStorage.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Items");
        DBListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                uploads.clear();
                for(DataSnapshot postSnapShot: dataSnapshot.getChildren()){
                    Product product = postSnapShot.getValue(Product.class);
                    try {
                        product.setImageKey(postSnapShot.getKey());
                    }catch (NullPointerException e){
                        Toast.makeText(ProductActivity.this,"Exception occured", Toast.LENGTH_SHORT).show();
                    }
                    uploads.add(product);

                }


                itemAdapter.notifyDataSetChanged();
                progress_circle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProductActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "In able to read data", Toast.LENGTH_SHORT).show();
                progress_circle.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void OnItemClick(int position) {

        Product selectedProduct = uploads.get(position);
        final String key = selectedProduct.getImageKey();

        Intent intent = new Intent(this, DisplayProductActivity.class);
        intent.putExtra("Key", key);
        startActivity(intent);

    }

    @Override
    public void OnUpdateClick(int position) {

        Product selectedProduct = uploads.get(position);
        final String selectedKey = selectedProduct.getImageKey();


        Product product = new Product();

        Intent intent = new Intent(this,AdminProductActivity.class);
        intent.putExtra("Key", selectedKey);

        startActivity(intent);


        databaseReference.child(selectedKey).setValue(product);
    }

    @Override
    public void OnDeleteClick(int position) {

        Product selectedProduct = uploads.get(position);
        final String selectedKey = selectedProduct.getImageKey();

        StorageReference imageReference = storage.getReferenceFromUrl(selectedProduct.getmUri());

        imageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                databaseReference.child(selectedKey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ProductActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProductActivity.this, "cannot Delete record", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProductActivity.this, "Error ", Toast.LENGTH_SHORT).show();
            }
        });
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(DBListener);
    }
}
