package com.perrinn.client;



import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
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
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.fragment.WalletFragment;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.perrinn.client.adapters.DockItemAdapter;
import com.perrinn.client.adapters.MainPagerAdapter;
import com.perrinn.client.beans.DockIndicator;

import com.perrinn.client.beans.Member;
import com.perrinn.client.beans.Team;
import com.perrinn.client.fragments.ChatFragment;
import com.perrinn.client.fragments.ChatScreensFragment;
import com.perrinn.client.fragments.CreateNewProjectFragment;
import com.perrinn.client.fragments.LandingFragment;
import com.perrinn.client.fragments.LoginFragment;
import com.perrinn.client.fragments.MapsFragment;
import com.perrinn.client.fragments.ProfileSettingsScreensFragment;
import com.perrinn.client.fragments.TeamFragment;
import com.perrinn.client.fragments.ProfileFragment;
import com.perrinn.client.fragments.ProjectFragment;
import com.perrinn.client.fragments.TeamMateProfileFragment;
import com.perrinn.client.fragments.TeamMembersFragment;
import com.perrinn.client.fragments.TeamScreensFragment;
import com.perrinn.client.fragments.TeamSettingsFragment;
import com.perrinn.client.fragments.TeamSettingsScreensFragment;
import com.perrinn.client.fragments.WalletScreensFragment;
import com.perrinn.client.fragments.WalletTransacHistFragment;
import com.perrinn.client.fragments.WalletTransacHistScreensFragment;
import com.perrinn.client.helpers.DockItemMarginDecorator;
import com.perrinn.client.helpers.DockManager;
import com.perrinn.client.helpers.ToggledViewPager;
import com.perrinn.client.listeners.InputInteractionListener;
import com.perrinn.client.listeners.MultiScreensListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentInteractionListener, TeamScreensFragment.TeamScreensInteractionListener,
        InputInteractionListener, MultiScreensListener, TeamMateProfileFragment.OnPrivateChatButtonInteractionListener,
        WalletScreensFragment.OnWalletScreensInteractionInteractionListener{
    private LinearLayout mDock;
    public RelativeLayout modifiedDock;
    private RecyclerView mPagesIndicatorsList;
    private ImageButton mPSB;
    private DockManager mDockManager;
    private FrameLayout mFragmentContainer;
    private WifiManager mWifiManager;
    private WifiInfo mWifiInfo;
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
    private static final String FRAGMENT_TEAM_MATE_PROFILE = "com.perrinn.client.fragments.FRAGMENT_TEAM_MATE_PROFILE";
    private static final String FRAGMENT_SINGLE_CHAT = "com.perrinn.client.fragments.FRAGMENT_CHAT";
    private static final String FRAGMENT_WALLET_SCREENS = "com.perrinn.client.fragments.FRAGMENT_WALLET_SCREENS";
    private static final String FRAGMENT_WALLET_TRANSAC_HISTORY_SCREENS = "com.perrinn.client.fragments.FRAGMENT_WALLET_TRANSAC_HISTORY_SCREENS";

    public static final int REQUEST_PERMISSIONS_READEXTERNAL_CAMERA = 0;
    public static final int REQUEST_PERMISSIONS_LOCATION = 1;
    public static final int REQUEST_PERMISSIONS_READWRITE = 2;
    public static final int REQUEST_PERMISSIONS_WIFISTATE = 3;

    private ToggledViewPager mFragmentPagerMain;
    private MainPagerAdapter mFragmentPagerMainAdapter;

    private boolean isInSingleFragmentView;
    private String singleFragmentTag;
    private int oldDockPosition = 0;

    private String lastTag;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

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
        mDockManager = new DockManager(new ArrayList<Team>());
        mPagesIndicatorsList = (RecyclerView) findViewById(R.id.pages_indicators_list);
        mPSB = (ImageButton) findViewById(R.id.psb);
        mFragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        if (savedInstanceState == null) {
            initDock();
            initDockManager();
           // Handler handler = new Handler();
           /* handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    addTeamScreensFragment();
                }
            }, 1000); */
        }
        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        mWifiInfo = mWifiManager.getConnectionInfo();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    addTeamScreensFragment();
                }else{
                    registerNewUser(mWifiInfo.getMacAddress().replace(':','_')+"@perrinn.com","password");
                }
            }
        };
        mAuth = FirebaseAuth.getInstance();
        askPermissions();
        mPagesIndicatorsList.setAdapter(new DockItemAdapter(this, mDockManager.getmTeams()));
        if(mFragmentContainer.getViewTreeObserver().isAlive())
            mFragmentContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int delta =  mFragmentContainer.getRootView().getHeight() - mFragmentContainer.getHeight();
                    if(delta > 396){ // FIXME: density is actually calculated on only one device, might change.
                        onKeyboardStateChanged(false);
                    }else{
                        onKeyboardStateChanged(true);
                    }
                }
            });
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

    // region Grid Buttons Listeners
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
    public void onWalletButtonPressed() {
        addWalletsFragment();
    }


    @Override
    public void onSelfPicturePressed() {
        addProfileSettingsFragment();
    }

    @Override
    public void onColleaguePicturePressed(int colleagueID) {
        addTeamMateProfileFragment();
    }
    //endregion


    @Override
    public void onPrivateChatInteraction(Member member) {
        addSingleChatFragment();
    }

    @Override
    public void onPageChange(int position) {
        updateDock(position);
        //ChatScreensFragment chatFragment = (ChatScreensFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_CHAT);
        ProfileSettingsScreensFragment profileSettingsFragment = (ProfileSettingsScreensFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_SETTINGS_PROFILE);
        TeamSettingsScreensFragment teamSettingsFragment = (TeamSettingsScreensFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_SETTINGS_TEAM);
        MapsFragment mapsFragment = (MapsFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_MAPS);
       /* if(chatFragment != null){
            chatFragment.setCurrentItem(position);
        }*/
        if(profileSettingsFragment != null){
            profileSettingsFragment.setCurrentItem(position);
        }
        if(teamSettingsFragment != null){
            teamSettingsFragment.setCurrentItem(position);
        }
        if(mapsFragment != null){
            mapsFragment.selectedTeamChanged(mDockManager.getmTeams().get(mDockManager.getSelectedIndex()));
        }
        ((TeamScreensFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TEAM_SCREENS)).setCurrentItem(position);
    }
    @Override
    public void onPageCountChanged(int count) {
        /*int delta = mDockManager.getmTeams().size() - count;
        if (delta == 0) return;
        delta = delta < 0 ? delta * -1 : delta; // FIXME: dirty but we do not have to call Math big boy.
        for (int i = 0; i < delta; i++)
            mDockManager.addNewDockItem(new DockIndicator(false));
        mPagesIndicatorsList.swapAdapter(new DockItemAdapter(this, mDockManager.getIndicators()), true);*/
    }

    @Override
    public void onKeyboardStateChanged(boolean keyboardHidden) {
        if (keyboardHidden)
            mDock.setVisibility(View.VISIBLE);
        else
            mDock.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthStateListener != null){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
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
            default:
                if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // TODO: prompt a message why we need permissions.
                }else{
                    addLoginFragment();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    /*
    * //////////////////////////////////////////////////
    * // Private methods
    * /////////////////////////////////////////////////
    */

    // region Dock management and dummy data settings
    /**
     * This method registers and init the stuff needed for the dock handling.
     */
    private void initDock() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        mPagesIndicatorsList.setLayoutManager(mLayoutManager);
        DockItemAdapter adapter = new DockItemAdapter(this, mDockManager.getmTeams());
        adapter.setOnDockIndicatorClickListener(new DockItemAdapter.OnDockIndicatorClickListener() {
            @Override
            public void onClick(Team indicator, int position) {
                // TODO: implement it
            }
        });
        mPSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean visible = isFragmentActive(FRAGMENT_TEAM_SCREENS);
                boolean exist = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TEAM_SCREENS) != null;
                if(exist && !visible) {
                    while (!visible && getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        onBackPressed();
                        visible = isFragmentActive(FRAGMENT_TEAM_SCREENS);
                    }
                }else
                    addTeamSettingsFragment();
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
        mDock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeamFragment();
            }
        });
    }

    private void updateDock(int position) {
        if (mDockManager.getmTeams().size() - 1 < position) return;
        mDockManager.onTeamSelected(position);
        mPagesIndicatorsList.swapAdapter(new DockItemAdapter(this,mDockManager.getmTeams()),true);
    }

    /**
     * Inits the dummy data for the application.
     * */
    private void initDockManager(){
        ArrayList<Member> members = new ArrayList<>();
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(new Team("Marketing","Busy preparing our next event.",R.drawable.team_members_background_placeholder,members,true));
        teams.add(new Team("Friends","Always fun stories to share",R.drawable.team_members_background2_placeholder,members,false));
        mDockManager = new DockManager(teams);
    }
    //endregion

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
        if (count == 0) {
            if (!tag.equals(FRAGMENT_TEAM_SCREENS)) return false;
            else return true;
        }
        FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1);
        return entry != null && entry.getName() != null && entry.getName() == tag;
    }

    private void askPermissions(){
        // asking for permission
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                    (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED)) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                        MainActivity.REQUEST_PERMISSIONS_READWRITE);
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_WIFI_STATE},
                        MainActivity.REQUEST_PERMISSIONS_WIFISTATE);
            }
        }else{
            addLoginFragment();
        }
    }

    private void registerNewUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Your account has been successfully created", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Account not registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signinUser(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Could not sign you in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // region Fragment Swaps
    /**
     * This method is intended to create and load the LoginFragment in the main container
     * layout.
     */
    private void addLoginFragment() {
        signinUser(mWifiInfo.getMacAddress()+"@perrinn.com","password");
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
                .add(R.id.fragment_container, MapsFragment.newInstance(mDockManager.getmTeams().get(mDockManager.getSelectedIndex())), FRAGMENT_MAPS)
                .addToBackStack(FRAGMENT_MAPS).commit();
        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addWalletsFragment() {
        if (isFragmentActive(FRAGMENT_WALLET_SCREENS)) return;
        lastTag = FRAGMENT_WALLET_SCREENS;
        if (!isFragmentActive(FRAGMENT_WALLET_SCREENS) && getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, WalletScreensFragment.newInstance(mDockManager.getSelectedIndex(),mDockManager.getmTeams()), FRAGMENT_WALLET_SCREENS)
                .addToBackStack(FRAGMENT_WALLET_SCREENS).commit();
        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addWalletTransacHistFragment() {
        if (isFragmentActive(FRAGMENT_WALLET_TRANSAC_HISTORY_SCREENS)) return;
        lastTag = FRAGMENT_WALLET_TRANSAC_HISTORY_SCREENS;
        if (!isFragmentActive(FRAGMENT_WALLET_TRANSAC_HISTORY_SCREENS) && getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, WalletTransacHistScreensFragment.newInstance(mDockManager.getSelectedIndex(),mDockManager.getmTeams()), FRAGMENT_WALLET_TRANSAC_HISTORY_SCREENS)
                .addToBackStack(FRAGMENT_WALLET_TRANSAC_HISTORY_SCREENS).commit();
        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addTeamScreensFragment() {
       // if (isFragmentActive(FRAGMENT_TEAM_SCREENS)) return;
        lastTag = FRAGMENT_TEAM_SCREENS;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, TeamScreensFragment.newInstance(mDockManager.getmTeams()), FRAGMENT_TEAM_SCREENS)
                .commit();
        //this.mDock.setVisibility(View.VISIBLE);
    }

    private void addTeamFragment() {
        if (isFragmentActive(FRAGMENT_TEAMS))
            return;
        lastTag = FRAGMENT_TEAMS;
        if (!isFragmentActive(FRAGMENT_MAPS) && !isFragmentActive(FRAGMENT_CHAT) && !isFragmentActive(FRAGMENT_SETTINGS_PROFILE) && !isFragmentActive(FRAGMENT_SETTINGS_TEAM) && !isFragmentActive(FRAGMENT_TEAM_SCREENS) && getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, TeamFragment.newInstance(mDockManager.getmTeams()), FRAGMENT_TEAMS)
                .addToBackStack(FRAGMENT_TEAMS).commit();

        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addProfileSettingsFragment() {
        if (isFragmentActive(FRAGMENT_SETTINGS_PROFILE)) return;
        lastTag = FRAGMENT_SETTINGS_PROFILE;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ProfileSettingsScreensFragment.newInstance(mDockManager.getSelectedIndex(),mDockManager.getmTeams()), FRAGMENT_SETTINGS_PROFILE)
                .addToBackStack(FRAGMENT_SETTINGS_PROFILE)
                .commit();
        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addTeamMateProfileFragment(){
        if (isFragmentActive(FRAGMENT_TEAM_MATE_PROFILE)) return;
        if (!isFragmentActive(FRAGMENT_TEAM_SCREENS) && getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        lastTag = FRAGMENT_TEAM_MATE_PROFILE;
        Member dummyMember = new Member("Alan","Turing",R.drawable.placeholder_team_members_4);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, TeamMateProfileFragment.newInstance(dummyMember,mDockManager.getmTeams().get(mDockManager.getSelectedIndex())), FRAGMENT_TEAM_MATE_PROFILE)
                .addToBackStack(FRAGMENT_TEAM_MATE_PROFILE)
                .commit();
        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addTeamSettingsFragment() {
        if (isFragmentActive(FRAGMENT_SETTINGS_TEAM)) return;
        /*if (!isFragmentActive(FRAGMENT_TEAM_SCREENS) && getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();*/
        lastTag = FRAGMENT_SETTINGS_TEAM;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, TeamSettingsScreensFragment.newInstance(mDockManager.getSelectedIndex(),mDockManager.getmTeams()), FRAGMENT_SETTINGS_TEAM)
                .addToBackStack(FRAGMENT_SETTINGS_TEAM)
                .commit();
        this.mDock.setVisibility(View.VISIBLE);
    }

    private void addSingleChatFragment(){
        if (isFragmentActive(FRAGMENT_SINGLE_CHAT)) return;
        if (!isFragmentActive(FRAGMENT_TEAM_SCREENS) && getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        lastTag = FRAGMENT_SINGLE_CHAT;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ChatFragment.newInstance(), FRAGMENT_CHAT)
                .addToBackStack(null)
                .commit();
    }

    //endregion

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
                .add(R.id.fragment_container, ChatScreensFragment.newInstance(mDockManager.getmTeams()), FRAGMENT_CHAT)
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


    @Override
    public void onWalletTransactionHistoryClick() {
        addWalletTransacHistFragment();
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

}
