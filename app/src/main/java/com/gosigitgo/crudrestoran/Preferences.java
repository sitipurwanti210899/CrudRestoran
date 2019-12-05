package com.gosigitgo.crudrestoran;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static String KEY_EMAIL = "EMAIL";
    private static String KEY_PASSWORD = "NAMA";
    private static String KEY_LOGIN = "LOGIN";

    public static void actionLogin(Context context, String email, String nama){

        sharedPreferences = context.getSharedPreferences(KEY_LOGIN, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, nama);
        editor.apply();
    }

    public static String getEmail(Context context){
        sharedPreferences = context.getSharedPreferences(KEY_LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public static String getPasword(Context context){
        sharedPreferences = context.getSharedPreferences(KEY_LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PASSWORD, null);
    }

    public static void actionLogout(Context context){
        sharedPreferences = context.getSharedPreferences(KEY_LOGIN, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear().apply();
    }
}
