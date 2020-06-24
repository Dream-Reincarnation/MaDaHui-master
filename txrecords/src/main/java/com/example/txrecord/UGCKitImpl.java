package com.example.txrecord;

import android.content.Context;

public class UGCKitImpl {
    private static Context sAppContext;

    public static void init(Context context) {
        sAppContext = context;
    }

    public static Context getAppContext() {
        return sAppContext;
    }

}
