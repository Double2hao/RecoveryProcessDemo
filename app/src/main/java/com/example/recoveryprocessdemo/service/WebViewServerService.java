package com.example.recoveryprocessdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.recoveryprocessdemo.WebViewServerBinder;

import androidx.annotation.Nullable;

/**
 * author: xujiajia
 * created on: 2021/1/21 6:50 PM
 * description:
 */
public class WebViewServerService extends Service {

  @Nullable @Override public IBinder onBind(Intent intent) {
    return new WebViewServerBinder();
  }

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    return super.onStartCommand(intent, flags, startId);
  }
}
