package com.perrinn.client.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.perrinn.client.R;
import com.perrinn.client.adapters.DockItemAdapter;
import com.perrinn.client.beans.DockIndicator;
import com.perrinn.client.fragments.ProfileFragment;
import com.perrinn.client.helpers.DockItemMarginDecorator;

import java.util.ArrayList;

/**
 * This class contains and manages all the fragments used for the settings in the application.
 *
 * @author Alessandro
 * @since 06.07.2016
 */
public class SettingsActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_CAMERA = 0;
    private static final int REQUEST_PERMISSION_GALLERY = 1;

    private RelativeLayout mDock;
    private RecyclerView mPagesIndicatorsList;
    private ArrayList<DockIndicator> mIndicators = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mDock = (RelativeLayout) findViewById(R.id.dock);
        mPagesIndicatorsList = (RecyclerView) findViewById(R.id.pages_indicators_list);

        // setting dummy dots for the moment
        mIndicators.add(new DockIndicator(true));
        mIndicators.add(new DockIndicator(false));
        mIndicators.add(new DockIndicator(false));
        mIndicators.add(new DockIndicator(false));
        mIndicators.add(new DockIndicator(false));
        mIndicators.add(new DockIndicator(false));
        initDock();

        // asking for permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},REQUEST_PERMISSION_CAMERA);
            }
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_PERMISSION_GALLERY);
            }
        }
        addProfileFragment();
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

    /**
     * This method registers and init the stuff needed for the dock handling.
     *
     * */
    private void initDock(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        mPagesIndicatorsList.setLayoutManager(mLayoutManager);
        DockItemAdapter adapter = new DockItemAdapter(this, mIndicators);
        adapter.setOnDockIndicatorClickListener(new DockItemAdapter.OnDockIndicatorClickListener() {
            @Override
            public void onClick(DockIndicator indicator, int position) {
                // TODO: implement it
            }
        });
        mPagesIndicatorsList.setAdapter(adapter);
        mPagesIndicatorsList.addItemDecoration(new DockItemMarginDecorator(this,
                R.dimen.dock_indicator_right_margin));



    }


}
