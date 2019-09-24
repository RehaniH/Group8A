package com.example.group8a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
/*import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;*/
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AdminProductActivity extends AppCompatActivity {

    private static final int  PICK_IMAGE_REQUEST = 1;

    EditText name , color, category, quantity;
    Button btn_choose, btn_upload, btn_show, btn_cancel;
    ImageView imageView;
    private Uri imageUri;
    ProgressBar progressBar;
    String uri;


    private DatabaseReference databaseReference;


    private StorageReference sRef;
    private DatabaseReference dataRef;
    private StorageTask mUploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);

        name = findViewById(R.id.name);
        color = findViewById(R.id.color);
        category = findViewById(R.id.category);
        quantity = findViewById(R.id.quantity);
        btn_upload = findViewById(R.id.btn_upload);
        btn_choose = findViewById(R.id.btn_choose);
        btn_show = findViewById(R.id.btn_show);
        imageView = findViewById(R.id.image_to_upload);
        progressBar = findViewById(R.id.progress_bar);
        btn_cancel = findViewById(R.id.cancel_btn);

        sRef = FirebaseStorage.getInstance().getReference("Items");
        dataRef = FirebaseDatabase.getInstance().getReference("Items");

        Intent intent = getIntent();

        String key = null;


        if(intent.getStringExtra("Key")!= null){
            key = intent.getStringExtra("Key");


            showProduct(key);




        }


        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        final String finalKey1 = key;
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(AdminProductActivity.this, "Upload in Progress", Toast.LENGTH_SHORT).show();
                }if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please enter name ", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(color.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please enter color ", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(category.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please choose category ", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(quantity.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter quantity ", Toast.LENGTH_SHORT).show();
                }else if(finalKey1 !=null){
                    onUpdate( finalKey1);
                }else{
                    uploadFile();
                }
            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProductsActivity();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AdminProductActivity.this, AdminActivity.class);
                startActivity(intent1);
            }
        });

    }//end of onCreate method


    /**
     * This method will open a file chooser
     */
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    /**
     * This mehod in crucial for laoding the image to imageview
     *
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(imageView);

            //imageView.setImageURI(imageUri);

        }
    }//end of  on acivity result

    /**
     * Method is called by the way we upload it
     */

    private String getFileExtension(Uri uri){
        ContentResolver cr  = getContentResolver();
        MimeTypeMap mimeMap = MimeTypeMap.getSingleton();
        return mimeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    /**
     * Method is used to upload the Image to FireBase
     */

    private void uploadFile(){

        if(imageUri != null){

            final StorageReference fileReference = sRef.child(System.currentTimeMillis() + "." +
                    getFileExtension(imageUri));

            mUploadTask = fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            /*
                             * Here the progress bar is delayed accordingly
                             */
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 500);

                            //Toast.makeText(AdminProductActivity.this, "Upload Successful", Toast.LENGTH_LONG).show();




                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                   // Toast.makeText(AdminProductActivity.this, "Uri=" + uri.toString(), Toast.LENGTH_LONG).show();

                                    try{


                                        Product upload = new Product();


                                        upload.setmUri(uri.toString());
                                        upload.setName(name.getText().toString().trim());
                                        upload.setColor(color.getText().toString().trim());
                                        upload.setCategory(category.getText().toString().trim());
                                        upload.setQuantity(Integer.parseInt(quantity.getText().toString().trim()));

                                        String uploadId = dataRef.push().getKey();

                                        if(uploadId != null){
                                            dataRef.child(uploadId).setValue(upload);
                                            Toast.makeText(getApplicationContext(), "Added to the inventory ", Toast.LENGTH_SHORT).show();
                                            clearControls();
                                        }





                                    }catch(NumberFormatException e){
                                        Toast.makeText(getApplicationContext(), "Invalid format for quantity  ", Toast.LENGTH_SHORT).show();
                                    }catch (NullPointerException e){
                                        Toast.makeText(getApplicationContext(), "No image selected  ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminProductActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressBar.setProgress((int)progress);

                        }
                    });
        }else
            Toast.makeText(this, "No Image File Selected", Toast.LENGTH_SHORT).show();
    }//end of upload File method


    private void clearControls(){

        name.setText("");
        color.setText("");
        category.setText("");
        quantity.setText("");


    }//end of clear controls

    /**
     * Start new Activity -> Products Activity
     */
    private void openProductsActivity(){
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
    }

    private void showProduct(String key){
        final Context context = this;

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Items").child(key);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                uri =  dataSnapshot.child("mUri").getValue().toString();
                name.setText(dataSnapshot.child("name").getValue().toString());
                color.setText(dataSnapshot.child("color").getValue().toString());
                category.setText(dataSnapshot.child("category").getValue().toString());
                quantity.setText(dataSnapshot.child("quantity").getValue().toString());
                Picasso.with(context).load(uri).placeholder(R.drawable.ic_image_black_24dp).fit().centerCrop().into(imageView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AdminProductActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


    }//end method

    public void onUpdate(final String key){



        if(imageUri != null){

            final StorageReference fileReference = sRef.child(System.currentTimeMillis() + "." +
                    getFileExtension(imageUri));

            mUploadTask = fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            /*
                             * Here the progress bar is delayed accordingly
                             */
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(AdminProductActivity.this, "Inventory Updated", Toast.LENGTH_LONG).show();




                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    try{


                                        Product upload = new Product();


                                        upload.setmUri(uri.toString());
                                        upload.setName(name.getText().toString().trim());
                                        upload.setColor(color.getText().toString().trim());
                                        upload.setCategory(category.getText().toString().trim());
                                        upload.setQuantity(Integer.parseInt(quantity.getText().toString().trim()));


                                        dataRef.child(key).setValue(upload);


                                        Toast.makeText(getApplicationContext(), "Inventory Updated ", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(AdminProductActivity.this, ProductActivity.class);
                                        startActivity(intent);


                                    }catch(NumberFormatException e){
                                        Toast.makeText(getApplicationContext(), "Invalid format for quantity  ", Toast.LENGTH_SHORT).show();
                                    }catch (NullPointerException e){
                                        Toast.makeText(getApplicationContext(), "No image selected  ", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AdminProductActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            progressBar.setProgress((int)progress);

                        }
                    });


        }else{
            Toast.makeText(getApplicationContext(), "No image file chosen", Toast.LENGTH_SHORT).show();
        }




    }//end of update method






}
