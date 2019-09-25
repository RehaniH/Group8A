package com.example.group8a;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HeadlineFragment extends Fragment {

    private Button button3;
    private Button button2;
    private Button button4;

    static final String NAME = "some value";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.headline_fragment_layout,container,false);

        button2=(Button)view.findViewById(R.id.button2);
        button3=(Button)view.findViewById(R.id.button3);
        button4=(Button)view.findViewById(R.id.button4);
        System.out.println("oncreate================");


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("=====");

                Intent intent = new Intent();
                intent.putExtra("Some", NAME);
                intent.setClass(getActivity(), deliveryoptions.class);
                startActivity(intent);

                //getActivity().

            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), giftting_options.class);
                getActivity().startActivity(intent);

            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), Payments.class);
                getActivity().startActivity(intent);

            }
        });


        return view;

    }

    public void startact(){
        System.out.println("==========================method");
        Intent intent = new Intent(getActivity(),deliveryoptions.class);
        startActivity(intent);
    }
}
