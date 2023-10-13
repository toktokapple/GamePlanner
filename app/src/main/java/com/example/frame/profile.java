package com.example.frame;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class profile extends Fragment {
    private static final int REQUEST_CODE = 100;
    private ImageView ProfileEdit, ProfileEllipse;
    private Uri uri;
    private View view;

    public profile() {
    }

    private static final String PREFS_PROFILE_IMAGE = "prefs_profile_image";
//저장
    private void saveProfileImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFS_PROFILE_IMAGE, Base64.encodeToString(byteArray, Base64.DEFAULT));
        editor.apply();
    }

    private Bitmap loadProfileImage() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String encodedImage = sharedPreferences.getString(PREFS_PROFILE_IMAGE, null);

        if (encodedImage != null) {
            byte[] byteArray = Base64.decode(encodedImage, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }

        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
        ProfileEdit = view.findViewById(R.id.ProfileEdit);
        ProfileEllipse = view.findViewById(R.id.ProfileEllipse);

        // 프로필 사진을 불러와서 설정
        Bitmap savedProfileImage = loadProfileImage();
        if (savedProfileImage != null) {
            ProfileEllipse.setImageBitmap(savedProfileImage);
            setMyProfileImage(savedProfileImage); // 이 부분을 수정해야 함
            // fourthfragment의 MyProfileEllipse 이미지 설정
            Fragment fourthfragment = requireActivity().getSupportFragmentManager().findFragmentByTag("fourthfragment");
            if (fourthfragment instanceof fourthfragment) {
                ((fourthfragment) fourthfragment).setMyProfileImage(savedProfileImage);
            }
        }
        ProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityResult.launch(intent);
            }
        });
        return view;
    }
    ActivityResultLauncher<Intent> startActivityResult=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK&&result.getData()!=null){
                        uri=result.getData().getData();
                        try {
                            // 먼저, 비트맵을 가져옵니다.
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
                            // 비트맵을 원형으로 자르기 위해 Bitmap 객체를 생성합니다.
                            Bitmap resizedBitmap = getCircleBitmap(bitmap);
                            // 이미지뷰에 원형 이미지를 설정합니다.
                            ProfileEllipse.setImageBitmap(resizedBitmap);

                            // fourthfragment의 MyProfileEllipse 이미지뷰에도 원형 이미지 적용
                            setMyProfileImage(resizedBitmap);
                            // 프로필 사진 저장
                            saveProfileImage(resizedBitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
    @Override
    public void onResume() {
        super.onResume();

        // fourthfragment의 MyProfileEllipse 이미지 설정
        Bitmap savedProfileImage = loadProfileImage();
        if (savedProfileImage != null) {
            Fragment fourthfragment = requireActivity().getSupportFragmentManager().findFragmentByTag("fourthfragment");
            if (fourthfragment instanceof fourthfragment) {
                ((fourthfragment) fourthfragment).setMyProfileImage(savedProfileImage);
            }
        }
    }
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        // 비트맵 객체를 정사각형으로 만듭니다.
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int diameter = Math.min(width, height);
        Bitmap croppedBitmap = Bitmap.createBitmap(bitmap, (width - diameter) / 2, (height - diameter) / 2, diameter, diameter);

        // 원형으로 자른 비트맵을 만듭니다.
        Bitmap circleBitmap = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circleBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawCircle(diameter / 2, diameter / 2, diameter / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(croppedBitmap, 0, 0, paint);

        return circleBitmap;
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
                fourthfragment fourthfragment = new fourthfragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fourthfragment)
                        .commit();
            }
        });
    }
    private void setMyProfileImage(Bitmap bitmap) {
        fourthfragment fragment = (fourthfragment) requireActivity().getSupportFragmentManager().findFragmentByTag("fourthfragment");
        if (fragment != null) {
            fragment.MyProfileEllipse.setImageBitmap(bitmap);
        }
    }
}