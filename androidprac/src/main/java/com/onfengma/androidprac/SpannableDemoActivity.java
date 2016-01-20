package com.onfengma.androidprac;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpannableDemoActivity extends AppCompatActivity {

    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_demo);
        ButterKnife.bind(this);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        String str = "没错,我就是测试字符串:";
        SpannableString s1 = new SpannableString(str + "1\n");
        s1.setSpan(new ForegroundColorSpan(Color.RED), 3, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        builder.append(s1);
        builder.append("aaaaa");
        builder.insert(6, "(我是插入的)");

        text.setText(builder);
    }
}
