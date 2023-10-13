package com.example.frame;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;


public class bottom_sheet_layout extends Fragment{
    private NumberPicker monthPicker;
    private NumberPicker dayPicker;
    private NumberPicker hourPicker;
    private NumberPicker minutePicker;
    private Spinner ampmSpinner;
    private Switch alarmswitch;
    private Button savebutton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        // 레이아웃에서 필요한 뷰들을 찾아서 초기화
        monthPicker = view.findViewById(R.id.monthPicker);
        dayPicker = view.findViewById(R.id.dayPicker);
        hourPicker = view.findViewById(R.id.hourPicker);
        minutePicker = view.findViewById(R.id.minutePicker);
        ampmSpinner = view.findViewById(R.id.ampmSpinner);
        alarmswitch = view.findViewById(R.id.alarmswitch);


        NumberPicker monthPicker=view.findViewById(R.id.monthPicker);//월
        NumberPicker dayPicker=view.findViewById(R.id.dayPicker);//일
        NumberPicker hourPicker=view.findViewById(R.id.hourPicker);//시
        NumberPicker minutePicker=view.findViewById(R.id.minutePicker);//분
        //월
        monthPicker.setMinValue(0);
        monthPicker.setMaxValue(12);
        //일
        dayPicker.setMinValue(0);
        dayPicker.setMaxValue(31);
        //시
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(12);
        //분
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);

        monthPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("@02d",i);
            }
        });
        dayPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("@02d",i);
            }
        });
        hourPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("@02d",i);
            }
        });
        minutePicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("@02d",i);
            }
        });


        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
