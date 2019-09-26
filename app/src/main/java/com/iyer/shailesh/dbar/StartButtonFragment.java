package com.iyer.shailesh.dbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;


public class StartButtonFragment extends Fragment implements View.OnClickListener {
    int setnumber;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_startbutton, container, false);
        (view.findViewById(R.id.startbtn)).setOnClickListener(this);
        NumberPicker numberPicker=(NumberPicker) view.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(9);
        setnumber=1;
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                setnumber = newVal;
            }
        });
        return view;

    }



    @Override
    public void onClick(View v) {
        ((MainActivity)getActivity()).newplayer(setnumber);
    }
}
