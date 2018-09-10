package com.taggart.e_green2;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;;


public class MainActivity extends AppCompatActivity
        implements UserInfo.UserInfoFragmentListener {


    private UserInfo userInfo;  // UserInfo fragment variable
    private SelectEV select_ev;  // SelectEV fragment variable
    private int car_pairs_total;
    private EV [] ev_array;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       if (savedInstanceState == null &&
                findViewById(R.id.fragmentContainer) != null) {
            // create UserInfo fragment
            userInfo = new UserInfo();



            // add the userInfo fragment to the content_main RelativeLayout
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragmentContainer, userInfo);
            transaction.commit(); // display user_info_fragment


        }
    }


    @Override
    public void onSearchComplete(int car_pairs) {
        car_pairs_total = car_pairs;
        if (findViewById(R.id.fragmentContainer) != null) // phone
            passEVs(R.id.fragmentContainer);
    }


    private void passEVs(int viewID)   {

        if (userInfo.ev_array != null)
        {
            select_ev = new SelectEV();

            // use a FragmentTransaction to display the SelectEV fragment
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.replace(viewID, select_ev);
            ev_array = userInfo.ev_array;
            transaction.addToBackStack(null);
            transaction.commit(); // causes SelectEV fragment to display
            //FragmentManager fm = getFragmentManager();
            //fm.executePendingTransactions();
            //fm = getSupportFragmentManager();
            //fm.executePendingTransactions();
            fm = getSupportFragmentManager();
            fm.executePendingTransactions();

            select_ev.displayEVs(ev_array, car_pairs_total);


            // figure out which EV's stored in userInfo.ev_array and display

        }

    }





    //  not sure if the below default functions will be needed at all

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
