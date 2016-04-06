package com.onfengma.androidprac;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainApplication extends Application {

    private String s = null;

    @Override
    public void onCreate() {
        super.onCreate();
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }

    @Subscribe
    public void onEvent(DemoEvent event) {}
}
