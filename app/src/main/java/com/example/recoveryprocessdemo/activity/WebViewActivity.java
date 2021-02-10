package com.example.recoveryprocessdemo.activity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.recoveryprocessdemo.IWebViewServer;
import com.example.recoveryprocessdemo.R;
import com.example.recoveryprocessdemo.service.WebViewServerService;

import androidx.annotation.Nullable;

/**
 * author: xujiajia
 * created on: 2021/1/21 4:08 PM
 * description:
 */
public class WebViewActivity extends Activity {

  //ui
  private Button btnDesc;
  private Button btnClear;
  private WebView wvContent;
  //data
  private IWebViewServer webViewServer;
  private ServiceConnection serviceConnection = new ServiceConnection() {
    @Override public void onServiceConnected(ComponentName name, IBinder service) {
      try {
        webViewServer = IWebViewServer.Stub.asInterface(service);
        webViewServer.asBinder().linkToDeath(new IBinder.DeathRecipient() {
          @Override public void binderDied() {
            startWebViewServerService();//如果断开就重启
          }
        }, 0);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }

    @Override public void onServiceDisconnected(ComponentName name) {

    }
  };

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview);

    initView();
    startWebViewServerService();
  }

  private void initView() {
    btnDesc = findViewById(R.id.btn_desc_web);
    btnClear = findViewById(R.id.btn_clear_web);
    wvContent = findViewById(R.id.wv_content_web);

    btnDesc.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (webViewServer == null) {
          return;
        }
        try {
          wvContent.loadUrl(webViewServer.getUrl());
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }
    });
    btnClear.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        wvContent.loadUrl("");
      }
    });
  }

  private void startWebViewServerService() {
    Intent intent = new Intent(this, WebViewServerService.class);
    bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
  }
}
