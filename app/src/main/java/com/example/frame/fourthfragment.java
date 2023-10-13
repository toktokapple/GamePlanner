package com.example.frame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class fourthfragment extends Fragment {
    private ImageView profilearrow,gamearrow,contentarrow;
    public ImageView MyProfileEllipse;
    public fourthfragment(){

    }
    // MyProfileEllipse 이미지 설정 메서드
    public void setMyProfileImage(Bitmap bitmap) {
        if (MyProfileEllipse != null) {
            MyProfileEllipse.setImageBitmap(bitmap);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fourthfragment, container, false);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profilearrow=(ImageView) view.findViewById(R.id.profilearrow);
        gamearrow=(ImageView) view.findViewById(R.id.gamearrow);
        contentarrow=(ImageView) view.findViewById(R.id.contentarrow);
        profilearrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction= getFragmentManager().beginTransaction();
                profile profile=new profile();
                transaction.replace(R.id.fragment_container,profile);
                transaction.commit();
                //타이틀변경,가운데 정렬,사이즈18
            }
        });
        gamearrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction= getFragmentManager().beginTransaction();
                mygame mygame=new mygame();
                transaction.replace(R.id.fragment_container,mygame);
                transaction.commit();
                //타이틀변경,가운데 정렬,사이즈18
            }
        });
        contentarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction= getFragmentManager().beginTransaction();
                mycontent mycontent=new mycontent();
                transaction.replace(R.id.fragment_container,mycontent);
                transaction.commit();
                //타이틀변경,가운데 정렬,사이즈18
            }
        });

    }
}
