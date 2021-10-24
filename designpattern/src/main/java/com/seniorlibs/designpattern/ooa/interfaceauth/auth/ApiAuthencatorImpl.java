package com.seniorlibs.designpattern.ooa.interfaceauth.auth;

import com.seniorlibs.designpattern.ooa.interfaceauth.apirequest.ApiRequest;
import com.seniorlibs.designpattern.ooa.interfaceauth.storage.ICredentialStorage;
import com.seniorlibs.designpattern.ooa.interfaceauth.model.AuthToken;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/8/15
 * Mender:
 * Modify:
 * Description: API 授权认证实现
 */
public class ApiAuthencatorImpl implements IApiAuthencator {

    // 基于接口而非实现编程
    private ICredentialStorage mStorage;

    /**
     * 依赖注入
     *
     * @param storage
     */
    public ApiAuthencatorImpl(ICredentialStorage storage) {
        mStorage = storage;
    }

    @Override
    public void auth(String url) {
        ApiRequest req = ApiRequest.genFakeReq(url);
//      ApiRequest req = new ApiRequest(url);
        AuthToken clientToken = new AuthToken(req.getBaseUrl(), req.getTimeStamp(), req.getToken());
        if (clientToken.isExpired()) {
            throw new RuntimeException("Request has expired!");
        }

        String password = mStorage.getPasswordByAppID(req.getAppID());
        AuthToken serverToken = AuthToken.buildAuthToken(req, password);

        if (!clientToken.match(serverToken)) {
            throw new RuntimeException("Client token does not match!");
        }

        System.out.println("pass auth!");
    }
}
