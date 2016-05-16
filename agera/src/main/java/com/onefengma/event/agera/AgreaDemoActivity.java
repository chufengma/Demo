package com.onefengma.event.agera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.agera.Updatable;

public class AgreaDemoActivity extends AppCompatActivity implements Updatable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrea_demo);
    }

    @Override
    public void update() {

    }
}
