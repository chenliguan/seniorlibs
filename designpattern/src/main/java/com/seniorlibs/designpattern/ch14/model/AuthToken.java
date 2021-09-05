package com.seniorlibs.designpattern.ch14.model;

import com.seniorlibs.designpattern.ch14.apirequest.ApiRequest;
import com.seniorlibs.designpattern.ch14.utils.AuthUtils;

import java.util.Date;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/8/15
 * Mender:
 * Modify:
 * Description: 授权 Token 实体
 */
public class AuthToken {

    private static final long EXPIRE_INTERVAL = 10 * 60 * 1000L;

    private long createTime;
    private String token;
    private String originalUrl;

    public AuthToken(String originalUrl, long createTime, String token) {
        this.originalUrl = originalUrl;
        this.createTime = createTime;
        this.token = token;
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public boolean isExpired() {
        return this.createTime > new Date().getTime() + EXPIRE_INTERVAL;
    }

    public boolean match(AuthToken authToken) {
        return this.token.equals(authToken.token);
    }


    public static AuthToken buildAuthToken(ApiRequest req, String password) {
        // srcStr format:
        // "GETcvm.api.qcloud.com/v2/index.php?"
        //        + "AppID=AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA"
        //        + "&Timestamp=1465185768";
        String srcStr = ""; // TODO
        srcStr = "geekbang?AppID=designpattern&Timestamp=1465185768";
        String token = AuthUtils.generateToken(srcStr, password);
        System.out.println("buildAuthToken: " + token);
        AuthToken authToken = new AuthToken(token);
        return authToken;
    }
}
