package com.onfengma.androidprac;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class EnterActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        listView = (ListView) findViewById(R.id.list);
        adapter = new ListAdapter(this, android.R.layout.simple_list_item_1, getEnters());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private Enter[] getEnters() {
       return  Enter.values();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_enter, menu);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Enter enter = adapter.getItem(position);
        startActivity(new Intent(this, enter.getClazz()));
    }

    class ListAdapter extends ArrayAdapter<Enter> {

        public ListAdapter(Context context, int resource, Enter[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view =  super.getView(position, convertView, parent);
            view.setTag(getItem(position));
            return view;
        }
    }

}
