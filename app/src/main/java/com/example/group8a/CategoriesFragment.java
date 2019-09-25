package com.example.group8a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment {



    LinearLayout layout;
    ArrayList arr ;
    private Query databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_categories, container, false);


        return inflater.inflate(R.layout.fragment_categories, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RelativeLayout all  = view.findViewById(R.id.all);
        RelativeLayout dresses  = view.findViewById(R.id.dresses);
        RelativeLayout workWear  = view.findViewById(R.id.workwear);
        RelativeLayout tops  = view.findViewById(R.id.tops);
        RelativeLayout denims  = view.findViewById(R.id.denims);
        RelativeLayout partyWear  = view.findViewById(R.id.partyWear);
        RelativeLayout shoes  = view.findViewById(R.id.shoes);
        RelativeLayout accessories  = view.findViewById(R.id.accessories);
        RelativeLayout linen  = view.findViewById(R.id.linen);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProductActivity.class);
                intent.putExtra("Some", "some");
                startActivity(intent);
            }
        });

        dresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dressIn = new Intent(getActivity(), ShoppingActivity.class);
                dressIn.putExtra("Some", "some");
                startActivity(dressIn);
            }
        });

        workWear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent work = new Intent(getActivity(), ShoppingActivity.class);
                work.putExtra("Some", "some");
                startActivity(work);
            }
        });

        tops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent work = new Intent(getActivity(), ShoppingActivity.class);
                work.putExtra("Some", "some");
                startActivity(work);

            }
        });

        denims.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent work = new Intent(getActivity(), ProductActivity.class);
                work.putExtra("Some", "some");
                startActivity(work);

            }
        });

        partyWear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        linen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent work = new Intent(getActivity(), ProductActivity.class);
                work.putExtra("Some", "some");
                startActivity(work);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent work = new Intent(getActivity(), ProductActivity.class);
                work.putExtra("Some", "some");
                startActivity(work);
            }
        });

        accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent work = new Intent(getActivity(), ProductActivity.class);
                work.putExtra("Some", "some");
                startActivity(work);
            }
        });

    }





}
