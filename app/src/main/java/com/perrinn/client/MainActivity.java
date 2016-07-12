package com.perrinn.client;



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
import com.perrinn.client.fragments.ChatFragment;
import com.perrinn.client.fragments.CreateNewProjectFragment;
import com.perrinn.client.fragments.LandingFragment;
import com.perrinn.client.fragments.LoadingFragment;
import com.perrinn.client.fragments.ProfileFragment;
import com.perrinn.client.fragments.ProjectFragment;
import com.perrinn.client.fragments.TeamsFragment;
import com.perrinn.client.helpers.DockItemMarginDecorator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoadingFragment.LoadingFragmentInteractionListener {
    private RelativeLayout mDock;
    private RecyclerView mPagesIndicatorsList;
    private ArrayList<DockIndicator> mIndicators = new ArrayList<>();
    private static final String FRAGMENT_LOADING = "com.perrinn.client.fragments.LOADING_FRAGMENT";
    private static final String FRAGMENT_LANDING = "com.perrinn.client.fragments.LANDING_FRAGMENT";
    private static final String FRAGMENT_PROJECT_PAGE = "com.perrinn.client.fragments.PROJECT_FRAGMENT";
    private static final String FRAGMENT_PROFILE = "com.perrinn.client.fragments.PROJECT_PROFILE";
    private static final String FRAGMENT_CREATE_NEW_PROJECT = "com.perrinn.client.fragments.CREATE_NEW_PROJECT_FRAGMENT";
    private static final String FRAGMENT_TEAMS = "com.perrinn.client.fragments.FRAGMENT_TEAMS";
    private static final String FRAGMENT_CHAT = "com.perrinn.client.fragments.FRAGMENT_CHAT_SCREEN";
   /*
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



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
            addLoadingFragment();
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

    private void addLandingPage(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, LandingFragment.newInstance(),FRAGMENT_LANDING)
                .addToBackStack(FRAGMENT_LANDING).commit();
    }

    private void addProjectPage(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ProjectFragment.newInstance(), FRAGMENT_PROJECT_PAGE)
                .addToBackStack(FRAGMENT_LANDING).commit();
    }

    private void addNewProfilePage(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment.newInstance(), FRAGMENT_PROFILE)
                .addToBackStack(FRAGMENT_LANDING).commit();
    }

    private void addCreateNewProjectPage(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, CreateNewProjectFragment.newInstance(), FRAGMENT_CREATE_NEW_PROJECT)
                .addToBackStack(FRAGMENT_PROJECT_PAGE).commit();
    }

    private void addChatPage(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ChatFragment.newInstance(), FRAGMENT_CHAT)
                .addToBackStack(FRAGMENT_LANDING).commit();
    }

    private void addTeamsPage(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, TeamsFragment.newInstance(), FRAGMENT_TEAMS)
                .addToBackStack(FRAGMENT_LANDING).commit();
    }




    @Override
    public void onTextInteraction() {
        addLandingPage();
    }

    public void onPressProfileButtonInteraction(View v){
        addNewProfilePage();
    }
    public void onPressProjectButtonInteraction(View v){
        addProjectPage();
    }
    //MAKES NO SENSE, the two functions above work just fine?
    //bug: java.lang.IllegalStateException: Could not find method addCreateNewProjectPage(View) in a parent or ancestor Context for android:onClick
    //attribute defined on view class android.support.v7.widget.AppCompatButton with id 'createNewProjectButton'
    public void onPressCreateNewProjectButtonInteraction(View v){
        addCreateNewProjectPage();
    }
    public void onPressChatButton(View v){ addChatPage(); }
    public void onPressTeamsButton(View v){addTeamsPage();}
}
