package br.com.android_e_commerce_paypal_master.app;

import android.app.Application;

public class MeuApp extends Application {

    private static MeuApp mInstance;

    public static MeuApp getInstance() {
        return mInstance;
    }
}
