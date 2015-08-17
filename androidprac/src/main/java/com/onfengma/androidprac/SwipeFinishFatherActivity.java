package com.onfengma.androidprac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.onfengma.androidprac.utils.Logger;

import java.util.ArrayList;

public class SwipeFinishFatherActivity extends AppCompatActivity implements BusListener {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_finish_father);
        textView = (TextView) findViewById(R.id.time);

        Bus.instance().register(this);
//        new CountDownTimer(1000000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                textView.setText(System.currentTimeMillis() + "");
//            }
//
//            @Override
//            public void onFinish() {
//            }
//        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_swipe_finish_father, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        startActivity(new Intent(this, SwipeFinishActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.i("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.i("onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.i("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.i("onResume");
    }

    @Override
    public void onChanged(int data) {
        WindowManager.LayoutParams lp = (WindowManager.LayoutParams) getWindow().getDecorView().getLayoutParams();
        lp.x = -data;
        getWindow().getDecorView().setLayoutParams(lp);
        textView.setText(System.currentTimeMillis() + ":" + data);
    }

    public static class Bus {

        private static Bus bus;
        private int data;

        public static Bus instance() {
            if (bus == null) {
                bus = new Bus();
            }
            return bus;
        }

        final ArrayList<BusListener> linsteners = new ArrayList<>();

        public void register(BusListener object) {
            linsteners.add(object);
        }

        public void unregister(BusListener obj) {
            linsteners.remove(obj);
        }

        public void sendData(int data) {
            this.data = data;
            notifyData();
        }

        private void notifyData() {
            for (BusListener listener : linsteners) {
                listener.onChanged(data);
            }

        }

    }

}
