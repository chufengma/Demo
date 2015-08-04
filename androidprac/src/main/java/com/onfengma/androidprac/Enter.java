package com.onfengma.androidprac;

/**
 * Created by chufengma on 15/8/2.
 */
public enum Enter {
    DataBindingActivity(DataBindingActivity.class),
    WindowActivity(WindowActivity.class),
    SwipeRefreshActivity(SwipeRefreshActivity.class),
    RecycleVIewActivity(RecycleVIewActivity.class);

    private Class clazz;

    Enter(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
