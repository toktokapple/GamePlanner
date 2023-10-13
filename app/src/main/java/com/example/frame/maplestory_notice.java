package com.example.frame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class maplestory_notice extends Fragment {

    TextView mapleeventview,mapleupdateview,mapleinformationview;
    public maplestory_notice(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.maplestory_notice,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 프래그먼트1로 돌아갈 때 뒤로가기 버튼 처리
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 프래그먼트1로 이동하는 코드를 여기에 작성합니다.
                // 예시: 프래그먼트1로 교체하려면 아래 코드를 사용합니다.
                maplestory_check maplestory_check = new maplestory_check();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, maplestory_check)
                        .commit();
            }
        });

        mapleeventview=(TextView) view.findViewById(R.id.mapleeventview);
        mapleupdateview=(TextView) view.findViewById(R.id.mapleupdateview);
        mapleinformationview=(TextView) view.findViewById(R.id.mapleinformationview);

        mapleeventview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maplestory_event maplestory_event = new maplestory_event();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, maplestory_event);
                fragmentTransaction.commit();
            }
        });
        mapleupdateview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maplestory_update maplestory_update = new maplestory_update();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, maplestory_update);
                fragmentTransaction.commit();
            }
        });
        mapleinformationview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maplestory_information maplestory_information = new maplestory_information();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, maplestory_information);
                fragmentTransaction.commit();
            }
        });
    }
}
