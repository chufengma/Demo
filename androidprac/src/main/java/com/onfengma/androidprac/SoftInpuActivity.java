package com.onfengma.androidprac;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SoftInpuActivity extends AppCompatActivity {

    @Bind(R.id.scrollView)
    ScrollView scrollView;
    @Bind(R.id.text)
    EditText text;

    /**
     * 1. TextView imeOptions
     * 2. InputMethod Visibility
     *   1> visibility of InputMethod
     *   2> resize about main window
     * 3. InputMethodManager
     *   3 parts: 1> inputMethodManager 2> input method 3> client applications
     */

    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_inpu);
        ButterKnife.bind(this);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Toast.makeText(SoftInpuActivity.this, "DONE id DONE", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

//        text.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                inputMethodManager.hideSoftInputFromInputMethod(text.getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
//            }
//        }, 1000);

    }

}
