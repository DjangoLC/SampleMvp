package com.example.joseenrique.myapplication.views.fragments.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.joseenrique.myapplication.R;
import com.example.joseenrique.myapplication.Utils.Utilities;
import com.example.joseenrique.myapplication.views.fragments.firstFragment;
import com.example.joseenrique.myapplication.views.fragments.secondFragment;
import com.example.joseenrique.myapplication.views.fragments.thirdFragment;

import java.util.HashMap;

import butterknife.internal.Utils;


public class MainActivity extends AppCompatActivity implements
                                    firstFragment.OnFirstFragmentInteractionListener,
                                    secondFragment.OnSecondFragmentInteractionListener,
                                    thirdFragment.OnThirdFragmentInteractionListener{



    private final String TAG = getClass().getSimpleName();

    private HashMap map;

    private Fragment f;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (!map.containsKey("home")) {
                        f = new firstFragment();
                        map.put("home", f);
                    } else {
                        f = (firstFragment) map.get("home");
                    }
                    loadFragment(f);
                    return true;
                case R.id.navigation_dashboard:
                    if (!map.containsKey("dashboard")){
                        f  = new secondFragment();
                        map.put("dashboard",f);
                    } else{
                        f = (secondFragment) map.get("dashboard");
                    }
                    loadFragment(f);
                    return true;
                case R.id.navigation_notifications:
                    if (!map.containsKey("notifications")){
                        f = new thirdFragment();
                        map.put("notifications",f);
                    }else{
                        f = (thirdFragment) map.get("notifications");
                    }
                    loadFragment(f);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //FisrtFragment will be show
        map = new HashMap();
        firstFragment f = new firstFragment();
        loadFragment(f);

        Utilities util = new Utilities(this,this);
        util.checkPermissions();

    }

    public void loadFragment(Fragment newFragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.content_frame, newFragment);
        transaction.disallowAddToBackStack();

        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
