package com.onfengma.androidprac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TaskAffiniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_affini);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task_affini, menu);
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

    public void onClick(View view) {
        Class x = null;
        if (view.getId() == R.id.affini1) {
            x = Affini1Activity.class;
        } else if (view.getId() == R.id.affini2) {
            x = Affini2Activity.class;
        } else {
            Intent intent = new Intent("com.demo.demo.demo");
            startActivity(intent);
            return;
        }
        startActivity(new Intent(this, x));
    }
}
