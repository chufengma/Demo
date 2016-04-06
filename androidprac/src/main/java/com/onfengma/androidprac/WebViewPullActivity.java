package com.onfengma.androidprac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class WebViewPullActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_pull);
        ((WebView)findViewById(R.id.web)).loadUrl("http://www.2cto.com/kf/201402/279066.html");
    }
}
