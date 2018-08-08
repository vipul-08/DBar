package com.iyer.shailesh.dbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.threed.jpct.Config;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;
import com.threed.jpct.util.ExtendedPrimitives;

import org.artoolkit.ar.base.ARToolKit;
import org.artoolkit.ar.base.NativeInterface;
import org.artoolkit.ar.jpct.ArJpctActivity;
import org.artoolkit.ar.jpct.TrackableObject3d;

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

        Drawable dr;
        Texture texture;
        Object3D db= Primitives.getSphere(40);
        db.calcTextureWrapSpherical();
        int arr[]={R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5,R.drawable.icon6,R.drawable.icon7};

        if ("parking".equalsIgnoreCase(location)) {
            //Dragon Ball 1
            tckobj= new TrackableObject3d("single_barcode;0;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db1", texture);
            try{
                db.setTexture("db1");
                tckobj.addChild(db);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);
        }
        else if ("stairs".equalsIgnoreCase(location)) {
            //Dragon Ball 2
            tckobj = new TrackableObject3d("single_barcode;1;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db2", texture);
            try{
                db.setTexture("db2");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);
        }
        else if ("exam cell".equalsIgnoreCase(location)) {
            //Dragon Ball 3
            tckobj= new TrackableObject3d("single_barcode;2;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db3", texture);
            try{
                db.setTexture("db3");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);


        }
        else if ("workshop".equalsIgnoreCase(location)) {
            //Dragon Ball 4
            tckobj= new TrackableObject3d("single_barcode;3;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db4", texture);
            try{
                db.setTexture("db4");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);


        }
        else if ("213".equalsIgnoreCase(location)) {
            //Dragon Ball 5
            tckobj= new TrackableObject3d("single_barcode;4;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db5", texture);
            try{
                db.setTexture("db5");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);
        }
        else if ("canteen".equalsIgnoreCase(location)) {
            //Dragon Ball 6
            tckobj= new TrackableObject3d("single_barcode;5;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db6", texture);
            try{
                db.setTexture("db6");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);
        }
        else if("amphitheatre".equalsIgnoreCase(location)) {
            //Dragon Ball 7
            tckobj = new TrackableObject3d("single_barcode;6;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db7",texture);
            try{
                db.setTexture("db7");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);
        }
        else if("front gate".equalsIgnoreCase(location)) {
            //Dragon Ball 7
            tckobj = new TrackableObject3d("single_barcode;7;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db8",texture);
            try{
                db.setTexture("db8");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);
        }
        else if("cafeteria".equalsIgnoreCase(location)) {
            //Dragon Ball 7
            tckobj = new TrackableObject3d("single_barcode;8;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db9",texture);
            try{
                db.setTexture("db9");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);
        }

        else if("319".equalsIgnoreCase(location)) {
            tckobj = new TrackableObject3d("single_barcode;9;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db10",texture);
            try{
                db.setTexture("db10");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);
        }

        else if("auditorium".equalsIgnoreCase(location)) {
            tckobj = new TrackableObject3d("single_barcode;10;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db11",texture);
            try{
                db.setTexture("db11");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);
        }

        else if("gymnasium".equalsIgnoreCase(location)) {
            tckobj = new TrackableObject3d("single_barcode;11;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db12",texture);
            try{
                db.setTexture("db12");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);
        }

        else if("library".equalsIgnoreCase(location)) {
            tckobj = new TrackableObject3d("single_barcode;12;80");
            dr= ContextCompat.getDrawable(this,arr[position]);
            texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(dr), 128, 128));
            TextureManager.getInstance().addTexture("db13",texture);
            try{
                db.setTexture("db13");
                tckobj.addChild(db);

            }
            catch(Exception e){
                e.printStackTrace();
            }
            list.add(tckobj);


        }



        Log.e("trackobjlist","reached end of method ");
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
                    Log.e("Aractivity","Since caught exiting activity");
                    returnIntent.putExtra("position",position);
                    setResult(RESULT_OK,returnIntent);
                    finish();
                }

            }
        });
    }


}
