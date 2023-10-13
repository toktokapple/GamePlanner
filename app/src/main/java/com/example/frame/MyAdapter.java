package com.example.frame;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Calendar;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<String> items;
    private Calendar calendar;
    private ViewPager2 viewPager2;
    private int currentPage = 0;
    private static final int INFINITE_ITEMS_COUNT = Integer.MAX_VALUE;
    private int maxItemCount;
    private int swipedPage = -1;
    private long lastClickTime = 0;
    public MyAdapter(Context context, List<String> items, ViewPager2 viewPager2, int maxItemCount) {
        this.context = context;
        this.items = items;
        this.viewPager2 = viewPager2;
        this.maxItemCount = maxItemCount;
        calendar = Calendar.getInstance();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView daynum;
        TextView dayweek;
        ImageView imgBanner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            daynum = itemView.findViewById(R.id.daynum);
            dayweek = itemView.findViewById(R.id.dayweek);
            imgBanner = itemView.findViewById(R.id.imgBanner);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener itemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        notifyDataSetChanged(); // 데이터 변경을 알려서 새로고침
    }
    public String getItem(int position) {
        // 무한 루프를 위해 아이템 인덱스를 리스트 크기로 나눔
        return items.get(position % items.size());
    }
    private ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            // 스와이프로 넘어온 페이지를 저장
            swipedPage = position;
            notifyDataSetChanged();
        }
    };
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        viewPager2.registerOnPageChangeCallback(onPageChangeCallback);
    }
    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        viewPager2.unregisterOnPageChangeCallback(onPageChangeCallback);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return maxItemCount;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int itemCount = maxItemCount;
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String item = items.get(position % items.size()); // 아이템을 무한으로 반복하도록 처리

        // 현재 페이지와 해당 날짜의 페이지 비교
        int nextPage = position;
        boolean isSwipeLeft = currentPage < nextPage;
        boolean isSwipeRight = currentPage > nextPage;

        // 시스템 날짜 가져오기
        Calendar systemCalendar = Calendar.getInstance();
        int systemYear = systemCalendar.get(Calendar.YEAR);
        int systemMonth = systemCalendar.get(Calendar.MONTH);
        int systemDate = systemCalendar.get(Calendar.DATE);

        // 기준 날짜로 설정 (1월 1일)
        Calendar baseCalendar = Calendar.getInstance();
        baseCalendar.set(systemYear, 0, 1);

        // position에 해당하는 날짜 계산하여 출력
        baseCalendar.add(Calendar.DATE, position);
        int itemDate = baseCalendar.get(Calendar.DATE);
        int itemMonth = baseCalendar.get(Calendar.MONTH);

        // 현재 보이는 페이지의 인덱스
        int currentPageIndex = viewPager2.getCurrentItem();

        if (itemDate <= itemCount) {
            String itemString = String.valueOf(itemDate);
            holder.daynum.setText(itemString);

            // 현재 보이는 페이지와 스와이프로 넘어온 페이지의 색상 처리
            if (position == currentPageIndex || position == swipedPage) {
                // 현재 페이지 또는 스와이프로 넘어온 페이지인 경우 daypurple로 설정
                holder.imgBanner.setBackgroundResource(R.drawable.daypurple);
                holder.daynum.setTextColor(Color.WHITE); // 폰트 색상을 white로 설정
                holder.dayweek.setTextColor(Color.WHITE); // 폰트 색상을 white로 설정
            } else {
                // 다른 날짜의 경우 daywhite로 설정
                holder.imgBanner.setBackgroundResource(R.drawable.daywhite);
                holder.daynum.setTextColor(Color.BLACK); // 폰트 색상을 기본 색상으로 설정 (예: 검정색)
                holder.dayweek.setTextColor(Color.BLACK); // 폰트 색상을 기본 색상으로 설정 (예: 검정색)
            }
        } else {
            holder.daynum.setText("");
            holder.dayweek.setText("");
        }

        // 기준 날짜로부터 요일 계산
        int dayOfWeekPosition = (systemCalendar.get(Calendar.DAY_OF_WEEK) + position - systemDate + 1) % 7;
        if (dayOfWeekPosition < 0) {
            dayOfWeekPosition += 7;
        }

        int daysInMonth;
        if (itemMonth == 1) {
            // 2월인 경우 윤년을 고려하여 처리
            if ((systemYear % 4 == 0 && systemYear % 100 != 0) || (systemYear % 400 == 0)) {
                daysInMonth = 29;
            } else {
                daysInMonth = 28;
            }
        } else if (itemMonth == 3 || itemMonth == 5 || itemMonth == 8 || itemMonth == 10) {
            daysInMonth = 30;
        } else {
            daysInMonth = 31;
        }

        if (itemDate > daysInMonth) {
            holder.dayweek.setText("");
        } else {
            String dayOfWeekString = getDayOfWeek(dayOfWeekPosition);
            holder.dayweek.setText(dayOfWeekString);
        }
    }
    // 항목의 위치(position)를 기반으로 해당 항목의 년(year) 값을 가져오는 메서드
    public int getYearFromPosition(int position) {
        // itemsToShow 리스트에서 해당 위치의 항목을 가져온 뒤, 년(year) 값을 추출하여 정수로 반환
        String item = items.get(position);
        // 예시: itemsToShow 리스트가 String으로 년-월-일 형식으로 되어 있다고 가정하고, "-"를 기준으로 잘라냄
        String[] parts = item.split("-");
        if (parts.length >= 1) {
            return Integer.parseInt(parts[0]);
        } else {
            return 0; // 예외 처리 등을 위해 0을 반환하거나 적절한 기본값을 반환
        }
    }

    // 항목의 위치(position)를 기반으로 해당 항목의 월(month) 값을 가져오는 메서드
    public int getMonthFromPosition(int position) {
        // itemsToShow 리스트에서 해당 위치의 항목을 가져온 뒤, 월(month) 값을 추출하여 정수로 반환
        String item = items.get(position);
        // 예시: itemsToShow 리스트가 String으로 년-월-일 형식으로 되어 있다고 가정하고, "-"를 기준으로 잘라냄
        String[] parts = item.split("-");
        if (parts.length >= 2) {
            return Integer.parseInt(parts[1]);
        } else {
            return 0; // 예외 처리 등을 위해 0을 반환하거나 적절한 기본값을 반환
        }
    }

    // 항목의 위치(position)를 기반으로 해당 항목의 일(dayOfMonth) 값을 가져오는 메서드
    public int getDayOfMonthFromPosition(int position) {
        // itemsToShow 리스트에서 해당 위치의 항목을 가져온 뒤, 일(dayOfMonth) 값을 추출하여 정수로 반환
        String item = items.get(position);
        // 예시: itemsToShow 리스트가 String으로 년-월-일 형식으로 되어 있다고 가정하고, "-"를 기준으로 잘라냄
        String[] parts = item.split("-");
        if (parts.length >= 3) {
            return Integer.parseInt(parts[2]);
        } else {
            return 0; // 예외 처리 등을 위해 0을 반환하거나 적절한 기본값을 반환
        }
    }
    private int startingDayOfWeek() {
        // 이전 달의 날짜를 계산하여 첫 번째 아이템의 요일을 정의
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.AUGUST); // 원하는 달(month)로 설정
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 요일 값 (일요일: 1, 월요일: 2, ... 토요일: 7)
        // ViewPager2의 첫 번째 아이템이 원하는 요일에 해당하도록 조정
        // 일요일(1)을 기준으로 잡고 시작하기 위해 (1 - dayOfWeek) 값을 반환
        return (1 - dayOfWeek + 7) % 7;
    }
    private String getDayOfWeek(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "일";
            case Calendar.MONDAY:
                return "월";
            case Calendar.TUESDAY:
                return "화";
            case Calendar.WEDNESDAY:
                return "수";
            case Calendar.THURSDAY:
                return "목";
            case Calendar.FRIDAY:
                return "금";
            case Calendar.SATURDAY:
                return "토";
            default:
                return "토";
        }
    }
}