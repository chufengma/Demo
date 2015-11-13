package com.onfengma.androidprac.service;

import android.app.Service;
import android.content.Intent;
import android.os.*;

import com.onfengma.androidprac.utils.Logger;

public class MessagerService extends Service {

    private static class MessageHander extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Logger.i(android.os.Process.myPid() + ":" + msg.getData().getString("msg"));
        }

    }

    public MessagerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i("message created : " + android.os.Process.myPid());
    }

    private Messenger messenger = new Messenger(new MessageHander());

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }



}
