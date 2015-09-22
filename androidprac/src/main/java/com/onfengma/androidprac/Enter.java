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
    MVPDemoActivity(MVPDemoActivity.class),
    OnTouchEventDemoActivity(OnTouchEventDemoActivity.class),
    AllDrawableDemoActivity(AllDrawableDemoActivity.class),
    MaterialDemoActivity(MaterialDemoActivity.class),
    DesignSupportDemoActivity(DesignSupportDemoActivity.class),
    ManagerSystemUiActivity(ManagerSystemUIActivity.class);

    private Class clazz;

    Enter(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
