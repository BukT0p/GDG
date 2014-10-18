package com.dataart.vyakunin.gdg_wear;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter emergency number");

// Set up the input
        final EditText input = new EditText(getActivity());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_PHONE);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String number = input.getText().toString();
                SharedPreferences prefs = getActivity().getSharedPreferences("com.dataart.vyakunin.app", Context.MODE_PRIVATE);
                prefs.edit().putString(HomeActivity.NUMBER_TO_PHONE, number).apply();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
