package com.onfengma.androidprac.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author yfchu
 * @date 2016/4/7
 */
public class PullCircleView extends View {

    private float currentAngle = 0f;
    private float startAngle = 0f;

    public PullCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullCircleView(Context context) {
        super(context);
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int strokeWidth = 8;

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);

        paint.setColor(Color.RED);
        RectF rectF = new RectF(getWidth()/2 - getHeight()/2 + strokeWidth, strokeWidth, getWidth()/2 + getHeight()/2 - strokeWidth, getHeight() - strokeWidth);
        canvas.drawArc(rectF, 90f, 280f, false, paint);
    }

    private Point getCenter() {
        return new Point(getWidth() / 2, getHeight() / 2);
    }

}
