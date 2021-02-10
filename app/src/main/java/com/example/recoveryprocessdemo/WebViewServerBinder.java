package com.example.recoveryprocessdemo;

import android.os.RemoteException;
import android.text.TextUtils;

/**
 * author: xujiajia
 * created on: 2021/1/21 8:27 PM
 * description:
 * 这个类的逻辑运行在主进程
 */
public class WebViewServerBinder extends IWebViewServer.Stub {
  @Override public String getUrl() throws RemoteException {
    checkRecovery();
    return TestDataManager.getInstance().getUrl();
  }

  /*
  确认是否需要执行恢复逻辑
   */
  public void checkRecovery() {
    if (TextUtils.isEmpty(TestDataManager.getInstance().getUrl())) {
      TestDataManager.getInstance().initUrlData();
    }
  }
}
