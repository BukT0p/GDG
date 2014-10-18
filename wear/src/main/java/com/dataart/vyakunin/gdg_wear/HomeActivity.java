package com.dataart.vyakunin.gdg_wear;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

public class HomeActivity extends Activity {

    private GridViewPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
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
}
