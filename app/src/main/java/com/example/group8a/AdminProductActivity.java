package com.example.group8a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                dbRef.push().setValue(product);

                //sending feedback to the user through a toast
                Toast.makeText(getApplicationContext(), "Added to the inventory ", Toast.LENGTH_SHORT).show();
                clearControls();

            }

        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid format for quantity  ", Toast.LENGTH_SHORT).show();
        }
    }


    private void clearControls(){

        textName.setText("");
        textColor.setText("");
        textCate.setText("");
        textQty.setText("");


    }
}
