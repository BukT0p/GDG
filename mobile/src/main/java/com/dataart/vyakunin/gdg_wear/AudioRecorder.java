package com.dataart.vyakunin.gdg_wear;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by grimmy on 10/18/14.
 */
public class AudioRecorder {
    private static boolean sRecording = false;

    private static final String LOG_TAG = "The_Compromat!_";

    private static MediaRecorder mRecorder = null;
    private static MediaPlayer mPlayer = null;

    public static void startRecord() {
        sRecording = true;

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        String file = Environment.getExternalStorageDirectory().toString() + "/Music/The_ComProMat";
        mRecorder.setOutputFile(file + "_" + String.valueOf(System.currentTimeMillis()) + ".3gp");
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    public static void stopRecord() {
        sRecording = false;

        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public static boolean isRecording() {
        return sRecording;
    }
}
