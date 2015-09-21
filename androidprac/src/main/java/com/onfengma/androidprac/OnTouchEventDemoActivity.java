package com.onfengma.androidprac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.onfengma.androidprac.views.DemoButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnTouch;

public class OnTouchEventDemoActivity extends AppCompatActivity {

    @Bind(R.id.demo_button)
    DemoButton demoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch_event_demo);
        ButterKnife.bind(this);
    }

    @OnTouch(R.id.demo_button)
    boolean onTouch() {
        Toast.makeText(OnTouchEventDemoActivity.this, "Button on touched by onTouchListener !", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void onClick(View view) {
        // Toast.makeText(this, "CLicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_on_touch_event_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
