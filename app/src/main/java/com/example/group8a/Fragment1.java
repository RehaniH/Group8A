package com.example.group8a;

import android.media.session.PlaybackState;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class Fragment1 extends Fragment {
    private EditText name;
    private EditText phone;
    public EditText email;
    private EditText msg;
    private Button submit;


    private String  sname,sphone,smsg;
    public String semail;

    public void clearControls() {
        name.setText("");
        phone.setText("");
        email.setText("");
        msg.setText("");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);
        name = (EditText) v.findViewById(R.id.fr1name);
        phone = (EditText) v.findViewById(R.id.fr1phone);
        email = (EditText) v.findViewById(R.id.fr1email);
        msg = (EditText) v.findViewById(R.id.fr1msg);
        submit=(Button) v.findViewById(R.id.fr1submit);




        submit.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {

                try {
                    sname = name.getText().toString();
                    sphone = phone.getText().toString();
                    semail = email.getText().toString();
                    smsg = msg.getText().toString();
                    if(TextUtils.isEmpty(sname)){
                        Toast.makeText(getActivity(),"Please enter  Your name",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(sphone)){
                        Toast.makeText(getActivity(),"Please enter Your phone number",Toast.LENGTH_SHORT).show();
                    }
                    else if(!isValidMobile(sphone)){
                        Toast.makeText(getActivity(),"Your phone number is invalid",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(semail)){
                        Toast.makeText(getActivity(),"Please enter Your email address",Toast.LENGTH_SHORT).show();
                    }
                    else if(!isValidMail(semail)){
                        Toast.makeText(getActivity(),"Your email address is invalid",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(smsg)){
                        Toast.makeText(getActivity(),"Please enter Your message",Toast.LENGTH_SHORT).show();
                    }
                    else {

                        FirebaseHandler fire = new FirebaseHandler();
                        fire.addGeneralEnquiries(sname, sphone, semail, smsg);
                        clearControls();
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getActivity(),"Data Submited Successfully",Toast.LENGTH_SHORT).show();
                }
            }

        });


        return v;
    }

    private boolean isValidMobile(String phone) {
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 6 && phone.length() <= 13;
        }
        return false;
    }

    private boolean isValidMail(String email) {

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(EMAIL_STRING).matcher(email).matches();

    }

}
