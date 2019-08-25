package com.seniorlibs.thread.synchronize;

public class ThreadB extends Thread {
    private IService mService;
    private String mParam;

    public ThreadB(IService service, String param) {
        super();
        this.mService = service;
        this.mParam = param;
    }

    @Override
    public void run() {
        super.run();
        mService.serviceMethodB(mParam);
    }
}
