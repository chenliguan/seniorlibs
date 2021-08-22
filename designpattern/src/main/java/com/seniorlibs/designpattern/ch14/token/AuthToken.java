package com.seniorlibs.designpattern.ch14.token;

import com.seniorlibs.designpattern.ch14.apirequest.ApiRequest;
import java.util.Base64;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/8/15
 * Mender:
 * Modify:
 * Description: 主页
 */
public class AuthToken {

  private static final long EXPIRE_INTERVAL = 10 * 60 * 1000L;
  private long createTime;
  private String token;
  private String originalUrl;

  public boolean isExpired() {
    return this.createTime > new Date().getTime() + EXPIRE_INTERVAL;
  }

  public boolean match(AuthToken authToken) {
    return this.token.equals(authToken.token);
  }

  public AuthToken(String originalUrl, long createTime, String token) {
    this.originalUrl = originalUrl;
    this.createTime = createTime;
    this.token = token;
  }

  public AuthToken(String token) {
    this.token = token;
  }

  public static AuthToken buildAuthToken(ApiRequest req, String password) {
    // srcStr format:
    // "GETcvm.api.qcloud.com/v2/index.php?"
    //        + "AppID=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA"
    //        + "&Timestamp=1465185768";
    String srcStr = ""; // TODO
    srcStr = "geekbang?"
        + "AppID=designpattern"
        + "&Timestamp=1465185768";
    String token = generateToken(srcStr, password);
    System.out.println("buildAuthToken: " + token);
    AuthToken authToken = new AuthToken(token);
    return authToken;
  }

  public static String generateToken(String value, String key) {
    return hmacSha1(value, key);
  }

  private static String hmacSha1(String value, String key) {
    try {
      // Get an hmac_sha1 key from the raw key bytes
      byte[] keyBytes = key.getBytes();
      SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

      // Get an hmac_sha1 Mac instance and initialize with the signing key
      Mac mac = Mac.getInstance("HmacSHA1");
      mac.init(signingKey);

      // Compute the hmac on input data bytes
      byte[] rawHmac = mac.doFinal(value.getBytes());

      byte[] result = new byte[0];
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        result = Base64.getEncoder().encode(rawHmac);
      }

      //  Covert array of Hex bytes to a String
      return new String(result, "UTF-8");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
