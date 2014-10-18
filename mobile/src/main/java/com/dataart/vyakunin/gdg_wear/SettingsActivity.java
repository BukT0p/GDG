package com.dataart.vyakunin.gdg_wear;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.settings_activity)
public class SettingsActivity extends FragmentActivity{

    public static Intent newIntent(Context context) {
        return SettingsActivity_.intent(context).get();
    }

    @AfterViews
    void init(){
        switchTo(SettingsMainFragment.newInstance());
    }

    public void switchTo(Fragment fragment) {
        switchTo(fragment, false, null);
    }

    public void switchTo(Fragment fragment, boolean addToBackStack, String ftag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if ((fragmentManager.getBackStackEntryCount() > 0) && (addToBackStack) && (fragmentManager.findFragmentByTag(ftag) != null)) {
            fragmentManager.popBackStack(ftag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        if (ftag == null) {
            transaction.replace(R.id.container, fragment);
        } else {
            transaction.replace(R.id.container, fragment, ftag);
        }
        if (addToBackStack) transaction.addToBackStack(ftag);
        transaction.commit();
    }
}
