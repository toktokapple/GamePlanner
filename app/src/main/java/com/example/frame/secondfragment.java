package com.example.frame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class secondfragment extends Fragment {
    private TextView hyperburnning,edentisc,summerpoint,acrasia;
    private TextView maplestorytitle,lostarctitle,maplearrow,lostarcarrow;
    private ImageView alarm;
    private boolean isCheck = true;
    private boolean isRectangle1 = true;
    private ProgressBar progressbar1;
    private int maxValue = 100; // 고정된 max 값
    private int value = 0;
    public secondfragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.secondfragment, container, false);

        progressbar1 = view.findViewById(R.id.progressbar1);

        Bundle bundle = getArguments();
        if (bundle != null) {
            maxValue = bundle.getInt("maxValue");
            value = bundle.getInt("value");
        }

        updateProgressBar();

        return view;
    }
    private void updateProgressBar() {
        float progress = (float) value / maxValue * 100;
        progressbar1.setProgress((int) progress);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        maplestorytitle=view.findViewById(R.id.maplestorytitle);
        maplestorytitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maplestory_check maplestory_check = new maplestory_check();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, maplestory_check);
                fragmentTransaction.commit();
            }
        });
        maplearrow=view.findViewById(R.id.maplearrow);
        maplearrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maplestory_check maplestory_check = new maplestory_check();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, maplestory_check);
                fragmentTransaction.commit();
            }
        });

        lostarctitle=view.findViewById(R.id.lostarctitle);
        lostarctitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        lostarcarrow=view.findViewById(R.id.lostarcarrow);
        lostarcarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        hyperburnning=view.findViewById(R.id.hyperburnning);
        hyperburnning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck) {
                    hyperburnning.setBackgroundResource(R.drawable.pinkcheck);
                } else {
                    hyperburnning.setBackgroundResource(R.drawable.checkminirectangle1);
                }
                isCheck = !isCheck;
            }
        });
        edentisc=view.findViewById(R.id.edentisc);
        edentisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck) {
                    edentisc.setBackgroundResource(R.drawable.pinkcheck);
                } else {
                    edentisc.setBackgroundResource(R.drawable.checkminirectangle1);
                }
                isCheck = !isCheck;
            }
        });
        summerpoint=view.findViewById(R.id.summerpoint);
        summerpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck) {
                    summerpoint.setBackgroundResource(R.drawable.pinkcheck);
                } else {
                    summerpoint.setBackgroundResource(R.drawable.checkminirectangle1);
                }
                isCheck = !isCheck;
            }
        });
        acrasia=view.findViewById(R.id.acrasia);
        acrasia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck) {
                    acrasia.setBackgroundResource(R.drawable.pinkcheck);
                } else {
                    acrasia.setBackgroundResource(R.drawable.checkminirectangle1);
                }
                isCheck = !isCheck;
            }
        });


    }
}
