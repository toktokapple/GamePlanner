package com.example.frame;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private LinearLayout FirstFrame,SecondFrame,ThirdFrame,FourthFrame;
    private TextView alarmellipse;
    private ImageView menuicons,icon1,icon2,mainicon,comuicon,checkicon,myicon,checklistadd;
    private int imageIndex=0;
    private boolean isImage1 = false;
    private mainfragment mainfragment;
    private FrameLayout alarmiconlayout;
    private mainfragment fragment;
    TextView titlename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MainActivity 레이아웃의 프래그먼트 컨테이너에 CalendarFragment 추가
        if (savedInstanceState == null) {
            mainfragment mainfragment = new mainfragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mainfragment, "mainfragment")
                    .commit();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        alarmellipse=(TextView) findViewById(R.id.alarmellipse);
        checklistadd=(ImageView) findViewById(R.id.addicon);//체크리스트addicon
        drawerLayout=findViewById(R.id.drawer);
        FirstFrame=(LinearLayout) findViewById(R.id.FirstFrame);//메인 프레임
        SecondFrame=(LinearLayout) findViewById(R.id.SecondFrame);//체크리스트 프레임
        ThirdFrame=(LinearLayout) findViewById(R.id.ThirdFrame);//커뮤니티 프레임
        FourthFrame=(LinearLayout) findViewById(R.id.FourthFrame);//마이 프레임
        menuicons=(ImageView) findViewById(R.id.menuicons);//메뉴 아이콘
        icon1= findViewById(R.id.icon1);//추가 아이콘
        mainicon=(ImageView)findViewById(R.id.mainicon);//메인 아이콘
        checkicon=(ImageView) findViewById(R.id.checkicon);//체크리스트 아이콘
        comuicon=(ImageView) findViewById(R.id.comuicon);//커뮤니티 아이콘
        myicon=(ImageView) findViewById(R.id.myicon);//마이 아이콘
        titlename=(TextView) findViewById(R.id.titlename);
        icon2= findViewById(R.id.addicon);
        checklistadd.setVisibility(View.INVISIBLE);//기본적으로 checklistadd숨기기

        //메인화면으로 실행
        if(savedInstanceState==null)
        {
            mainfragment mainfragment = new mainfragment();
            fragmentTransaction.replace(R.id.fragment_container,mainfragment);
            fragmentTransaction.commit();
            icon1.setVisibility(View.GONE);
            icon2.setVisibility(View.VISIBLE);
            alarmellipse.setVisibility(View.GONE);
        }//앱 실행시 메인프래그먼트로 실행
        FirstFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                mainfragment mainfragment=new mainfragment();
                transaction.replace(R.id.fragment_container,mainfragment);
                transaction.commit();
                icon2.setImageResource(R.drawable.addicon);
                icon1.setVisibility(View.GONE);
                icon2.setVisibility(View.VISIBLE);
                alarmellipse.setVisibility(View.GONE);
                //메인레이아웃 클릭시 아이콘 변경
                    mainicon.setImageResource(R.drawable._st_);
                    checkicon.setImageResource(R.drawable._nd);
                    comuicon.setImageResource(R.drawable._nd3);
                    myicon.setImageResource(R.drawable._th);
                //메인 제목
                titlename.setText("Jueing");
                menuicons.setVisibility(View.VISIBLE);
            }
        });
        SecondFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                secondfragment secondfragment=new secondfragment();
                transaction.replace(R.id.fragment_container,secondfragment);
                transaction.commit();
                icon2.setImageResource(R.drawable.alarm);
                icon1.setVisibility(View.VISIBLE);
                icon2.setVisibility(View.VISIBLE);
                alarmellipse.setVisibility(View.GONE);
                //체크리스트레이아웃 클릭시 아이콘변경
                    mainicon.setImageResource(R.drawable._st);
                    checkicon.setImageResource(R.drawable._nd_);
                    comuicon.setImageResource(R.drawable._nd3);
                    myicon.setImageResource(R.drawable._th);
                    isImage1 = !isImage1;
                //체크리스트 제목
                titlename.setText("Check list");
                //addicon
                if (isImage1) {
                    icon1.setImageResource(R.drawable.addicon);
                } else {
                    icon1.setImageResource(R.drawable.addicon);
                }
                isImage1 = !isImage1;
                menuicons.setVisibility(View.GONE);
            }
        });

        ThirdFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                thirdfragment thirdfragment=new thirdfragment();
                transaction.replace(R.id.fragment_container,thirdfragment);
                transaction.commit();
                icon2.setImageResource(R.drawable.addicon);
                icon1.setVisibility(View.INVISIBLE);
                icon2.setVisibility(View.VISIBLE);
                alarmellipse.setVisibility(View.GONE);
                    mainicon.setImageResource(R.drawable._st);
                    checkicon.setImageResource(R.drawable._nd);
                    comuicon.setImageResource(R.drawable._nd3_);
                    myicon.setImageResource(R.drawable._th);
                    isImage1 = !isImage1;
                //커뮤니티 제목
                    titlename.setText("Community");
                //addicon
                if (isImage1) {
                    icon1.setImageResource(R.drawable.addicon);
                } else {
                    icon1.setImageResource(R.drawable.addicon);
                }
                isImage1 = !isImage1;
                menuicons.setVisibility(View.GONE);
            }
        });

        FourthFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                fourthfragment fourthfragment=new fourthfragment();
                transaction.replace(R.id.fragment_container,fourthfragment);
                transaction.commit();
                icon2.setImageResource(R.drawable.alarm);
                icon1.setVisibility(View.GONE);
                icon2.setVisibility(View.VISIBLE);
                alarmellipse.setVisibility(View.GONE);
                icon2.setImageResource(R.drawable.setting);
                    mainicon.setImageResource(R.drawable._st);
                    checkicon.setImageResource(R.drawable._nd);
                    comuicon.setImageResource(R.drawable._nd3);
                    myicon.setImageResource(R.drawable._th_);
                    isImage1 = !isImage1;
                //마이 제목
                titlename.setText("My Page");
                //addicon숨기기
                menuicons.setVisibility(View.GONE);
            }
        });
        //addicon기능
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        fragment = new mainfragment(); // mainfragment 객체 생성
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable currentDrawable = icon2.getDrawable();
                if (currentDrawable.getConstantState().equals(getResources().getDrawable(R.drawable.alarm).getConstantState())) {
                    drawerLayout.openDrawer(findViewById(R.id.alarm_nav_view));
                } else if (currentDrawable.getConstantState().equals(getResources().getDrawable(R.drawable.setting).getConstantState())) {
                    FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                    settingfragment settingfragment=new settingfragment();
                    transaction.replace(R.id.fragment_container, settingfragment);
                    transaction.commit();
                    titlename.setText("설정");
                }else if(currentDrawable.getConstantState().equals(getResources().getDrawable(R.drawable.addicon).getConstantState())){
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1; // 월은 0부터 시작하므로 1을 더해줍니다.
                    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                    // mainfragment 객체의 showBottomSheetDialog 메서드 호출
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    mainfragment fragment = (mainfragment) fragmentManager.findFragmentById(R.id.fragment_container);
                    if (fragment != null) {
                        fragment.showBottomSheetDialog(MainActivity.this, year, month, dayOfMonth);
                    }
                }
            }
        });
        menuicons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    drawerLayout.openDrawer(findViewById(R.id.nav_view));
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(findViewById(R.id.alarm_nav_view))) {
            drawerLayout.closeDrawer(findViewById(R.id.alarm_nav_view));
        } else {
            super.onBackPressed();
        }
    }
}








