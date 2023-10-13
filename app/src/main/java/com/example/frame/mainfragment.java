package com.example.frame;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class mainfragment extends Fragment{
    private BottomSheetDialog bottomSheetDialog;
    private Context mContext;
    private ImageView addicon;
    private View bottomSheetView;
    private int currentPage = 0;
    private FrameLayout dayFragmentContainer;
    private boolean isViewCreated = false; // onCreateView가 호출되었는지 여부를 확인하는 변수
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private CalendarView macar;
    private ViewPager2 viewpager2;
    private long lastClickTime = 0;
    // Add this member variable
    private Calendar baseCalendar;
    private SparseArray<Fragment> dayFragments = new SparseArray<>();

    private static final long DOUBLE_CLICK_TIME_DELTA = 300; // Milliseconds
    // 클래스 멤버 변수로 shouldShowNotification 선언
    private boolean shouldShowNotification = false;

    // 클래스 멤버 변수로 선택한 시간 값을 저장할 변수들 선언
    private int selectedMonth = 0;
    private int selectedDay = 0;
    private int selectedHour = 0;
    private int selectedMinute = 0;
    private boolean isPM = false;
    private static final String TAG = "FCM_TEST";
    public mainfragment(){
    }
    // Fragment를 생성하는 정적 팩토리 메서드
    public static mainfragment newInstance() {
        return new mainfragment();
    }
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            // 스크롤 상태 변경 시 동작
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            // 스크롤 시 동작
        }
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mainfragment, container, false);

        // FCM 토큰 가져오기
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("FCM", "Fetching FCM registration token failed", task.getException());
                return;
            }

            // Get new FCM registration token
            String token = task.getResult();
            Log.d("FCM", "FCM registration token: " + token);
        });

        bottomSheetDialog = new BottomSheetDialog(requireContext());
        dayFragmentContainer = rootView.findViewById(R.id.DayFragmentContainer);
        macar = rootView.findViewById(R.id.macar);
        macar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                // Double click detected
            } else {
                // Single click detected
                loadDayFragment(dayOfMonth);
            }
            lastClickTime = clickTime;
        });
        viewpager2 = rootView.findViewById(R.id.viewpager2);
        // onViewCreated 메서드 내부에서
        RecyclerView recyclerView = (RecyclerView) viewpager2.getChildAt(0);

        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(onScrollListener);
        }

        // MyAdapter에 아이템을 추가합니다.
        List<String> newItems = new ArrayList<>();
        newItems.add("item1");
        newItems.add("item2");
        newItems.add("item3");
        newItems.add("item4");
        newItems.add("item5");
        newItems.add("item6");
        newItems.add("item7");
        newItems.add("item8");
        newItems.add("item9");
        newItems.add("item10");
        newItems.add("item11");
        newItems.add("item12");
        newItems.add("item13");
        newItems.add("item14");
        newItems.add("item15");
        newItems.add("item16");
        newItems.add("item17");
        newItems.add("item18");
        newItems.add("item19");
        newItems.add("item20");
        newItems.add("item21");
        newItems.add("item22");
        newItems.add("item23");
        newItems.add("item24");
        newItems.add("item25");
        newItems.add("item26");
        newItems.add("item27");
        newItems.add("item28");
        newItems.add("item29");
        newItems.add("item30");
        newItems.add("item31");

        // 현재 날짜를 가져옵니다.
        Calendar currentCalendar = Calendar.getInstance();
        int currentMonth = currentCalendar.get(Calendar.MONTH) + 1;
        int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
        // 현재 월의 시작 날짜(1일)와 현재 일을 가져옵니다.
        int startDayOfMonth = 1;
        int maxItemCount;
        if (currentMonth == 2) {
            int currentYear = currentCalendar.get(Calendar.YEAR);
            if (currentYear % 4 == 0 && (currentYear % 100 != 0 || currentYear % 400 == 0)) {
                maxItemCount = 29;
            } else {
                maxItemCount = 28;
            }
        } else if (currentMonth == 4 || currentMonth == 6 || currentMonth == 9 || currentMonth == 11) {
            maxItemCount = 30;
        } else {
            maxItemCount = 31;
        }
        List<String> itemsToShow = new ArrayList<>();
        for (int i = 1; i <= maxItemCount; i++) {
            itemsToShow.add("item" + i);
        }
        adapter = new MyAdapter(requireContext(), itemsToShow, viewpager2,maxItemCount);
        viewpager2.setAdapter(adapter);

        viewpager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewpager2.setOffscreenPageLimit(2);


        // 현재 일에 해당하는 아이템 위치를 계산하여 해당 위치로 ViewPager2를 스크롤합니다.
        int targetPosition = currentDay - startDayOfMonth;
        viewpager2.post(() -> {
            viewpager2.setCurrentItem(targetPosition, false);
            // 앱 실행 시 현재 보이는 페이지에 대해 loadDayFragment 호출
            int dayOfMonth = calculateDayOfMonthFromPosition(viewpager2.getCurrentItem());
            loadDayFragment(dayOfMonth);
        });

        // ViewPager2 초기화
        viewpager2 = rootView.findViewById(R.id.viewpager2);
        float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        viewpager2.setPageTransformer((page, position) -> {
            float myOffset = position * -(2 * pageOffset + pageMargin);
            if (position < -2) {
                page.setTranslationX(-myOffset);
            } else if (position <= 2) {
                float scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f));
                page.setTranslationX(myOffset);
                page.setScaleY(scaleFactor);
                page.setAlpha(scaleFactor);
            } else {
                page.setAlpha(0f);
                page.setTranslationX(myOffset);
            }
        });
        // viewPager2의 클릭 이벤트 리스너를 설정합니다.
        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            private long lastClickTime = 0;
            private final long DOUBLE_CLICK_TIME_DELTA = 300; // 더블 클릭 간격(밀리초)

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 스크롤 시 동작할 로직 (필요에 따라 구현)
            }

            @Override
            public void onPageSelected(int position) {
                // 뷰페이저의 아이템(페이지)를 선택했을 때 동작할 로직
                if (position == 1) { // 1페이지 (position은 0부터 시작)
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                        // 더블 클릭으로 간주하여 showBottomSheetDialog 호출
                        // 실시간 시스템값을 가져오기 위해 Calendar 클래스를 사용합니다.
                        Calendar systemCalendar = Calendar.getInstance();
                        int year = systemCalendar.get(Calendar.YEAR);
                        int month = systemCalendar.get(Calendar.MONTH) + 1; // 월은 0부터 시작하므로 1을 더해줍니다.
                        int dayOfMonth = calculateDayOfMonthFromPosition(viewpager2.getCurrentItem());
                    } else {
                        // 단일 클릭으로 간주하여 loadDayFragment 호출
                        int dayOfMonth = calculateDayOfMonthFromPosition(viewpager2.getCurrentItem());

                        // 현재 선택한 페이지와 로드된 페이지가 같은지 확인하여 중복 로드를 방지합니다.
                        if (dayOfMonth != currentPage) {
                            loadDayFragment(dayOfMonth);
                            currentPage = dayOfMonth;
                        }
                    }
                    lastClickTime = currentTime;
                }
            }


            @Override
            public void onPageScrollStateChanged(int state) {
                // 페이지 스크롤 상태 변경 시 동작할 로직 (필요에 따라 구현)
            }
        });
        // ViewPager2의 페이지 변경 이벤트를 감지하는 리스너 설정
        baseCalendar = Calendar.getInstance();
        isViewCreated=true;
        return rootView;
    }
    // position으로부터 해당 날짜의 일(dayOfMonth)을 계산하는 메서드
    private int calculateDayOfMonthFromPosition(int position) {
        // 당월 1일의 Calendar 객체 생성
        Calendar baseCalendar = Calendar.getInstance();
        baseCalendar.set(Calendar.DAY_OF_MONTH, 1);

        // position만큼 날짜를 더해줌
        baseCalendar.add(Calendar.DATE, position);

        // 계산된 날짜의 일(dayOfMonth) 반환
        return baseCalendar.get(Calendar.DAY_OF_MONTH);
    }
    private void loadDayFragment(int dayOfMonth) {
        if (dayOfMonth != currentPage) {
            // 이전 프래그먼트 제거
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            Fragment previousFragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.DayFragmentContainer);
            if (previousFragment != null) {
                transaction.remove(previousFragment);
            }

            // 선택한 dayOfMonth에 해당하는 프래그먼트를 찾습니다.
            Fragment dayFragment = dayFragments.get(dayOfMonth);

            // 프래그먼트가 아직 생성되지 않았다면, 새로 생성하고 배열에 저장합니다.
            if (dayFragment == null) {
                switch (dayOfMonth) {
                    case 1:
                        dayFragment = new day1();
                        break;
                    case 2:
                        dayFragment = new day2();
                        break;
                    case 3:
                        dayFragment = new day3();
                        break;
                    // 필요한 경우 다른 날짜에 대한 case를 추가합니다.
                    default:
                        dayFragment = new DayDefaultFragment();
                        break;
                }
                dayFragments.put(dayOfMonth, dayFragment);
            }

            // 새로운 프래그먼트 추가
            transaction.add(R.id.DayFragmentContainer, dayFragment);
            transaction.commit();
            currentPage = dayOfMonth; // currentPage를 업데이트합니다.
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 프래그먼트가 뷰를 파괴할 때 dayFragments 배열을 초기화합니다.
        dayFragments.clear();
    }
    public void showBottomSheetDialog(Activity activity,int year, int month, int dayOfMonth) {
        if(mContext==null) {
            // 이미 BottomSheetDialog가 생성되어 있는 경우 띄우기만 하고 종료합니다.
            if (bottomSheetDialog != null && bottomSheetDialog.isShowing()) {
                return;
            }

            if (bottomSheetView == null) {
                bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_layout, null);

                Spinner ampmSpinner = bottomSheetView.findViewById(R.id.ampmSpinner);

                ArrayAdapter<CharSequence> ampmAdapter = ArrayAdapter.createFromResource(requireContext(),
                        R.array.ampm_options, android.R.layout.simple_spinner_item);
                ampmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ampmSpinner.setAdapter(ampmAdapter);

                NumberPicker monthPicker = bottomSheetView.findViewById(R.id.monthPicker);
                NumberPicker dayPicker = bottomSheetView.findViewById(R.id.dayPicker);
                NumberPicker hourPicker = bottomSheetView.findViewById(R.id.hourPicker);
                NumberPicker minutePicker = bottomSheetView.findViewById(R.id.minutePicker);

                monthPicker.setMinValue(1);
                monthPicker.setMaxValue(12);
                monthPicker.setValue(month + 1);

                dayPicker.setMinValue(1);
                dayPicker.setMaxValue(31);
                dayPicker.setValue(dayOfMonth);

                hourPicker.setMinValue(1);
                hourPicker.setMaxValue(12);
                hourPicker.setValue(month + 1);

                minutePicker.setMinValue(0);
                minutePicker.setMaxValue(59);
                minutePicker.setValue(dayOfMonth);

                Button savebutton = bottomSheetView.findViewById(R.id.savebutton);
                Switch alarmswitch = bottomSheetView.findViewById(R.id.alarmswitch);
                // 버튼 클릭 리스너 등록
                savebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 사용자가 선택한 값들을 가져옴
                        int selectedMonth = monthPicker.getValue();
                        int selectedDay = dayPicker.getValue();
                        int selectedHour = hourPicker.getValue();
                        int selectedMinute = minutePicker.getValue();
                        boolean isPm = ampmSpinner.getSelectedItem().toString().equals("오후");

                        // 오후 선택시 시간 값에 12를 더함
                        if (isPm) {
                            selectedHour += 12;
                        }

                        // 스위치 상태를 가져옴
                        boolean isSwitchOn = alarmswitch.isChecked();

                        // 푸시 알림 설정
                        if (isSwitchOn) {
                            setPushNotification(selectedMonth, selectedDay, selectedHour, selectedMinute);
                        }
                        bottomSheetDialog.dismiss();
                    }
                });
            }
            // bottomSheetView가 다른 뷰에 속해 있다면 먼저 제거한 후 BottomSheetDialog를 보여줍니다.
            ViewGroup parentViewGroup = (ViewGroup) bottomSheetView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeView(bottomSheetView);
            }
            bottomSheetDialog = new BottomSheetDialog(requireContext());
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        }
    }
    private void setPushNotification(int month, int day, int hour, int minute) {
        // 1. 알림을 생성할 PendingIntent 생성

        Intent notificationIntent = new Intent(requireContext(), YourNotificationReceiver.class);
        notificationIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                requireContext(),
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_IMMUTABLE
        );

        // 2. 선택한 날짜와 시간으로 Calendar 객체 생성
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1); // month는 0부터 시작하므로 1을 빼줍니다.
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        // 3. AlarmManager를 이용하여 알림 설정
        AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        pendingIntent
                );
            } else {
                alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        pendingIntent
                );
            }
        }

        // 4. NotificationManager를 이용하여 알림 채널 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "your_channel_id"; // 알림 채널 ID
            CharSequence channelName = "Your Channel Name"; // 알림 채널 이름
            String channelDescription = "Your Channel Description"; // 알림 채널 설명
            int importance = NotificationManager.IMPORTANCE_HIGH; // 알림 중요도 (높음)

            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setDescription(channelDescription);

            NotificationManager notificationManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

    }
}
