package com.onfengma.androidprac;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.onfengma.androidprac.service.AIDLService;
import com.onfengma.androidprac.service.IDemoAidlInterface;
import com.onfengma.androidprac.utils.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IPCDemoActivity extends AppCompatActivity {

    @Bind(R.id.messenger_view)
    Button messengerView;

    private Messenger messenger;

    private IDemoAidlInterface iDemoAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipcdemo);
        ButterKnife.bind(this);
        boolean success = bindService(new Intent(this, AIDLService.class), serviceConnectionAIDL, BIND_AUTO_CREATE);
    }

    @OnClick(R.id.messenger_view)
    public void clickOnMessenger() {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("msg", "from : " + android.os.Process.myPid());
        message.setData(bundle);
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.aidl_view)
    public void clickOnAIDL() {
        try {
            Logger.i(iDemoAidlInterface.getMsg());
            iDemoAidlInterface.sendMsg("hello " + "from : " + android.os.Process.myPid());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection serviceConnectionAIDL = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iDemoAidlInterface =  IDemoAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iDemoAidlInterface = null;
        }
    };

    private ServiceConnection serviceConnectionMessenger = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
        }
    };


}
