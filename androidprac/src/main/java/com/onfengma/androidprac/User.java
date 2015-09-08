package com.onfengma.androidprac;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by chufengma on 15/7/26.
 */
public class User extends BaseObservable {

    private int age;
    private String name;

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(com.onfengma.androidprac.BR.age);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.onfengma.androidprac.BR.name);
    }
}
