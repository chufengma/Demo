package com.onfengma.androidprac;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.onfengma.androidprac.views.VideoSurfaceView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoDemoActivity extends AppCompatActivity implements View.OnTouchListener {

    @Bind(R.id.video_surface)
    VideoSurfaceView videoSurface;
    @Bind(R.id.video_layout)
    RelativeLayout videoLayout;
    @Bind(R.id.record)
    Button record;
    @Bind(R.id.record_layout)
    RelativeLayout recordLayout;
    @Bind(R.id.switch_camera)
    Button switchCamera;
    @Bind(R.id.record_time_line)
    View recordTimeLine;
    @Bind(R.id.cancel_notice)
    TextView cancelNotice;
    @Bind(R.id.progress)
    ProgressBar progress;

    private long startTime;
    private long endTime;

    private CountDownTimer recordTimeCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_demo);
        ButterKnife.bind(this);

        progress.setVisibility(View.VISIBLE);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));

        final LinearLayout.LayoutParams videoLP = (LinearLayout.LayoutParams) videoLayout.getLayoutParams();
        videoLP.width = getResources().getDisplayMetrics().widthPixels;
        videoLP.height = getResources().getDisplayMetrics().widthPixels;
        videoLayout.setLayoutParams(videoLP);

        final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                RelativeLayout.LayoutParams recordBtnLp = (RelativeLayout.LayoutParams) record.getLayoutParams();
                recordBtnLp.height = recordLayout.getHeight() / 5 * 3;
                recordBtnLp.width = recordBtnLp.height;
                record.setLayoutParams(recordBtnLp);
                recordLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        };
        recordLayout.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);

        record.setOnTouchListener(this);

        videoSurface.setVideoCallbacks(new VideoSurfaceView.VideoCallbacks() {
            @Override
            public void startPreview() {
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCameraReady(Size size) {
            }

            @Override
            public void onPreviewReady() {
                progress.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.switch_camera)
    void onSwitchCameraClick() {
        videoSurface.switchCamera();
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoSurface.startPreviewing();
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoSurface.release();
    }

    float height;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startRecoding();
            startRecordTimeLineCountDown();
            height = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            stopRecoding();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (height - event.getY() > 80 && System.currentTimeMillis() - startTime > 800) {
                cancelRecoding();
            }
        }
        return true;
    }

    private CountDownTimer getRecordTimeCountDownTimer() {
        if (recordTimeCountDownTimer == null) {
            recordTimeCountDownTimer = new CountDownTimer(11 * 1000, 50) {

                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) recordTimeLine.getLayoutParams();
                int widthPixels = getResources().getDisplayMetrics().widthPixels;

                @Override
                public void onTick(long millisUntilFinished) {
                    lp.width = (int) (widthPixels * millisUntilFinished / (10 * 1000));
                    recordTimeLine.setLayoutParams(lp);
                }

                @Override
                public void onFinish() {
                    stopRecoding();
                    recordTimeLine.setVisibility(View.GONE);
                }
            };
        }
        return recordTimeCountDownTimer;
    }

    private void startRecordTimeLineCountDown() {
        recordTimeLine.setVisibility(View.VISIBLE);
        cancelNotice.setVisibility(View.VISIBLE);
        getRecordTimeCountDownTimer().start();
    }

    private void startRecoding() {
        if (!videoSurface.isRecording()) {
            videoSurface.startRecording();
            startTime = System.currentTimeMillis();
        }
    }

    private void stopRecoding() {
        if (videoSurface.isRecording()) {
            endTime = System.currentTimeMillis();
            long videoTime = (endTime - startTime) / 1000 - 1;
            if (videoTime < 3) {
                videoSurface.cancelRecoding();
                Toast.makeText(VideoDemoActivity.this, "时间小于3秒，自动丢弃...", Toast.LENGTH_SHORT).show();
            } else {
                videoSurface.stopRecoding();
                Toast.makeText(VideoDemoActivity.this, "总时长:" + videoTime + "s", Toast.LENGTH_SHORT).show();
            }
            recordTimeLine.setVisibility(View.GONE);
            cancelNotice.setVisibility(View.GONE);
            getRecordTimeCountDownTimer().cancel();
        }
    }

    private void cancelRecoding() {
        if (videoSurface.isRecording()) {
            startTime = 0;
            endTime = 0;
            videoSurface.cancelRecoding();
            recordTimeLine.setVisibility(View.GONE);
            cancelNotice.setVisibility(View.GONE);
            getRecordTimeCountDownTimer().cancel();
        }
    }

}
