package com.perrinn.client;



import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.perrinn.client.activities.SettingsActivity;
import com.perrinn.client.adapters.DockItemAdapter;
import com.perrinn.client.adapters.MainPagerAdapter;
import com.perrinn.client.beans.DockIndicator;

import com.perrinn.client.fragments.ChatFragment;
import com.perrinn.client.fragments.CreateNewProjectFragment;
import com.perrinn.client.fragments.LandingFragment;
import com.perrinn.client.fragments.LoginFragment;
import com.perrinn.client.fragments.MapsFragment;
import com.perrinn.client.fragments.TeamFragment;
import com.perrinn.client.fragments.ProfileFragment;
import com.perrinn.client.fragments.ProjectFragment;
import com.perrinn.client.fragments.TeamMembersFragment;
import com.perrinn.client.fragments.TeamScreensFragment;
import com.perrinn.client.fragments.TeamSettingsFragment;
import com.perrinn.client.helpers.DockItemMarginDecorator;
import com.perrinn.client.helpers.DockManager;
import com.perrinn.client.helpers.ToggledViewPager;
import com.perrinn.client.listeners.InputInteractionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentInteractionListener, TeamScreensFragment.TeamScreensInteractionListener,
        InputInteractionListener {
    private LinearLayout mDock;
    public RelativeLayout modifiedDock;
    private RecyclerView mPagesIndicatorsList;
    private ImageButton mPSB;
    private DockManager mDockManager;
    private static final String FRAGMENT_LOADING = "com.perrinn.client.fragments.LOADING_FRAGMENT";
    private static final String FRAGMENT_LANDING = "com.perrinn.client.fragments.LANDING_FRAGMENT";
    private static final String FRAGMENT_PROJECT_PAGE = "com.perrinn.client.fragments.PROJECT_FRAGMENT";
    private static final String FRAGMENT_PROFILE = "com.perrinn.client.fragments.PROJECT_PROFILE";
    private static final String FRAGMENT_CREATE_NEW_PROJECT = "com.perrinn.client.fragments.FRAGMENT_NEW_PROJECT";
    private static final String FRAGMENT_TEAMS = "com.perrinn.client.fragments.FRAGMENT_TEAMS";
    private static final String FRAGMENT_CHAT = "com.perrinn.client.fragments.FRAGMENT_CHAT_SCREEN";
    private static final String FRAGMENT_TEAM_SCREENS = "com.perrinn.client.fragments.FRAGMENT_TEAM_SCREENS";
    private static final String FRAGMENT_SETTINGS_PROFILE = "com.perrinn.client.fragments.FRAGMENT_SETTINGS_PROFILE";
    private static final String FRAGMENT_SETTINGS_TEAM = "com.perrinn.client.fragments.FRAGMENT_SETTINGS_TEAM";
    private static final String FRAGMENT_MAPS = "com.perrinn.client.fragments.FRAGMENT_MAPS";

    public static final int REQUEST_PERMISSIONS_READEXTERNAL_CAMERA = 0;
    public static final int REQUEST_PERMISSIONS_LOCATION = 1;

    private ToggledViewPager mFragmentPagerMain;
    private MainPagerAdapter mFragmentPagerMainAdapter;

    private boolean isInSingleFragmentView;
    private String singleFragmentTag;
    private int oldDockPosition = 0;

    private String lastTag;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /*
    * //////////////////////////////////////////////////
    * // Overrided methods
    * /////////////////////////////////////////////////
    */

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDock = (LinearLayout) findViewById(R.id.dock);
        modifiedDock = (RelativeLayout) findViewById(R.id.chatdock);
        mDockManager = new DockManager(new ArrayList<DockIndicator>());
        mPagesIndicatorsList = (RecyclerView) findViewById(R.id.pages_indicators_list);
        mPSB = (ImageButton) findViewById(R.id.psb);
        if (savedInstanceState == null) {
            addLoginFragment();
            initDock();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    addTeamScreensFragment();
                }
            }, 1000);
        }
        mPagesIndicatorsList.setAdapter(new DockItemAdapter(this, mDockManager.getIndicators()));


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*int historySize = event.getHistorySize();
        int pointerCount = event.getPointerCount();
        for(int i = 0; i < historySize;i++){
            System.out.print("Detected event at: "+event.getHistoricalEventTime(i));
            for(int j = 0; j < pointerCount;j++){
                System.out.println(" pointer "+j+": ("+event.getHistoricalX(j,i)+","+event.getHistoricalY(j,i)+")");
            }
        }*/
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onChatButtonPressed() {
        addChatPage();
    }

    @Override
    public void onMailButtonPressed() {

    }

    @Override
    public void onCalendarButtonPressed() {

    }

    @Override
    public void onActivityButtonPressed() {

    }

    @Override
    public void onDocumentsButtonPressed() {

    }

    @Override
    public void onGuestButtonPressed() {

    }

    @Override
    public void onImages01ButtonPressed() {

    }

    @Override
    public void onImages02ButtonPressed() {

    }

    @Override
    public void onMapsButtonPressed() {
        addMapsFragment();
    }

    @Override
    public void onMicButtonPressed() {

    }

    @Override
    public void onSpeakerButtonPressed() {

    }

    @Override
    public void onPageChange(int position) {
        updateDock(position);
    }

    @Override
    public void onSelfPicturePressed() {
        addProfileSettingsFragment();
    }

    @Override
    public void onColleaguePicturePressed(int colleagueID) {

    }

    @Override
    public void onPageCountChanged(int count) {
        int delta = mDockManager.getIndicators().size() - count;
        if (delta == 0) return;
        delta = delta < 0 ? delta * -1 : delta; // FIXME: dirty but we do not have to call Math big boy.
        for (int i = 0; i < delta; i++)
            mDockManager.addNewDockItem(new DockIndicator(false));
        mPagesIndicatorsList.swapAdapter(new DockItemAdapter(this, mDockManager.getIndicators()), true);
    }

    @Override
    public void onKeyboardStateChanged(boolean keyboardHidden) {
        if (keyboardHidden)
            mDock.setVisibility(View.VISIBLE);
        else
            mDock.setVisibility(View.GONE);
    }

    @Override
    public void onComplete() {
        this.mDock.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        FragmentManager fm = getSupportFragmentManager();
        switch (requestCode) {
            case REQUEST_PERMISSIONS_LOCATION:
                MapsFragment mapsFragment = (MapsFragment) fm.findFragmentByTag(FRAGMENT_MAPS);
                mapsFragment.onRequestPermissionsResult(requestCode,permissions,grantResults);
                break;
            case REQUEST_PERMISSIONS_READEXTERNAL_CAMERA:
                ProfileFragment profileFragment = (ProfileFragment) fm.findFragmentByTag(FRAGMENT_SETTINGS_PROFILE);
                profileFragment.onRequestPermissionsResult(requestCode,permissions,grantResults);
                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
    /*
    * //////////////////////////////////////////////////
    * // Private methods
    * /////////////////////////////////////////////////
    */

    /**
     * This method registers and init the stuff needed for the dock handling.
     */
    private void initDock() {
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
        mPSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean visible = isFragmentActive(FRAGMENT_TEAM_SCREENS);
                if (!visible && getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    onBackPressed();
                } else addTeamSettingsFragment();
            }
        });
        /*mPagesIndicatorsList.addItemDecoration(new DockItemMarginDecorator(this,
                R.dimen.dock_indicator_right_margin));*/
        mPagesIndicatorsList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_DOWN)
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
    }

    private void updateDock(int position) {
        if (mDockManager.getIndicators().size() - 1 < position) return;
        mDockManager.getIndicators().get(oldDockPosition).setActive(false);
        mDockManager.getIndicators().get(position).setActive(true);
        mPagesIndicatorsList.swapAdapter(new DockItemAdapter(this, mDockManager.getIndicators()), true);
        oldDockPosition = position;
    }

    /**
     * This method tests if the fragment given exists in the backstack and if he's currently visible.
     *
     * @param tag the tag of the fragment requested.
     * @return boolean true if the fragment is visible.
     */
    private boolean isFragmentActive(String tag) {
        FragmentManager manager = getSupportFragmentManager();
        if (manager == null) return false;
        int count = manager.getBackStackEntryCount();
        if (count == 0) return false;
        FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1);
        return entry != null && entry.getName() != null && entry.getName() == tag;
    }

    /**
     * This method is intended to create and load the LoginFragment in the main container
     * layout.
     */
    private void addLoginFragment() {
        if (isFragmentActive(FRAGMENT_LOADING)) return;
        lastTag = FRAGMENT_LOADING;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, LoginFragment.newInstance(), FRAGMENT_LOADING)
                .commit();
        this.mDock.setVisibility(View.GONE);
    }

    private void addMapsFragment() {
        if (isFragmentActive(FRAGMENT_MAPS)) return;
        lastTag = FRAGMENT_MAPS;
        if (!isFragmentActive(FRAGMENT_MAPS) && getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, MapsFragment.newInstance(), FRAGMENT_MAPS)
                .addToBackStack(FRAGMENT_MAPS).commit();
        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addTeamScreensFragment() {
        if (isFragmentActive(FRAGMENT_TEAM_SCREENS)) return;
        lastTag = FRAGMENT_TEAM_SCREENS;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, TeamScreensFragment.newInstance(), FRAGMENT_TEAM_SCREENS)
                .commit();
        //this.mDock.setVisibility(View.VISIBLE);
    }

    private void addTeamFragment() {
        if (isFragmentActive(FRAGMENT_TEAMS)) return;
        lastTag = FRAGMENT_TEAMS;
        if (!isFragmentActive(FRAGMENT_TEAM_SCREENS) && getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, TeamFragment.newInstance(), FRAGMENT_TEAMS)
                .addToBackStack(FRAGMENT_TEAMS).commit();

        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addProfileSettingsFragment() {
        if (isFragmentActive(FRAGMENT_SETTINGS_PROFILE)) return;
        lastTag = FRAGMENT_SETTINGS_PROFILE;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ProfileFragment.newInstance(), FRAGMENT_SETTINGS_PROFILE)
                .addToBackStack(FRAGMENT_SETTINGS_PROFILE)
                .commit();
        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addTeamSettingsFragment() {
        if (isFragmentActive(FRAGMENT_SETTINGS_TEAM)) return;
        if (!isFragmentActive(FRAGMENT_TEAM_SCREENS) && getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        lastTag = FRAGMENT_SETTINGS_TEAM;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, TeamSettingsFragment.newInstance(), FRAGMENT_SETTINGS_TEAM)
                .addToBackStack(null)
                .commit();
        this.mDock.setVisibility(View.VISIBLE);
    }

    /*
    * //////////////////////////////////////////////////
    * //the methods below are responsible for switching fragments inside mainactivity
    * /////////////////////////////////////////////////
    */

    /*

    private void addLandingPage(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, LandingFragment.newInstance(),FRAGMENT_LANDING)
                .addToBackStack(FRAGMENT_LANDING).commit();

        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addProjectPage(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ProjectFragment.newInstance(), FRAGMENT_PROJECT_PAGE)
                .addToBackStack(FRAGMENT_LANDING).commit();

        this.mDock.setVisibility(View.INVISIBLE);
    }

    private void addNewProfilePage(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment.newInstance(), FRAGMENT_PROFILE)
                .addToBackStack(FRAGMENT_LANDING).commit();

        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addCreateNewProjectPage(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, CreateNewProjectFragment.newInstance(), FRAGMENT_CREATE_NEW_PROJECT)
                .addToBackStack(null).commit();

        this.mDock.setVisibility(View.VISIBLE);
    }*/

    private void addChatPage() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ChatFragment.newInstance(), FRAGMENT_CHAT)
                .addToBackStack(FRAGMENT_CHAT)
                .commit();
        this.mDock.setVisibility(View.VISIBLE);
    }

    /*
        * //////////////////////////////////////////////////
        * //the functions below are binded to the XML layout files and are called whenever a UI element
        * is engaged by the user
        * /////////////////////////////////////////////////
        */
    @Override
    public void onLoginEnterButtonInteraction() {
        addTeamScreensFragment();
    }


    @Override
    public void onLoginCreateTeamButtonInteraction() {
        // TODO: implement create a team view
    }

    @Override
    public void onLoginJoinTeamButtonInteraction() {
        // TODO: implement join a team view
    }

    public void onPressProfileButtonInteraction(View v) {
        //addNewProfilePage();
    }

    public void onPressChatButtonInteraction(View v) {
        addChatPage();

    }

    public void onPressProjectButtonInteraction(View v) {
        //addProjectPage();
    }

    public void onPressCreateNewProjectButtonInteraction(View v) {
        System.out.println("got to onPressNewProjectButtonInteraction reached");
        //addCreateNewProjectPage();
        System.out.println("Exiting function");
    }

    public void onPressChatButton(View v) {
        addChatPage();
    }

    public void onPressTeamsButton(View v) {
        //addTeamsPage();
        //addTeamFragment();
    }

    public void onPressTeamScreenButton(View v) {
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.perrinn.client/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.perrinn.client/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
