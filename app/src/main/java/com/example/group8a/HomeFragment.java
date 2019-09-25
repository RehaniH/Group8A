package com.example.group8a;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {

    Button contactUs, cate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        cate = v.findViewById(R.id.collection);

        cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), ProductActivity.class);
                in.putExtra("some", "some");
                startActivity(in);
            }
        });

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void toContactUs(View view){

        /*Intent intent = new Intent(getActivity(), ContactUs.class);
        startActivity(intent);*/
    }

    public void toCategories(View view){

        CategoriesFragment fragment = new CategoriesFragment();


    }




}
