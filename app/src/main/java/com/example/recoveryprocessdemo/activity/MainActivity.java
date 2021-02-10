package com.example.recoveryprocessdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.recoveryprocessdemo.R;
import com.example.recoveryprocessdemo.TestDataManager;

public class MainActivity extends Activity  {

  //ui
  private TextView tvUrl;
  private Button btnStartWebView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initView();
    initData();
  }

  private void initView() {
    tvUrl = findViewById(R.id.tv_url_main);
    btnStartWebView = findViewById(R.id.btn_start_web_main);

    btnStartWebView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        startActivity(intent);
      }
    });
  }

  private void initData() {
    TestDataManager.getInstance().initUrlData();
    tvUrl.setText(TestDataManager.getInstance().getUrl());
  }


}