package com.dataart.vyakunin.gdg_wear;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_home)
public class HomeActivity extends Activity {

    @Click(R.id.settings_btn)
    void onSettingsClicked(){
        startActivity(SettingsActivity.newIntent(this));
    }
}
