package com.dataart.vyakunin.gdg_wear;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.settings_main)
public class SettingsMainFragment extends Fragment {

    public static Fragment newInstance() {
        return new SettingsMainFragment();
    }
}
