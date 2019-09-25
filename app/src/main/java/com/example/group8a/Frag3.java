package com.example.group8a;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Frag3 extends Fragment {
    private EditText oid;
    private EditText name;
    private  EditText phone;
    private EditText email;
    private EditText msg;
    private Button submit;

    private String soid,sname,semail,sphone,smsg;

    public void clearControls() {
        oid.setText("");
        name.setText("");
        phone.setText("");
        email.setText("");
        msg.setText("");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag3, container, false);

        oid = (EditText) v.findViewById(R.id.fr3oid);
        name = (EditText) v.findViewById(R.id.fr3name);
        phone= (EditText) v.findViewById(R.id.fr3phone);
        email = (EditText) v.findViewById(R.id.fr3mail);
        msg = (EditText) v.findViewById(R.id.fr3msg);
        submit=(Button) v.findViewById(R.id.fr3submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    soid = oid.getText().toString();
                    sname = name.getText().toString();
                    sphone = phone.getText().toString();
                    semail = email.getText().toString();
                    smsg = msg.getText().toString();

                    if (TextUtils.isEmpty(soid)){
                        Toast.makeText(getActivity(),"Please enter  order id",Toast.LENGTH_SHORT).show();
                    }
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
                        fire.addOrderRelated(soid, sname, sphone, semail, smsg);
                        clearControls();
                    }

                }catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Data Submited Successfully", Toast.LENGTH_SHORT).show();
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
