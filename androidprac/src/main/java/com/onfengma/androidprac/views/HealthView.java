package com.onfengma.androidprac.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yifeng on 15/12/29.
 */
public class HealthView extends View {

    private static int TOP;
    private static int LEFT;
    private static int RIGIT;
    private static int BOTTOM;

    private float angle = 300;

    public HealthView(Context context) {
        super(context);
        init();
    }

    public HealthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HealthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        init();
    }

    private void init() {
        TOP = getTop() + getHeight() / 4;
        LEFT = getLeft() + getWidth() / 4;
        RIGIT = LEFT + getWidth() / 2;
        BOTTOM = TOP + getWidth() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (angle <= 0) {
            return;
        }
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        Shader mShader = new LinearGradient(0, 0, RIGIT, BOTTOM,
                new int[] { Color.BLUE, Color.CYAN }, null, Shader.TileMode.REPEAT); // 一个材质,打造出一个线性梯度沿著一条线。
        paint.setShader(mShader);
        RectF rectF = new RectF(LEFT, TOP, RIGIT, BOTTOM);
        canvas.drawArc(rectF, 120, angle, false, paint);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
        invalidate();
    }
}
