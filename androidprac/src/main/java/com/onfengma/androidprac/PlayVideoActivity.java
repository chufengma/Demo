package com.onfengma.androidprac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayVideoActivity extends AppCompatActivity {

    @Bind(R.id.video)
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        ButterKnife.bind(this);

        video.setVideoPath("file://" + "/sdcard/Android/data/com.wumii.android.goddess/files/videos/1444882239444.mp4");
        video.start();
    }


}
