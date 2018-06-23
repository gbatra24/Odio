package com.android.odio.fragments;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.android.odio.BuildConfig;
import com.android.odio.MySharedPreferences;
import com.android.odio.R;


public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        EditTextPreference storagePref = (EditTextPreference) findPreference("storage_pref");
        String storageLocation = MySharedPreferences.loadStringSavedPreferences(MySharedPreferences.spStorageLocation,
                getActivity());
        storagePref.setSummary(storageLocation);
        storagePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                //store the value in shared pref
                preference.setSummary(newValue.toString());
                MySharedPreferences.saveStringPreferences(MySharedPreferences.spStorageLocation,
                        newValue.toString(),getActivity());

                return true;
            }
        });

        final ListPreference qualityPref = (ListPreference) findPreference("quality_list");
        int indexQuality = MySharedPreferences.loadIntSavedPreferences(MySharedPreferences.spIndexQuality,getActivity());
        qualityPref.setSummary(qualityPref.getEntries()[indexQuality]);
        qualityPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                //Update the value in Shared Pref
                int index = qualityPref.findIndexOfValue(newValue.toString());
                preference.setSummary(qualityPref.getEntries()[index]);

//                System.out.println("Quality : "+qualityPref.getEntries()[index]);
                MySharedPreferences.saveStringPreferences(MySharedPreferences.spQuality,
                        qualityPref.getEntries()[index].toString(),getActivity());
                MySharedPreferences.saveIntPreferences(MySharedPreferences.spIndexQuality,
                        index,getActivity());
                return true;
            }
        });

        final ListPreference channelPref = (ListPreference) findPreference("channel_list");
        int indexChannel = MySharedPreferences.loadIntSavedPreferences(MySharedPreferences.spIndexChannel,getActivity());
        channelPref.setSummary(channelPref.getEntries()[indexChannel]);
        channelPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                //Update the value in Shared Pref
                int index = channelPref.findIndexOfValue(newValue.toString());
                preference.setSummary(channelPref.getEntries()[index]);

//                System.out.println("Channel : "+channelPref.getEntries()[index]);
                MySharedPreferences.saveStringPreferences(MySharedPreferences.spChannel,
                        channelPref.getEntries()[index].toString(),getActivity());
                MySharedPreferences.saveIntPreferences(MySharedPreferences.spIndexChannel,
                        index,getActivity());
                return true;
            }
        });

        Preference aboutPref = findPreference(getString(R.string.pref_about_key));
        aboutPref.setSummary(getString(R.string.pref_about_desc, BuildConfig.VERSION_NAME));

    }
}
