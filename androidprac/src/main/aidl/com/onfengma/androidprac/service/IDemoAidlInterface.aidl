// IDemoAidlInterface.aidl
package com.onfengma.androidprac.service;

// Declare any non-default types here with import statements

interface IDemoAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    String getMsg();

    void sendMsg(String msg);

}
