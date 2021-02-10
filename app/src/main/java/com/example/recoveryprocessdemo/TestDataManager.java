package com.example.recoveryprocessdemo;

/**
 * author: xujiajia
 * created on: 2021/1/21 5:21 PM
 * description:
 */
public class TestDataManager {

  //constants
  private static final String TEST_URL = "https://xujiajia.blog.csdn.net/";
  //data
  private String url = null;

  private static class Host {
    private static final TestDataManager instance = new TestDataManager();
  }

  private TestDataManager() {
  }

  public static TestDataManager getInstance() {
    return Host.instance;
  }

  /*
  实际操作的时候，此处有可能是网络、io等操作
   */
  public void initUrlData() {
    url = TEST_URL;
  }

  public String getUrl() {
    return url;
  }
}
