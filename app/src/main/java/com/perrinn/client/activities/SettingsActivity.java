package com.perrinn.client.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.perrinn.client.R;
import com.perrinn.client.adapters.DockItemAdapter;
import com.perrinn.client.beans.DockIndicator;
import com.perrinn.client.fragments.ProfileFragment;
import com.perrinn.client.fragments.TeamFragment;
import com.perrinn.client.fragments.TeamSettingsFragment;
import com.perrinn.client.helpers.DockItemMarginDecorator;
import com.perrinn.client.helpers.DockManager;

import java.util.ArrayList;

/**
 * This class contains and manages all the fragments used for the settings in the application.
 *
 * @author Alessandro
 * @since 06.07.2016
 */
public class SettingsActivity extends AppCompatActivity {
    public static final int SHOW_PROFILE_SETTINGS = 0;
    public static final int SHOW_TEAM_SETTINGS = 1;
    public static final String PARAM_SHOW_SETTINGS = "com.perrinn.client.SettingsActivity.PARAM_SHOW_SETTINGS";
    private static final int REQUEST_PERMISSION_CAMERA = 0;
    private static final int REQUEST_PERMISSION_GALLERY = 1;

    private DockManager mDockManager;
    private RelativeLayout mDock;
    private ImageButton mPSB;
    private RecyclerView mPagesIndicatorsList;
    private ArrayList<DockIndicator> mIndicators = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mDock = (RelativeLayout) findViewById(R.id.dock);
        mPagesIndicatorsList = (RecyclerView) findViewById(R.id.pages_indicators_list);
        mPSB = (ImageButton) findViewById(R.id.psb);
        initDock();


        Intent showSettings = getIntent();
        if(showSettings != null && showSettings.hasExtra(PARAM_SHOW_SETTINGS)){
            switch(showSettings.getExtras().getInt(PARAM_SHOW_SETTINGS)){
                case SHOW_PROFILE_SETTINGS:
                    addProfileFragment();
                    break;
                case SHOW_TEAM_SETTINGS:
                    addTeamSettingsFragment();
                    break;
                default:
                    addTeamSettingsFragment();
            }
        }else
            addTeamSettingsFragment();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSION_CAMERA){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED);
            else finish();
        } else if(requestCode == REQUEST_PERMISSION_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED);
            else finish();
        }

    }

    /**
     * This method will replace the current showing fragment of the settings activity
     * with the profile settings fragment.
     *
     * */
    private void addProfileFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.settings_fragment_container, ProfileFragment.newInstance())
                .commit();
    }

    private void addTeamSettingsFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.settings_fragment_container, TeamSettingsFragment.newInstance(null,0))
                .commit();
    }

    private void addTeamFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.settings_fragment_container, TeamFragment.newInstance())
                .commit();
    }

    /**
     * This method registers and init the stuff needed for the dock handling.
     *
     * */
    private void initDock(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        mPagesIndicatorsList.setLayoutManager(mLayoutManager);
        DockItemAdapter adapter = new DockItemAdapter(this, mDockManager.getIndicators());
        adapter.setOnDockIndicatorClickListener(new DockItemAdapter.OnDockIndicatorClickListener() {
            @Override
            public void onClick(DockIndicator indicator, int position) {
                // TODO: implement it
            }
        });
        mPagesIndicatorsList.setAdapter(adapter);
        mPagesIndicatorsList.addItemDecoration(new DockItemMarginDecorator(this,
                R.dimen.dock_indicator_right_margin));
        mPagesIndicatorsList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if(e.getAction() == MotionEvent.ACTION_DOWN)
                    addTeamFragment();
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        mPSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
