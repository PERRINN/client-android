package com.perrinn.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


import com.perrinn.client.adapters.DockItemAdapter;
import com.perrinn.client.beans.DockIndicator;
import com.perrinn.client.fragments.LoadingFragment;
import com.perrinn.client.fragments.TeamFragment;
import com.perrinn.client.helpers.DockItemMarginDecorator;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RelativeLayout mDock;
    private RecyclerView mPagesIndicatorsList;
    private ArrayList<DockIndicator> mIndicators = new ArrayList<>();


    /*
    * //////////////////////////////////////////////////
    * // Overrided methods
    * /////////////////////////////////////////////////
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        if (savedInstanceState == null) {
            addTeamFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @Override
    public void onStart() {
        super.onStart();

    }

    /** Called after data retrieval is complete to switch to landing view */
    public void landingPageIntent(View view) {
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
    * //////////////////////////////////////////////////
    * // Private methods
    * /////////////////////////////////////////////////
    */

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

    /**
     * This method is intended to create and load the LoadingPageFragment in the main container
     * layout.
     *
     * */
    private void addLoadingFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, LoadingFragment.newInstance())
                .commit();
    }

    /**
     * This method is intended to create and load the TeamFragment in the main container
     * layout.
     *
     * */
    private void addTeamFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, TeamFragment.newInstance())
                .commit();
    }
}
