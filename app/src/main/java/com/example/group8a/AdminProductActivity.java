package com.example.group8a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminProductActivity extends AppCompatActivity {

    EditText textName, textColor, textCate, textQty;
    Button btnSave, btnShow, btnUpdate, btnDelete;
    DatabaseReference dbRef;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);

        textName = findViewById(R.id.name);
        textColor = findViewById(R.id.color);
        textCate = findViewById(R.id.category);
        textQty = findViewById(R.id.quantity);

        btnSave = findViewById(R.id.btnSave);

        product = new Product();

    }

    public void onClickSave(View view){

        dbRef = FirebaseDatabase.getInstance().getReference().child("Products");

        try{

            if(TextUtils.isEmpty(textName.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter name ", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(textColor.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please enter color ", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(textCate.getText().toString())){
                Toast.makeText(getApplicationContext(), "Please choose category ", Toast.LENGTH_SHORT).show();
            }else {
                //here the inputs from the products view is assigned to Product object
                product.setName(textName.getText().toString().trim());
                product.setColor(textColor.getText().toString().trim());
                product.setCategory(textCate.getText().toString().trim());
                product.setQuantity(Integer.parseInt(textQty.getText().toString().trim()));


                //inserting to the database
               // dbRef.push().setValue(product);
                    dbRef.child("Std1").setValue(product);
                //sending feedback to the user through a toast
                Toast.makeText(getApplicationContext(), "Added to the inventory ", Toast.LENGTH_SHORT).show();
                clearControls();

            }

        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid format for quantity  ", Toast.LENGTH_SHORT).show();
        }
    }//end of onclick method


    private void clearControls(){

        textName.setText("");
        textColor.setText("");
        textCate.setText("");
        textQty.setText("");


    }//end of clear controls

    public void onShow(View view){

        dbRef = FirebaseDatabase.getInstance().getReference().child("Products").child("Std1");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    try{
                        textName.setText(dataSnapshot.child("name").getValue().toString());
                        textCate.setText(dataSnapshot.child("category").getValue().toString());
                        textColor.setText(dataSnapshot.child("color").getValue().toString());
                        textQty.setText(dataSnapshot.child("quantity").getValue().toString());
                    }catch(NullPointerException e){
                        Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
                    }

                }else
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }//end of retrieve method

    public void onUpdate(View view){
        dbRef = FirebaseDatabase.getInstance().getReference().child("Student");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("Std1")){
                    try{
                        product.setName(textName.getText().toString());
                        product.setCategory(textCate.getText().toString());
                        product.setColor(textColor.getText().toString());
                        product.setQuantity(Integer.parseInt(textQty.getText().toString().trim()));

                        dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("Std1");
                        dbRef.setValue(product);
                        clearControls();
                        Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                    }catch(NumberFormatException e){
                        Toast.makeText(getApplicationContext(), "Invalid contact number", Toast.LENGTH_SHORT).show();
                    }catch(NullPointerException e){
                        Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    }//end try catch block


                }else
                    Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
