package com.onfengma.androidprac;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.onfengma.androidprac.views.PresentPopupWindow;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WindowActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    @Bind(R.id.text2)
    Button text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        textView = (TextView) findViewById(R.id.text);
//        textView.setOnClickListener(this);
//
//        View view = new View(this);
//        view.setBackgroundColor(Color.BLUE);
//
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_TOAST);
//        layoutParams.height = 400;
//        layoutParams.width = 400;
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
//        getWindowManager().addView(view, layoutParams);
    }

    @OnClick(R.id.text2)
    public void clickShowDialog() {
        PresentPopupWindow window = new PresentPopupWindow(this);
        window.show();
    }

    private int getStatusBarHeight() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View view) {
        Dialog dialog = new Dialog(this, R.style.dialog);
        dialog.setContentView(R.layout.custom_dialog_content);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;

        int[] position = new int[2];
        view.getLocationOnScreen(position);

        layoutParams.x = 0;
        layoutParams.y = 0;

//        dialog.getWindow().getDecorView().setPadding(0,0,0,0);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
