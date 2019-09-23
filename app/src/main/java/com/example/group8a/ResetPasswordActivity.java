package com.example.group8a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText inputEmail;
    private Button btnReset, btnBack;
    private FirebaseAuth auth;
    private ProgressBar progressBarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputEmail = (EditText) findViewById(R.id.userEmail);
        btnReset =(Button) findViewById(R.id.btn_reset_password);
        btnBack =(Button) findViewById(R.id.btn_back);
        progressBarr =(ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        btnBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplication(), "Enter your registered email address.",Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBarr.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions in how to reset your password.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ResetPasswordActivity.this, "Failed to reset password email!", Toast.LENGTH_SHORT).show();
                                }
                                progressBarr.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }
}
