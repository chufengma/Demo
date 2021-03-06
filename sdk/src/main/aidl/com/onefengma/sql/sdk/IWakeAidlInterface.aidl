// IWakeAidlInterface.aidl
package com.onefengma.sql.sdk;

// Declare any non-default types here with import statements

interface IWakeAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

            void sendRequest(String s);

            int getResponse();
}
