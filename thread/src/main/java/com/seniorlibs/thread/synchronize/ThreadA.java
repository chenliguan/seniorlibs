package com.seniorlibs.thread.synchronize;

public class ThreadA extends Thread {
    private IService mService;
    private String mParam;

    public ThreadA(IService service, String param) {
        super();
        this.mService = service;
        this.mParam = param;
    }

    @Override
    public void run() {
        super.run();
        mService.serviceMethodA(mParam);
    }
}
