package com.seniorlibs.designpattern.ooa.interfaceauth.apirequest;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/8/15
 * Mender:
 * Modify:
 * Description: ApiRequest
 * 1.功能点有两个：将 token、AppID、时间戳拼接到 URL 中，形成新的 URL；解析 URL，得到 token、AppID、时间戳等信息。
 */
public class ApiRequest {

  private String baseUrl;
  private String appID;
  private long timeStamp;
  private String token;

  public ApiRequest(String fullUrl) {

    Map<String, String> params = parse(fullUrl);
    // TODO
    String baseUrl = "";
    String appID = "";
    long timeStamp = 0L;
    String token = "";
  }

  public static ApiRequest genFakeReq(String fullUrl) {
    ApiRequest req = new ApiRequest("geekbang", "designpattern", new Date().getTime(),
        "IXIGIpJ9hdOBCyjStaDJ5Nom07g=");
    return req;
  }

  private static Map<String, String> parse(String fullUrl) {
    Map<String, String> params = new TreeMap<>();
    // TODO
    return params;
  }

  public ApiRequest(String baseUrl, String appID, long timeStamp, String token) {
    this.baseUrl = baseUrl;
    this.appID = appID;
    this.timeStamp = timeStamp;
    this.token = token;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public String getAppID() {
    return appID;
  }

  public long getTimeStamp() {
    return timeStamp;
  }

  public String getToken() {
    return token;
  }
}
