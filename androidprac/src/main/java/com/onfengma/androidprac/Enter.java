package com.onfengma.androidprac;

import com.onfengma.androidprac.MVP.MVPDemoActivity;

/**
 * Created by chufengma on 15/8/2.
 */
public enum Enter {
    DataBindingActivity(DataBindingActivity.class),
    WindowActivity(WindowActivity.class),
    SwipeRefreshActivity(SwipeRefreshActivity.class),
    RecycleVIewActivity(RecycleVIewActivity.class),
    TaskAffiniActivity(TaskAffiniActivity.class),
    FrameAnimationActivity(FrameAnimationActivity.class),
    GuidenceActivity(GuidenceActivity.class),
    SwipeFinishFatherActivity(SwipeFinishFatherActivity.class),
    OkHttpClientActivity(OkHttpClientActivity.class),
    DrawerLayoutActivity(DrawerLayoutActivity.class),
    MVPDemoActivity(MVPDemoActivity.class),
    OnTouchEventDemoActivity(OnTouchEventDemoActivity.class),
    AllDrawableDemoActivity(AllDrawableDemoActivity.class),
    MaterialDemoActivity(MaterialDemoActivity.class),
    DesignSupportDemoActivity(DesignSupportDemoActivity.class),
    ThemeAndStyleDemoActivity(ThemeAndStyleDemoActivity.class),
    StorageDemoActivity(StorageDemoActivity.class),
    ShareDemoActivity(ShareDemoActivity.class),
    SceneDemoActivity(SceneDemoActivity.class),
    VideoDemoActivity(VideoDemoActivity.class),
    PlayVideoActivity(PlayVideoActivity.class),
    MeterialAnimActivity(MeterialAnimActivity.class),
    SoftInputActivity(SoftInpuActivity.class),
    ViewTestActivity(ViewTestActivity.class),
    TabLayoutDemoActivity(TabLayoutDemoActivity.class),
    WebViewDemoActivity(WebViewDemoActivity.class),
    IPCDemoActivity(IPCDemoActivity.class),
    DrawableDemoActivity(DrawableDemoActivity.class),
    ManagerSystemUiActivity(ManagerSystemUIActivity.class);

    private Class clazz;

    Enter(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
