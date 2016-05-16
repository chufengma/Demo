package com.onfengma.androidprac;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.onfengma.androidprac.utils.ViewUtils;

public class ShakingDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaking_demo);

        final TextView textView = (TextView) findViewById(R.id.dot);
        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                float delta = ViewUtils.dipToPixels(20, getResources().getDisplayMetrics());
//                PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
//                        Keyframe.ofFloat(0f, 0),
//                        Keyframe.ofFloat(.10f, -delta),
//                        Keyframe.ofFloat(.26f, delta),
//                        Keyframe.ofFloat(.42f, -delta),
//                        Keyframe.ofFloat(.58f, delta),
//                        Keyframe.ofFloat(.74f, -delta),
//                        Keyframe.ofFloat(.90f, delta),
//                        Keyframe.ofFloat(1f, 0f)
//                );
//
//                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(textView, pvhTranslateX);
//                objectAnimator.setDuration(1000);
//
//                PropertyValuesHolder pvhTranslateX2 = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
//                        Keyframe.ofFloat(0f, 0),
//                        Keyframe.ofFloat(.10f, -delta),
//                        Keyframe.ofFloat(.26f, delta),
//                        Keyframe.ofFloat(.42f, -delta),
//                        Keyframe.ofFloat(.58f, delta),
//                        Keyframe.ofFloat(.74f, -delta),
//                        Keyframe.ofFloat(1f, 0f)
//                );
//                AnimatorSet animationSet = new AnimatorSet();
//                ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(textView, pvhTranslateX2);
//                objectAnimator2.setDuration(1000);
//                objectAnimator2.setStartDelay(300);
//
//                animationSet.playSequentially(objectAnimator, objectAnimator2);
//                animationSet.start();


                float delta = ViewUtils.dipToPixels(20, getResources().getDisplayMetrics());
                PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                        Keyframe.ofFloat(0f, 0),
                        Keyframe.ofFloat(.05f, -delta),
                        Keyframe.ofFloat(.10f, delta),
                        Keyframe.ofFloat(.15f, -delta),
                        Keyframe.ofFloat(.20f, delta),
                        Keyframe.ofFloat(.25f, -delta),
                        Keyframe.ofFloat(.30f, delta),
                        Keyframe.ofFloat(.35f, -delta),
                        Keyframe.ofFloat(.40f, delta),
                        Keyframe.ofFloat(0.45f, 0),
                        Keyframe.ofFloat(0.50f, 0),
                        Keyframe.ofFloat(0.55f, 0),
                        Keyframe.ofFloat(0.60f, 0),
                        Keyframe.ofFloat(.65f, -delta),
                        Keyframe.ofFloat(.70f, delta),
                        Keyframe.ofFloat(.75f, -delta),
                        Keyframe.ofFloat(.80f, delta),
                        Keyframe.ofFloat(.85f, -delta),
                        Keyframe.ofFloat(.90f, delta),
                        Keyframe.ofFloat(1f, 0)
                );

                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(textView, pvhTranslateX);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
            }
        });
    }
}
