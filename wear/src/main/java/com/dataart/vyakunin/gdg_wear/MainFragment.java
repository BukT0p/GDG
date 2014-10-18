package com.dataart.vyakunin.gdg_wear;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A fragment with a Google +1 button.
 *
 */
public class MainFragment extends android.app.Fragment {

    private TextView mTestText;
    private String mText;

    public void setText(String text){
        mText = text;
    }



    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mTestText = (TextView) view.findViewById(R.id.test_text);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mTestText.setText(mText);
    }


}
