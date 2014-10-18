package com.dataart.vyakunin.gdg_wear;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by grimmy on 10/18/14.
 */
public class SendAlertFragment extends Fragment {

    public SendAlertFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_alert, container, false);

        return view;
    }

}
