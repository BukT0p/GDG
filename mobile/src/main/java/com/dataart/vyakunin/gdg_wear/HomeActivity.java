package com.dataart.vyakunin.gdg_wear;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_home)
public class HomeActivity extends Activity {

    public static final String SEND_ALERT = "send_alert";
    public static final String CALL = "call";
    public static final String RECORD_AUDIO = "record_audio";
    public static final String RECORD_VIDEO = "record_video";

    public static final String MESSAGE_TO_SEND = "message";
    public static final String NUMBER_TO_PHONE = "phone_number";

    @Click(R.id.settings_btn)
    void onSettingsClicked() {
        startActivity(SettingsActivity.newIntent(this));
    }

    @AfterViews
    void init() {
        Bundle action = getIntent().getExtras();
        if (action != null) {
            String command = action.getString("Command");
            if (command != null) {
                doAction(command);
            }
        }
    }

    private void doAction(String action) {
        if (action.equals(SEND_ALERT)) {
            //TODO
            Toast.makeText(this, "Send alert", Toast.LENGTH_SHORT).show();
            sendBunchOfSms();
        } else if (action.equals(CALL)) {
            //TODO
            Toast.makeText(this, "Call", Toast.LENGTH_SHORT).show();
            CallHelper.callToPhone(this,"+380665648199");
        } else if (action.equals(RECORD_AUDIO)) {
            if(AudioRecorder.isRecording()){
                AudioRecorder.stopRecord();
            } else {
                AudioRecorder.startRecord();
            }
            Toast.makeText(this, "Record audio", Toast.LENGTH_SHORT).show();
        } else if (action.equals(RECORD_VIDEO)) {
            //TODO
            Toast.makeText(this, "Video", Toast.LENGTH_SHORT).show();
        }
    }

    @Background
     void sendBunchOfSms() {
        SharedPreferences prefs = getSharedPreferences("com.dataart.vyakunin.app", Context.MODE_PRIVATE);
        String message = prefs.getString(MESSAGE_TO_SEND, "Help me!");
        Cursor cursor = getContentResolver().query(StoreContentProvider.getContentUri(Store.ContactNumbers.CONTENT_URI), null, null, null, null);
        int columnId = cursor.getColumnIndex(Store.ContactNumbers.PHONE_NUMBER);
        if (cursor.moveToFirst()) {
            do {
                sendSMS(cursor.getString(columnId), message);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void sendSMS(String phoneNumber, String message) {
        Toast.makeText(this, "Sending message:"+message+" to:"+phoneNumber, Toast.LENGTH_SHORT).show();
//        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
