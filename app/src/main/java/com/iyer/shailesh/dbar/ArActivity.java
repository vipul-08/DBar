package com.iyer.shailesh.dbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.threed.jpct.Config;
import com.threed.jpct.Loader;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;

import org.artoolkit.ar.base.ARToolKit;
import org.artoolkit.ar.base.NativeInterface;
import org.artoolkit.ar.jpct.ArJpctActivity;
import org.artoolkit.ar.jpct.TrackableObject3d;

import java.util.HashMap;
import java.util.List;

public class ArActivity extends ArJpctActivity {
    private int position;
    private String pos;
    private String location;
    public static String LOCATION_STRING="a";
    public static String POSITION_STRING="b";
    public int UID;
    View view;
    TrackableObject3d tckobj;
    sqlitehelper sqlite;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        Bundle extras = getIntent().getBundleExtra(RiddlesFragment.x);
        pos = extras.getString(RiddlesFragment.POSITION_STRING);
        position = Integer.parseInt(pos);
        location = extras.getString(RiddlesFragment.LOCATION_STRING);
        view=findViewById(R.id.cameraLayout);
        sqlite=new sqlitehelper(this);
    }

    @Override
    protected FrameLayout supplyFrameLayout() {
        return (FrameLayout)this.findViewById(R.id.cameraLayout);
    }

    @Override
    public void configureWorld(World world) {
        Config.farPlane = 2000;
        world.setAmbientLight(255, 255, 255);
    }
    @Override
    public void onBackPressed(){
        ARToolKit.getInstance().cleanup();
        Intent returnIntent=new Intent();
        Bundle extras = new Bundle();
        extras.putString(LOCATION_STRING,location);
        extras.putString(POSITION_STRING,pos);
        returnIntent.putExtras(extras);
        setResult(RESULT_CANCELED,returnIntent);
        Log.e("ArActivity","On back presed");
        finish();
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.e("aractivity","on Resume");

    }
    @Override
    protected void populateTrackableObjects(List<TrackableObject3d> list) {
        //Trackable marker initializations
        NativeInterface.arwSetPatternDetectionMode(NativeInterface.AR_MATRIX_CODE_DETECTION);
        NativeInterface.arwSetMatrixCodeType(NativeInterface.AR_MATRIX_CODE_3x3_PARITY65);

        Object3D db = null;
        try {
            Object3D[] dbs = Loader.load3DS(getAssets().open("alienfour.3ds"), 50);
            dbs[0].setOrigin(new SimpleVector(0, 0, -270));
            dbs[0].rotateX(90);
            db = dbs[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("exam cell","single_barcode;0;80");
        map.put("compost pit","single_barcode;1;80");
        map.put("mba audi","single_barcode;2;80");
        map.put("gopuram","single_barcode;3;80");
        map.put("canteen","single_barcode;4;80");
        map.put("sop audi","single_barcode;5;80");
        map.put("gym","single_barcode;6;80");
        map.put("chemistry lab","single_barcode;7;80");
        map.put("gate","single_barcode;8;80");
        map.put("computer center","single_barcode;9;80");
        map.put("first aid room","single_barcode;10;80");
        map.put("physics lab","single_barcode;11;80");
        map.put("amphitheatre","single_barcode;12;80");
        map.put("suggestion box","single_barcode;13;80");
        map.put("biotechnology library","single_barcode;14;80");
        map.put("ppt bridge","single_barcode;15;80");
        map.put("workshop","single_barcode;16;80");
        map.put("fe notice board","single_barcode;17;80");
        map.put("art gallary","single_barcode;18;80");
        map.put("student council room","single_barcode;19;80");
        map.put("xerox shop","single_barcode;20;80");
        map.put("ppt sample display","single_barcode;21;80");
        map.put("glass building","single_barcode;22;80");
        map.put("library","single_barcode;23;80");
        map.put("em lab","single_barcode;24;80");
        map.put("beacon room","single_barcode;25;80");
        map.put("cafeteria","single_barcode;26;80");
        map.put("server room","single_barcode;27;80");
        map.put("bcr","single_barcode;28;80");
        map.put("nss room","single_barcode;29;80");

        Log.d(TAG, "populateTrackableObjects: "+map.get(location.toLowerCase()));

        tckobj = new TrackableObject3d(map.get(location.toLowerCase()));
        try {
            tckobj.addChild(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.add(tckobj);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UID=tckobj.getmMarkerId();
                Log.e("Marker","searching marker with  "+UID);
                boolean markervisible= ARToolKit.getInstance().queryMarkerVisible(UID);
                Intent returnIntent= new Intent();
                if (markervisible){
                    sqlite.update_Status(position);
                    ARToolKit.getInstance().cleanup();
                    returnIntent.putExtra("position",position);
                    setResult(RESULT_OK,returnIntent);
                    finish();
                }

            }
        });
    }


}
