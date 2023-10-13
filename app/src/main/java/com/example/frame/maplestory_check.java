package com.example.frame;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class maplestory_check extends Fragment {
    TextView eventbutton;
    TextView checknum1,checknum2,checknum3,checknum4,checknum5,checknum6;
    TextView pinkcheck1,pinkcheck2,pinkcheck3,pinkcheck4,pinkcheck5,pinkcheck6;
    TextView allbutton,daybutton,weekbutton;

    private boolean isCheck1 = true;
    private boolean isCheck2 = true;
    private boolean isCheck3 = true;
    private boolean isCheck4 = true;
    private boolean isCheck5 = true;
    private boolean isCheck6 = true;
    private boolean buttoncheck1 = true;
    private boolean buttoncheck2 = true;
    private boolean buttoncheck3 = true;
    private int maxvalue=0;
    private int value=0;
    FrameLayout zacum,magnus,pafulatus,swuo,demian,hila;
    public maplestory_check(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.maplestory_check,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout[] frameLayouts = {
            zacum = (FrameLayout) view.findViewById(R.id.zacum),
            magnus = (FrameLayout) view.findViewById(R.id.magnus),
            pafulatus = (FrameLayout) view.findViewById(R.id.pafulatus),
            swuo = (FrameLayout) view.findViewById(R.id.swuo),
            demian = (FrameLayout) view.findViewById(R.id.demian),
            hila = (FrameLayout) view.findViewById(R.id.hila)
        };
        // FrameLayout의 수만큼 value 값을 증가시킵니다.
        maxvalue += frameLayouts.length;
        // 각 FrameLayout에 value 값을 설정합니다.
        for (FrameLayout frameLayout : frameLayouts) {
            frameLayout.setTag(maxvalue);
        }

        checknum1=(TextView) view.findViewById(R.id.checknum1);
        checknum2=(TextView) view.findViewById(R.id.checknum2);
        checknum3=(TextView) view.findViewById(R.id.checknum3);
        checknum4=(TextView) view.findViewById(R.id.checknum4);
        checknum5=(TextView) view.findViewById(R.id.checknum5);
        checknum6=(TextView) view.findViewById(R.id.checknum6);
        pinkcheck1=(TextView) view.findViewById(R.id.pinkcheck1);
        pinkcheck2=(TextView) view.findViewById(R.id.pinkcheck2);
        pinkcheck3=(TextView) view.findViewById(R.id.pinkcheck3);
        pinkcheck4=(TextView) view.findViewById(R.id.pinkcheck4);
        pinkcheck5=(TextView) view.findViewById(R.id.pinkcheck5);
        pinkcheck6=(TextView) view.findViewById(R.id.pinkcheck6);
        allbutton=(TextView) view.findViewById(R.id.allbutton);
        daybutton=(TextView) view.findViewById(R.id.daybutton);
        weekbutton=(TextView) view.findViewById(R.id.weekbutton);
        allbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allbutton.setBackgroundResource(R.drawable.orangetag);
                allbutton.setTextColor(Color.WHITE);
                daybutton.setBackgroundResource(R.drawable.whitetag);
                daybutton.setTextColor(Color.BLACK);
                weekbutton.setBackgroundResource(R.drawable.whitetag);
                weekbutton.setTextColor(Color.BLACK);
                zacum.setVisibility(View.VISIBLE);
                magnus.setVisibility(View.VISIBLE);
                pafulatus.setVisibility(View.VISIBLE);
                swuo.setVisibility(View.VISIBLE);
                demian.setVisibility(View.VISIBLE);
                hila.setVisibility(View.VISIBLE);
            }
        });
        daybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allbutton.setBackgroundResource(R.drawable.whitetag);
                allbutton.setTextColor(Color.BLACK);
                daybutton.setBackgroundResource(R.drawable.orangetag);
                daybutton.setTextColor(Color.WHITE);
                weekbutton.setBackgroundResource(R.drawable.whitetag);
                weekbutton.setTextColor(Color.BLACK);
                zacum.setVisibility(View.VISIBLE);
                magnus.setVisibility(View.GONE);
                pafulatus.setVisibility(View.VISIBLE);
                swuo.setVisibility(View.VISIBLE);
                demian.setVisibility(View.VISIBLE);
                hila.setVisibility(View.GONE);
            }
        });
        weekbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allbutton.setBackgroundResource(R.drawable.whitetag);
                allbutton.setTextColor(Color.BLACK);
                daybutton.setBackgroundResource(R.drawable.whitetag);
                daybutton.setTextColor(Color.BLACK);
                weekbutton.setBackgroundResource(R.drawable.orangetag);
                weekbutton.setTextColor(Color.WHITE);
                zacum.setVisibility(View.GONE);
                magnus.setVisibility(View.VISIBLE);
                pafulatus.setVisibility(View.GONE);
                swuo.setVisibility(View.GONE);
                demian.setVisibility(View.GONE);
                hila.setVisibility(View.VISIBLE);
            }
        });
        zacum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck1) {
                    pinkcheck1.setVisibility(View.VISIBLE);
                    checknum1.setText("1회");
                    value++;
                } else {
                    pinkcheck1.setVisibility(View.GONE);
                    checknum1.setText("0회");
                    value--;
                }
                isCheck1 = !isCheck1;
                updateProgressBar();
            }
        });
        magnus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck2) {
                    pinkcheck2.setVisibility(View.VISIBLE);
                    checknum2.setText("1회");
                    value++;
                } else {
                    pinkcheck2.setVisibility(View.GONE);
                    checknum2.setText("0회");
                    value--;
                }
                isCheck2 = !isCheck2;
                updateProgressBar();
            }
        });
        pafulatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck3) {
                    pinkcheck3.setVisibility(View.VISIBLE);
                    checknum3.setText("1회");
                    value++;
                } else {
                    pinkcheck3.setVisibility(View.GONE);
                    checknum3.setText("0회");
                    value--;
                }
                isCheck3 = !isCheck3;
                updateProgressBar();
            }
        });
        swuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck4) {
                    pinkcheck4.setVisibility(View.VISIBLE);
                    checknum4.setText("1회");
                    value++;
                } else {
                    pinkcheck4.setVisibility(View.GONE);
                    checknum4.setText("0회");
                    value--;
                }
                isCheck4 = !isCheck4;
                updateProgressBar();
            }
        });
        demian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck5) {
                    pinkcheck5.setVisibility(View.VISIBLE);
                    checknum5.setText("1회");
                    value++;
                } else {
                    pinkcheck5.setVisibility(View.GONE);
                    checknum5.setText("0회");
                    value--;
                }
                isCheck5 = !isCheck5;
                updateProgressBar();
            }
        });
        hila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCheck6) {
                    pinkcheck6.setVisibility(View.VISIBLE);
                    checknum6.setText("1회");
                    value++;
                } else {
                    pinkcheck6.setVisibility(View.GONE);
                    checknum6.setText("0회");
                    value--;
                }
                isCheck6 = !isCheck6;
                updateProgressBar();
            }
        });
        // 프래그먼트1로 돌아갈 때 뒤로가기 버튼 처리
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 프래그먼트1로 이동하는 코드를 여기에 작성합니다.
                // 예시: 프래그먼트1로 교체하려면 아래 코드를 사용합니다.
                secondfragment secondfragment = new secondfragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, secondfragment)
                        .commit();
            }
        });

        eventbutton=(TextView) view.findViewById(R.id.eventbutton);

        eventbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maplestory_notice maplestory_notice = new maplestory_notice();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, maplestory_notice);
                fragmentTransaction.commit();
            }
        });
    }
    private void updateProgressBar() {
        // secondfragment에 접근하여 ProgressBar 업데이트
        secondfragment secondfragment = new secondfragment();
        Bundle bundle = new Bundle();
        bundle.putInt("maxvalue", maxvalue);
        bundle.putInt("value", value);
        secondfragment.setArguments(bundle);

    }
}
