package com.dataart.vyakunin.gdg_wear;

import android.app.Activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_home)
public class HomeActivity extends Activity {

    public static final String SEND_ALERT = "android.gdg.send_alert";
    public static final String CALL = "android.gdg.call";
    public static final String RECORD_AUDIO = "android.gdg.record_audio";
    public static final String RECORD_VIDEO = "android.gdg.record_video";

    @Click(R.id.settings_btn)
    void onSettingsClicked() {
        startActivity(SettingsActivity.newIntent(this));
    }

    @AfterViews
    void init() {
        String action = getIntent().getAction();
        if (action != null) {
            doAction(action);
        }
    }

    private void doAction(String action) {
        if (action.equals(SEND_ALERT)) {
            //TODO
        } else if (action.equals(CALL)) {
            //TODO
        } else if (action.equals(RECORD_AUDIO)) {
            //TODO
        } else if (action.equals(RECORD_VIDEO)) {
            //TODO
        }
    }
}
