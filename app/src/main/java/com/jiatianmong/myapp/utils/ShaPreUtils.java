package com.jiatianmong.myapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jiatianmong on 2016-10-6 20:56
 */
public class ShaPreUtils {
    public static boolean getBoolean(Context context, String key, boolean setDefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, setDefValue);
    }
    public static void setBoolean(Context context, String key, boolean getDefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, getDefValue).commit();
    }
    public static String getString(Context context, String key, String setDefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sharedPreferences.getString(key, setDefValue);
    }
    public static void setString(Context context, String key, String getDefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, getDefValue).commit();
    }
    public static int getInt(Context context, String key, int setDefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, setDefValue);
    }
    public static void setInt(Context context, String key, int getDefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, getDefValue).commit();
    }


}
