package com.onfengma.androidprac;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewTestActivity extends AppCompatActivity {

    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.show)
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.text)
    public void onTextClick() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(text, "translationX", 100);
        animator.setDuration(2000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                showPosition();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private void showPosition() {
        int[] position = new int[2];
        text.getLocationInWindow(position);

        int[] position2 = new int[2];
        text.getLocationOnScreen(position2);
        show.setText("top:" + text.getTop() +
                "\nx:" + text.getX() +
                "\ntransX:" + text.getTranslationX() +
                "\nLocationX in window" + position[0] +
                "\nLocationX in screen" + position2[0]
                + "\nscrollX:" + text.getScrollX());
    }


}
