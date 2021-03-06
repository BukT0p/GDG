package com.dataart.vyakunin.gdg_wear;

import android.content.Intent;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by grimmy on 10/18/14.
 */
public class ListenerServiceFromWear extends WearableListenerService {

    private static final String HELLO_WORLD_WEAR_PATH = "/hello-world-wear";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

            Intent startIntent = new Intent(this, HomeActivity_.class);
            startIntent.putExtra("Command", messageEvent.getPath());

            startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startIntent);

    }

}