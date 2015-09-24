package com.onfengma.androidprac;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class StorageDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_demo);

        getPreferences(Context.MODE_APPEND).edit().putString("demo", "demo").commit();
        getSharedPreferences("sharedPreferences", Context.MODE_APPEND).edit().putString("share_demo", "share_demo").commit();

        String innerFileDirPath = getFilesDir().getAbsolutePath();
        String extraFileDirPath = getExternalFilesDir(null).getAbsolutePath();

        Toast.makeText(this, innerFileDirPath + ":" + extraFileDirPath + ":", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_storage_demo, menu);
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
}
