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


public class Frag2 extends Fragment {
    private EditText title;
    private EditText name;
    private EditText email;
    private EditText phone;
    private EditText msg;

    private Button submit;

    private String ctitle,cname,cemail,cphone,cmsg;


    public void clearControls() {
        title.setText("");
        name.setText("");
        email.setText("");
        phone.setText("");
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

        //return
        View v= inflater.inflate(R.layout.fragment_frag2, container, false);

        title = (EditText) v.findViewById(R.id.fr2ptitle);
        name=  (EditText) v.findViewById(R.id.fr2name);
        email = (EditText) v.findViewById(R.id.fr2email);
        phone = (EditText) v.findViewById(R.id.fr2phone);
        msg = (EditText) v.findViewById(R.id.fr2msg);
        submit=(Button) v.findViewById(R.id.fr2submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    ctitle = title.getText().toString();
                    cname = name.getText().toString();
                    cphone = phone.getText().toString();
                    cemail = email.getText().toString();
                    cmsg = msg.getText().toString();

                    if (TextUtils.isEmpty(ctitle)){
                        Toast.makeText(getActivity(),"Please enter  Product title",Toast.LENGTH_SHORT).show();
                    }
                    if(TextUtils.isEmpty(cname)){
                        Toast.makeText(getActivity(),"Please enter  Your name",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(cphone)){
                        Toast.makeText(getActivity(),"Please enter Your phone number",Toast.LENGTH_SHORT).show();
                    }
                    else if(!isValidMobile(cphone)){
                        Toast.makeText(getActivity(),"Your phone number is invalid",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(cemail)){
                        Toast.makeText(getActivity(),"Please enter Your email address",Toast.LENGTH_SHORT).show();
                    }
                    else if(!isValidMail(cemail)){
                        Toast.makeText(getActivity(),"Your email address is invalid",Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(cmsg)){
                        Toast.makeText(getActivity(),"Please enter Your message",Toast.LENGTH_SHORT).show();
                    }
                    else {

                        FirebaseHandler fire = new FirebaseHandler();
                        fire.addProductRelated(ctitle, cname, cemail, cphone, cmsg);
                        clearControls();
                    }
                }catch (NumberFormatException e){
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
