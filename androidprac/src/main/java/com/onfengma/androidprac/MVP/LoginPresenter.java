package com.onfengma.androidprac.MVP;

public class LoginPresenter {

    ILoginView view;

    public LoginPresenter(ILoginView view) {
        this.view = view;
    }

    public void login(final String name, final String password) {
        view.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (name.equals("fengma")) {
                    view.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.onFailed("fengma is god user , so come on!");
                            view.hideLoading();
                        }
                    });
                    return;
                }

                view.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.onSuccess("Welcome!!!");
                        view.hideLoading();
                    }
                });
            }
        }).start();
    }

}
