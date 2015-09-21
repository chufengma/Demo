package com.onfengma.androidprac;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.onfengma.androidprac.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends AppCompatActivity {

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding);
        ActivityDataBindingBinding activityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        user.setAge(12);
        user.setName("chufengma");
        activityDataBindingBinding.setUser(user);
    }

    public void onClick(View view) {
        user.setName("fengma is greate");
        user.setAge(123);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data_binding, menu);
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
