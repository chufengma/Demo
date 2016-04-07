package com.onfengma.androidprac.views;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.onfengma.androidprac.R;
import com.onfengma.androidprac.views.PullLayout.PullStatus;

/**
 * @author yfchu
 * @date 2016/4/7
 */
public class PullHeaderView extends FrameLayout {

    private TextView headTextView;
    private View pullCircleView;
    private PullStatus pullStatus = PullStatus.NORMAL;

    public PullHeaderView(Context context) {
        super(context);
        inflate(context, R.layout.pull_layout_header, this);
        headTextView = (TextView) findViewById(R.id.header_text);
        pullCircleView = findViewById(R.id.circle_view);
    }

    public void updateHeight(int height) {
        pullCircleView.setRotation(height);
    }

    public void updatePullStatus(PullStatus pullStatus) {
        this.pullStatus = pullStatus;
        switch (pullStatus) {
            case NORMAL:
                headTextView.setText("下拉查看更多精彩");
                break;
            case CAN_LOOSE:
                headTextView.setText("松手查看更多精彩");
                break;
            case PULLING:
                headTextView.setText("下拉查看更多精彩");
                break;
        }
    }


}
