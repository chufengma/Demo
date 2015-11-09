package com.onfengma.androidprac;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

public class SceneDemoActivity extends AppCompatActivity {

    Scene scene1;
    Scene scene2;
    Transition fade;
    Transition changeBounds;

    Scene currentScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_demo);
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

        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene);

        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene_demo_scene_one, this);
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene_demo_scene_two, this);

        fade = new Fade();
        changeBounds = new ChangeBounds();
        TransitionManager.go(scene2, fade);
    }

    public void onClick(View view) {
        if (currentScene == scene1) {
            currentScene = scene2;
        } else {
            currentScene = scene1;
        }

        TransitionManager.go(currentScene, changeBounds);
    }

}
