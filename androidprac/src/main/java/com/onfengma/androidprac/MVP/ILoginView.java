package com.onfengma.androidprac.MVP;

/**
 * Created by chuyifeng on 2015/9/8.
 */
public interface ILoginView {

    void showLoading();

    void hideLoading();

    void onSuccess(String msg);

    void onFailed(String reason);

    void runOnUiThread(Runnable runnable);
}
