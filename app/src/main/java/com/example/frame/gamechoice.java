package com.example.frame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class gamechoice extends Fragment {
    private ImageView maplelogo,lostlogo,lollogo,pubglogo;
    public gamechoice(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gamechoice, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        maplelogo=(ImageView)view.findViewById(R.id.maplelogo);
        lostlogo=(ImageView)view.findViewById(R.id.lostlogo);
        lollogo=(ImageView)view.findViewById(R.id.lollogo);
        pubglogo=(ImageView)view.findViewById(R.id.pubglogo);

        maplelogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maplestory_check maplestory_check = new maplestory_check();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, maplestory_check);
                fragmentTransaction.commit();
            }
        });
        lostlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maplestory_check maplestory_check = new maplestory_check();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, maplestory_check);
                fragmentTransaction.commit();
            }
        });
        lollogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maplestory_check maplestory_check = new maplestory_check();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, maplestory_check);
                fragmentTransaction.commit();
            }
        });
        pubglogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maplestory_check maplestory_check = new maplestory_check();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, maplestory_check);
                fragmentTransaction.commit();
            }
        });
    }
}
