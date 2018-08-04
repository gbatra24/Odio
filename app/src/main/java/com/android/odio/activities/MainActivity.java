package com.android.odio.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.odio.R;
import com.android.odio.fragments.RecordFragment;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.crash.FirebaseCrash;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

//    private PagerSlidingTabStrip tabs;
//    private ViewPager pager;
    private Toolbar toolbar;
    private TextView bottomSheetTitle;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        /*FirebaseCrash.logcat(Log.ERROR, "MainActivity", "NPE caught");
        FirebaseCrash.report(ex);*/
        toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /*ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE},10);*/

        bottomSheetTitle = findViewById(R.id.bottomsheet_header);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        RecordFragment fragment = new RecordFragment();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();

        LinearLayout bottomSheetLayout = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN: {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                    }
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        bottomSheetTitle.setText(R.string.bottomsheet_down);
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        bottomSheetTitle.setText(R.string.bottomsheet_up);
                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING: {
                    }
                        break;
                    case BottomSheetBehavior.STATE_SETTLING: {
                    }
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

//        Crashlytics.getInstance().crash();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
