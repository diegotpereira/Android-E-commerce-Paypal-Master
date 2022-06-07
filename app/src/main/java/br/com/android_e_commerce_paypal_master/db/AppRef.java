package br.com.android_e_commerce_paypal_master.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import br.com.android_e_commerce_paypal_master.app.MeuApp;

public class AppRef {

    private static AppRef singleTonInstance = null;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static final String PREF_NAME = "app_prefs";
    private static final int PRIVATE_MODE = 0;
    private static final String KEY_AUTH_TOKEN = "auth_token";

    public static AppRef getInstance() {
        if (singleTonInstance == null) {
            singleTonInstance = new AppRef(MeuApp.getInstance().getApplicationContext());
        }
        return singleTonInstance;
    }
    @SuppressLint("WrongConstant")
    private AppRef(Context context) {
        super();
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
        editor.apply();
    }
    public void salvarAuthToken(String authToken){

        editor.putString(KEY_AUTH_TOKEN, authToken);
        editor.commit();
    }
    public String getAuthToken() {

        return sharedPreferences.getString(KEY_AUTH_TOKEN, null);
    }
    public void limparDado() {
        editor.clear().commit();
    }
}
