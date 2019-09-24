package com.example.group8a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth= FirebaseAuth.getInstance();

        btnSignIn = (Button)findViewById(R.id.sign_in_button);
        btnSignUp =(Button) findViewById(R.id.sign_up_button);
        inputEmail=(EditText) findViewById(R.id.userEmail);
        inputPassword=(EditText)findViewById(R.id.password);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword=(Button)findViewById(R.id.reset_button);
//Continue
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignupActivity.this,ResetPasswordActivity.class));
            }
        });
//check this
        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String email =inputEmail.getText().toString().trim();
                String password= inputPassword.getText().toString().trim();

                //Toast IF Conditions

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter your email address!",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter your password!",Toast.LENGTH_SHORT).show();
                }
                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"Password should be minimum 6 characters !",Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);

                //creating user
                auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignupActivity.this,"In progress"+ task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                        if(!task.isSuccessful()){
                            Toast.makeText(SignupActivity.this,"Authentication failed."+task.getException(),Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignupActivity.this,"Successfully Registered!"+task.getException(),Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                        }
                    }
                });

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    //

}
