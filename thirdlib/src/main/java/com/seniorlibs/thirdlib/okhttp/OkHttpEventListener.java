package com.seniorlibs.thirdlib.okhttp;

import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpEventListener extends EventListener {

    public static final String TAG = "OkHttpEventListener";

    public static final Factory FACTORY = new Factory() {
        @Override
        public EventListener create(Call call) {
            return new OkHttpEventListener();
        }
    };

    OkHttpEvent okHttpEvent;

    public OkHttpEventListener() {
        super();
        okHttpEvent = new OkHttpEvent();
    }

    @Override
    public void callStart(Call call) {
        super.callStart(call);
        Log.i(TAG, "callStart");
    }

    @Override
    public void dnsStart(Call call, String domainName) {
        super.dnsStart(call, domainName);
        okHttpEvent.dnsStartTime = System.currentTimeMillis();
        Log.i(TAG, "dnsStart");
    }

    @Override
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        super.dnsEnd(call, domainName, inetAddressList);
        okHttpEvent.dnsEndTime = System.currentTimeMillis();
        Log.i(TAG, "dnsEnd：" + (okHttpEvent.dnsStartTime - okHttpEvent.dnsEndTime));
    }

    @Override
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        super.connectStart(call, inetSocketAddress, proxy);
        Log.i(TAG, "connectStart");
    }

    @Override
    public void secureConnectStart(Call call) {
        super.secureConnectStart(call);
        Log.i(TAG, "secureConnectStart");
    }

    @Override
    public void secureConnectEnd(Call call, @Nullable Handshake handshake) {
        super.secureConnectEnd(call, handshake);
        Log.i(TAG, "secureConnectEnd");
    }

    @Override
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol);
        Log.i(TAG, "connectEnd");
    }

    @Override
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol, IOException ioe) {
        super.connectFailed(call, inetSocketAddress, proxy, protocol, ioe);
        Log.e(TAG, "connectFailed");
    }

    @Override
    public void connectionAcquired(Call call, Connection connection) {
        super.connectionAcquired(call, connection);
        Log.i(TAG, "connectionAcquired");
    }

    @Override
    public void connectionReleased(Call call, Connection connection) {
        super.connectionReleased(call, connection);
        Log.i(TAG, "connectionReleased");
    }

    @Override
    public void requestHeadersStart(Call call) {
        super.requestHeadersStart(call);
        Log.i(TAG, "requestHeadersStart");
    }

    @Override
    public void requestHeadersEnd(Call call, Request request) {
        super.requestHeadersEnd(call, request);
        Log.i(TAG, "requestHeadersEnd");
    }

    @Override
    public void requestBodyStart(Call call) {
        super.requestBodyStart(call);
        Log.i(TAG, "requestBodyStart");
    }

    @Override
    public void requestBodyEnd(Call call, long byteCount) {
        super.requestBodyEnd(call, byteCount);
        Log.i(TAG, "requestBodyEnd，byteCount：" + byteCount);
    }

    @Override
    public void responseHeadersStart(Call call) {
        super.responseHeadersStart(call);
        Log.i(TAG, "responseHeadersStart");
    }

    @Override
    public void responseHeadersEnd(Call call, Response response) {
        super.responseHeadersEnd(call, response);
        Log.i(TAG, "responseHeadersEnd：" + response.message());
    }

    @Override
    public void responseBodyStart(Call call) {
        super.responseBodyStart(call);
        Log.i(TAG, "responseBodyStart");
    }

    @Override
    public void responseBodyEnd(Call call, long byteCount) {
        super.responseBodyEnd(call, byteCount);
        okHttpEvent.responseBodySize = byteCount;
        Log.i(TAG, "responseBodyEnd + byteCount：" + byteCount);
    }

    @Override
    public void callEnd(Call call) {
        super.callEnd(call);
        okHttpEvent.apiSuccess = true;
        Log.i(TAG, "callEnd ");
    }


    @Override
    public void callFailed(Call call, IOException ioe) {
        Log.i(TAG, "callFailed ");
        super.callFailed(call, ioe);
        okHttpEvent.apiSuccess = false;
        okHttpEvent.errorReason = Log.getStackTraceString(ioe);
        Log.e(TAG, "reason ：" + ioe.getMessage() + " " + okHttpEvent.errorReason);
    }
}
