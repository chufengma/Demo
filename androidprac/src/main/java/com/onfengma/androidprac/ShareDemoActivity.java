package com.onfengma.androidprac;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareDemoActivity extends AppCompatActivity {

    @Bind(R.id.simple_text)
    Button simpleText;
    @Bind(R.id.binary_content)
    Button binaryContent;

    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_demo);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo_share, menu);
        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider

        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

            mShareActionProvider.setShareIntent(getTextShareIntent());
        // Return true to display menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.simple_text)
    public void clickOnSimpleText() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        // intent.putExtra(Intent.EXTRA_TEXT, "simple text to send!!!");
        intent.putExtra(Intent.EXTRA_EMAIL, "578371580@qq.com");
        intent.setType("image/*");

        // simple post to system
        // startActivity(intent);

        // add a chooser
        startActivity(Intent.createChooser(intent, "选择一个吊炸天的app发送吧！"));
    }

    private Intent getTextShareIntent() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "simple text to send!!!");
        intent.setType("text/*");
        return intent;
    }

}
