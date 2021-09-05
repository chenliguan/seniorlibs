package com.seniorlibs.designpattern.ch14.auth;

import com.seniorlibs.designpattern.ch14.apirequest.ApiRequest;
import com.seniorlibs.designpattern.ch14.storage.ICredentialStorage;
import com.seniorlibs.designpattern.ch14.token.AuthToken;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/8/15
 * Mender:
 * Modify:
 * Description: API 授权认证实现
 */
public class DefaultIApiAuthencatorImpl implements IApiAuthencator {

    private ICredentialStorage mStorage;

    public DefaultIApiAuthencatorImpl(ICredentialStorage storage) {
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
