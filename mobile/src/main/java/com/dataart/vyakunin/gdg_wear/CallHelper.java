package com.dataart.vyakunin.gdg_wear;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;

/**
 * Created by grimmy on 10/18/14.
 */
public class CallHelper {
    public static void callToPhone(Context context, String telNumber) {
        String uri = "tel:" + telNumber ;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        context.startActivity(intent);
    }
}
