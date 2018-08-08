package com.iyer.shailesh.dbar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimesFragment extends Fragment {
    String [] strings;
    sqlitehelper sqlite;
    public TimesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_times, container, false);
        LinearLayout linearlayout=(LinearLayout) view.findViewById(R.id.timeslinearlayout);
        sqlite=new sqlitehelper(getActivity());
        strings=sqlite.get_times();
        for(String str : strings){

            TextView valueTV = new TextView(getActivity());
            valueTV.setText(str);
            valueTV.setTextSize(34);
            valueTV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearlayout.addView(valueTV);
        }
        return view;
    }

}
