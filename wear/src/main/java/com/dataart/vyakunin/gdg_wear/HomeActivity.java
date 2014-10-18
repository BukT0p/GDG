package com.dataart.vyakunin.gdg_wear;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

public class HomeActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    Node mNode; // the connected device to send the message to
    GoogleApiClient mGoogleApiClient;
    private static final String HELLO_WORLD_WEAR_PATH = "/hello-world-wear";
    private boolean mResolvingError=false;


    private GridViewPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        //Connect the GoogleApiClient
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {

            MainFragment mMainFragment;
            SendAlertFragment mSendAlertFragment;
            CallFragment mCallFragment;
            RecordFragment mRecordFragment;
            VideoRecordFragment mVideoRecordFragment;

            @Override
            public void onLayoutInflated(WatchViewStub stub) {
               mPager = (GridViewPager) findViewById(R.id.grid_pager);
                FragmentGridPagerAdapter adapter = new FragmentGridPagerAdapter(getFragmentManager()) {

                    @Override
                    public Fragment getFragment(int i, int i2) {
                        if(i == 0 && i2 == 0) {
                            return getMainFragment();
                        }
                        if(i == 1 && i2 == 0){
                            return getCallFragment();
                        }
                        if(i == 2 && i2 == 0){
                            return getRecordFragment();
                        }
                        if(i == 0 && i2 == 1){
                            return getSendAlertFragment();
                        }
                        if(i == 2 && i2 == 1){
                            return getRecordVideoFragment();
                        }
                        return null;
                    }

                    private Fragment getMainFragment() {
                        if(mMainFragment == null){
                            mMainFragment = new MainFragment();
                        }
                        return mMainFragment;
                    }

                    @Override
                    public int getRowCount() {
                        return 3;
                    }

                    @Override
                    public int getColumnCount(int i) {
                        if (i == 0 || i == 2) return 2;
                        return 1;
                    }

                    public Fragment getCallFragment() {
                        if(mCallFragment == null){
                            mCallFragment = new CallFragment();
                        }
                        return mCallFragment;
                    }

                    public Fragment getRecordFragment() {
                        if(mRecordFragment == null){
                            mRecordFragment = new RecordFragment();
                        }
                        return mRecordFragment;
                    }

                    public Fragment getSendAlertFragment() {
                        if(mSendAlertFragment == null){
                            mSendAlertFragment = new SendAlertFragment();
                        }
                        return mSendAlertFragment;
                    }

                    public Fragment getRecordVideoFragment() {
                        if(mVideoRecordFragment == null){
                            mVideoRecordFragment = new VideoRecordFragment();
                        }
                        return mVideoRecordFragment;
                    }
                };
                mPager.setAdapter(adapter);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!mResolvingError) {
            mGoogleApiClient.connect();
        }
    }

    /**
     * Send message to mobile handheld
     */
    public void sendMessage() {

        if (mNode != null && mGoogleApiClient!=null && mGoogleApiClient.isConnected()) {
            Wearable.MessageApi.sendMessage(
                    mGoogleApiClient, mNode.getId(), HELLO_WORLD_WEAR_PATH, null).setResultCallback(

                    new ResultCallback<MessageApi.SendMessageResult>() {
                        @Override
                        public void onResult(MessageApi.SendMessageResult sendMessageResult) {

                            if (!sendMessageResult.getStatus().isSuccess()) {
                                Log.e("TAG", "Failed to send message with status code: "
                                        + sendMessageResult.getStatus().getStatusCode());
                            }
                        }
                    }
            );
        }else{
            //Improve your code
        }

    }

    /*
     * Resolve the node = the connected device to send the message to
     */
    private void resolveNode() {

        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(NodeApi.GetConnectedNodesResult nodes) {
                for (Node node : nodes.getNodes()) {
                    mNode = node;
                }
            }
        });
    }


    @Override
    public void onConnected(Bundle bundle) {
        resolveNode();
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Improve your code
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //Improve your code
    }
}
