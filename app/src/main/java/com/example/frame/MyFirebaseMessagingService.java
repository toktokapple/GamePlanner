package com.example.frame;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // FCM 메시지를 수신했을 때 실행되는 부분
        // 알림을 표시하거나 데이터를 처리하는 등의 작업을 수행할 수 있습니다.
    }
}
