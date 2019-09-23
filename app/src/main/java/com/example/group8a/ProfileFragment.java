package com.example.group8a;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ProfileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        Button btnOpen = (Button) view.findViewById(R.id.profileButton);
        btnOpen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(),ProfileActivity.class);
                in.putExtra("some","som data");
                startActivity(in);
            }
        });

        return view;


    }


}
