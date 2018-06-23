package com.android.odio;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MySharedPreferences {
    private static String PREF_HIGH_QUALITY = "pref_high_quality";
    private static final String sharedPrefsFile = "OdioSharedPref";
    public static final String spQuality = "Quality";
    public static  final String spChannel = "Channel";
    public static final String spIndexChannel = "IndexChannel";
    public static final String spIndexQuality = "IndexQuality";
    public static final String spStorageLocation = "Storage";


    /*public static void setPrefHighQuality(Context context, boolean isEnabled) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PREF_HIGH_QUALITY, isEnabled);
        editor.apply();
    }

    public static boolean getPrefHighQuality(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PREF_HIGH_QUALITY, false);
    }*/

    public static void saveStringPreferences(String key, String value,
                                             Context ctx) {
        try {
            SharedPreferences sp = ctx.getSharedPreferences(sharedPrefsFile,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString(key, value);
            edit.apply();
        } catch (Exception e) {
            //
        }
    }

    public static String loadStringSavedPreferences(String key, Context ctx) {
        String name = "";
        try {
            SharedPreferences sp = ctx.getSharedPreferences(sharedPrefsFile,
                    Context.MODE_PRIVATE);
            name = sp.getString(key, "Odio/");
        } catch (Exception e) {
            //
        }
        return name;
    }

    public static void saveIntPreferences(String key, int value, Context ctx) {
        try {
            SharedPreferences sp = ctx.getSharedPreferences(sharedPrefsFile,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putInt(key, value);
            edit.apply();
        } catch (Exception e) {
            //
        }
    }

    public static int loadIntSavedPreferences(String key, Context ctx) {
        int name = -1;
        try {
            SharedPreferences sp = ctx.getSharedPreferences(sharedPrefsFile,
                    Context.MODE_PRIVATE);
            name = sp.getInt(key, 0);
        } catch (Exception e) {
            //
        }
        return name;
    }

    public static void saveLongPreferences(String key, long value, Context ctx) {
        try {
            SharedPreferences sp = ctx.getSharedPreferences(sharedPrefsFile,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putLong(key, value);
            edit.apply();
        } catch (Exception e) {
            //
        }
    }

    public static long loadLongSavedPreferences(String key, Context ctx) {
        long name = -1;
        try {
            SharedPreferences sp = ctx.getSharedPreferences(sharedPrefsFile,
                    Context.MODE_PRIVATE);
            name = sp.getLong(key, -1);
        } catch (Exception e) {
            //
        }
        return name;
    }

    public static void saveBooleanPreferences(String key, boolean value, Context ctx) {
        try {
            SharedPreferences sp = ctx.getSharedPreferences(sharedPrefsFile,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean(key, value);
            edit.apply();
        } catch (Exception e) {
            //
        }
    }

    public static boolean loadBooleanSavedPreferences(String key, Context ctx) {
        boolean name = false;
        try {
            SharedPreferences sp = ctx.getSharedPreferences(sharedPrefsFile,
                    Context.MODE_PRIVATE);
            name = sp.getBoolean(key, false);
        } catch (Exception e) {
            //
        }
        return name;
    }
}
