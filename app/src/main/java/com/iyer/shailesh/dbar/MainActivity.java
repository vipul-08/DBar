package com.iyer.shailesh.dbar;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    sqlitehelper sqlite=new sqlitehelper(this);
    FragmentManager manager;
    FragmentTransaction transaction;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            StartButtonFragment frag1 = new StartButtonFragment();
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            transaction.add(R.id.main_layout, frag1, "start");
            transaction.commit();
        }
        boolean flag=sqlite.isGameInProgress();

        if(flag){
            int qno = sqlite.getCurrentQues();
            int setNum = sqlite.getUserSet();

            Log.d("QUES_NO",qno+"");

            Intent intent = new Intent(MainActivity.this,RiddlesActivity.class);
            intent.putExtra("set",setNum);
            intent.putExtra("ques",qno);
            startActivity(intent);
            finish();

//            RiddlesFragment riddlesFragment=new RiddlesFragment();
//            manager=getSupportFragmentManager();
//            transaction = manager.beginTransaction();
//            transaction.replace(R.id.main_layout,riddlesFragment);
//            transaction.addToBackStack("list");
//            transaction.commit();
        }
    }

    public void newplayer(int set){
        sqlite.createnewuser(set);

        Intent intent = new Intent(MainActivity.this,RiddlesActivity.class);
        intent.putExtra("set",set);
        intent.putExtra("ques",1);
        startActivity(intent);
        finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        Log.e("Activity Result","from activity " +requestCode);
        super.onActivityResult(requestCode, resultCode, data);
        manager=getSupportFragmentManager();
        if (manager != null){
            List<Fragment> fragments = manager.getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                        fragment.onActivityResult(requestCode, resultCode, data);


                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Intent i = new Intent(this,org.artoolkit.ar.base.camera.CameraPreferencesActivity.class);
                startActivity(i);
                return true;

            case R.id.times:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                TimesFragment timesFragment=(TimesFragment) getSupportFragmentManager().findFragmentByTag("TIMES");
                if (timesFragment!=null &&timesFragment.isVisible()){
                    //Do nothing...
                }
                else {
                    TimesFragment timesFragment1 = new TimesFragment();
                    manager = getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    transaction.replace(R.id.main_layout, timesFragment1, "TIMES");
                    transaction.addToBackStack("times");
                    transaction.commit();
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
