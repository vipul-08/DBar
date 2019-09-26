package com.iyer.shailesh.dbar;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class RiddlesFragment extends ListFragment {
    sqlitehelper sqlite;
    ArrayList<Riddles> riddlesList;
    CustomAdapter adapter;
    Button endgame;
    int setnumber;
    public static int flag=0;
    public static String LOCATION_STRING="c";
    public static String POSITION_STRING="d";
    public static String x="e";

    public RiddlesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        sqlite = new sqlitehelper(getActivity());
        setnumber = sqlite.getUserSet();
        riddlesList = sqlite.initialiseRiddles(setnumber);
        riddlesList = sqlite.initialiseDetails(riddlesList);
        adapter = new CustomAdapter(getActivity(), riddlesList);
        View view = inflater.inflate(R.layout.fragment_riddles, container, false);
        setListAdapter(adapter);
        endgame = (Button) view.findViewById(R.id.endgame);
        endgame.setVisibility(View.GONE);
        endgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("Button", "Button is pressed");
                sqlite.finishGame();
                Intent i=new Intent(getActivity(),VideoActivity.class);
                getActivity().startActivity(i);
                getActivity().finish();

            }
        });
        Log.e("RiddleFrag", "OnCreate View is called");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("RiddlesFrag", "Dataset Changed");
        adapter.notifyDataSetChanged();
        if(sqlite==null){
            sqlite=new sqlitehelper(getActivity());
        }
        boolean statusf=sqlite.canfinishgame();
        if (statusf) {
            Log.e("Activity Result", "endgame should be visible");
            endgame.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
        Riddles item = riddlesList.get(position);
        int flagger = 1;
        if (position == 0) {
            flagger = 1;
        } else {
            for (int k = 0; k < position; k++) {
                if (riddlesList.get(k).getStatus() != 2) {
                    flagger = 0;
                }
            }
        }
        if (flagger == 1)
        {
            if (item.getStatus() == 0) {
                showPopup(view, item.getQUESTION(), item.getANSWER(), position);
                Log.e("HEYY", "item clicked");
            } else if (item.getStatus() == 1) {
                Log.e("HEYY", "Launching ar activity");

                Bundle extras = new Bundle();
                Intent i = new Intent(getActivity(), ArActivity.class);

                String location = item.getANSWER().toString();
                extras.putString(LOCATION_STRING, location);

                String pos = Integer.toString(position);
                extras.putString(POSITION_STRING, pos);

                i.putExtra(x, extras);

                getActivity().startActivityForResult(i, 1);
                Log.e("HEYY", "Coming back from ar activty");
                flag = 1;

            } else if (item.getStatus() == 2) {
                Log.e("HEYY", "Riddle Done");
            }
        }
        else {
            Toast.makeText(getActivity(), "Please solve the above riddles first..", Toast.LENGTH_LONG).show();
        }
    }


    public void showPopup(final View anchorView, String question, final String answer, final int position) {
        View popupView = getActivity().getLayoutInflater().inflate(R.layout.popup_layout, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Example: If you have a TextView inside `popup_layout.xml`
        TextView tv = (TextView) popupView.findViewById(R.id.riddleQuestion);
        tv.setText(question);
        final EditText ev = (EditText) popupView.findViewById(R.id.riddleAnswer);
        // Initialize more widgets from `popup_layout.xml`
        Button check = (Button) popupView.findViewById(R.id.answerButton);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView sv = (TextView) anchorView.findViewById(R.id.riddlestatus);
                if (answer.equalsIgnoreCase(ev.getText().toString())) {
                    riddlesList.get(position).setStatus(1);
                    sqlite.update_Status(position);
                    sv.setText("Find the coin at:"+answer);
                    Toast.makeText(getActivity(), "RightAnswer", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                    flag=0;
                } else {
                    Toast.makeText(getActivity(), "WrongAnswer", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);
        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //getActivity();
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            int position = data.getIntExtra("position", 0);
            Log.e("Activity Result", "setting status");
            if (riddlesList != null) {
                Log.e("Activity Result", "inside if loop setting status");
                riddlesList.get(position).setStatus(2);
                adapter.notifyDataSetChanged();
            }


        } else if (requestCode == 1 && resultCode == Activity.RESULT_CANCELED) {
            Log.e("Activity Result", "not setting status");
        }
    }
}
