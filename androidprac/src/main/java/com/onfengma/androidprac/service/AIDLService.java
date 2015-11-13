package com.onfengma.androidprac.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.onfengma.androidprac.utils.Logger;

/**
 * Created by chuyifeng on 2015/11/9.
 */
public class AIDLService extends Service {

   private static class IDemoAidlInterface extends com.onfengma.androidprac.service.IDemoAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {}

        @Override
        public String getMsg() throws RemoteException {
            return "FUCK THE CODE！！！！！";
        }

        @Override
        public void sendMsg(String msg) throws RemoteException {
            Logger.i("msg:" + msg);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IDemoAidlInterface();
    }


}
