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


public class WishlistFragment extends Fragment {
   

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        Button wishlistButton =  (Button) view.findViewById(R.id.wishlistButton);

        wishlistButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(),LoginActivity.class);
                in.putExtra("some","som data");
                startActivity(in);
            }
        });;
        return view;


    }


}
