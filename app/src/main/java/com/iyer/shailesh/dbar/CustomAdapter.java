package com.iyer.shailesh.dbar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.iyer.shailesh.dbar.R.string.status2;

/**
 * Created by sweth on 9/12/2016.
 */
public class CustomAdapter extends ArrayAdapter {
    private Context mContext;
    private ArrayList<Riddles> mList;

    public CustomAdapter(Context context, ArrayList<Riddles> list) {
        super(context, R.layout.list_item, list);

        mContext = context;
        mList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        int status;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }
        else {
            view = convertView;
        }

        TextView riddleID = (TextView) view.findViewById(R.id.riddleid);
        TextView riddleStatus = (TextView) view.findViewById(R.id.riddlestatus);
        //Log.e("HEEYYY",String.valueOf(mList.get(position).getRID()));

        riddleID.setText((String.valueOf(mList.get(position).getRID())));
        status = mList.get(position).getStatus();
        if (status == 0) {
            riddleStatus.setText(R.string.status1);
        }
        else if (status == 1){
            riddleStatus.setText("Find the coin at:"+mList.get(position).getANSWER() );
        }
        else if (status == 2){
            riddleStatus.setText(R.string.status3);
        }
        return view;
    }
}