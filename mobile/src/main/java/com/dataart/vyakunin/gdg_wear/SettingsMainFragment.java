package com.dataart.vyakunin.gdg_wear;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.settings_main)
public class SettingsMainFragment extends Fragment {

    private static final String TAG = "SettingsMainFragment";

    public static Fragment newInstance() {
        return SettingsMainFragment_.builder().build();
    }

    @Click(R.id.settings_message_numbers)
    void settingsMessageNumbersClicked() {
        ((SettingsActivity) getActivity()).switchTo(MessageContactsFragment.newInstance(), true, MessageContactsFragment.TAG);
        Log.d(TAG, "");
    }

    @Click(R.id.settings_message_list)
    void settingsMessageListClicked() {
        ((SettingsActivity) getActivity()).switchTo(MessageListFragment.newInstance(), true, MessageListFragment.TAG);
        Log.d(TAG, "");
    }

    @Click(R.id.settings_phone_numbers)
    void settingsPhoneNumbersClicked() {

    }
}
