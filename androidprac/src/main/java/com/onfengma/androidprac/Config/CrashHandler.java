package com.onfengma.androidprac.Config;

/**
 * @author yfchu
 * @date 2016/3/29
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ex.printStackTrace();
        ex.printStackTrace();
    }

}
