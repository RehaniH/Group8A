package com.example.group8a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CartFragment extends Fragment {

    Button btn1, btn2, btn3, btn4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.fragment_cart, container, false);
        btn1 = view.findViewById(R.id.button);
        btn2 = view.findViewById(R.id.button2);
        btn3 = view.findViewById(R.id.button3);
        btn4 = view.findViewById(R.id.button4);
        System.out.println("===================CartFrag");

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("==========onclick");

                Intent intent = new Intent();
                intent.setClass(getActivity(),deliveryoptions.class);
                startActivity(intent);
            }
        });




        return  inflater.inflate(R.layout.fragment_cart, container, false);


    }

    public void clickbutton (View view){
        System.out.println("============");

    }



}
