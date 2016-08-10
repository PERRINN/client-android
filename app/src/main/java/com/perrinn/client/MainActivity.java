package com.perrinn.client;



import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.perrinn.client.activities.SettingsActivity;
import com.perrinn.client.adapters.DockItemAdapter;
import com.perrinn.client.adapters.MainPagerAdapter;
import com.perrinn.client.beans.DockIndicator;

import com.perrinn.client.fragments.ChatFragment;
import com.perrinn.client.fragments.CreateNewProjectFragment;
import com.perrinn.client.fragments.LandingFragment;
import com.perrinn.client.fragments.LoginFragment;
import com.perrinn.client.fragments.TeamFragment;
import com.perrinn.client.fragments.ProfileFragment;
import com.perrinn.client.fragments.ProjectFragment;
import com.perrinn.client.fragments.TeamMembersFragment;
import com.perrinn.client.helpers.DockItemMarginDecorator;
import com.perrinn.client.helpers.ToggledViewPager;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentInteractionListener,
        TeamMembersFragment.OnTeamMembersFragmentInteractionListener {
    private RelativeLayout mDock;
    private RecyclerView mPagesIndicatorsList;
    private ImageButton mPSB;
    private ArrayList<DockIndicator> mIndicators = new ArrayList<>();
    private static final String FRAGMENT_LOADING = "com.perrinn.client.fragments.LOADING_FRAGMENT";
    private static final String FRAGMENT_LANDING = "com.perrinn.client.fragments.LANDING_FRAGMENT";
    private static final String FRAGMENT_PROJECT_PAGE = "com.perrinn.client.fragments.PROJECT_FRAGMENT";
    private static final String FRAGMENT_PROFILE = "com.perrinn.client.fragments.PROJECT_PROFILE";
    private static final String FRAGMENT_CREATE_NEW_PROJECT = "com.perrinn.client.fragments.FRAGMENT_NEW_PROJECT";
    private static final String FRAGMENT_TEAMS = "com.perrinn.client.fragments.FRAGMENT_TEAMS";
    private static final String FRAGMENT_CHAT = "com.perrinn.client.fragments.FRAGMENT_CHAT_SCREEN";
    private static final String FRAGMENT_TEAM_MEMBERS = "com.perrinn.client.fragments.FRAGMENT_TEAM_MEMBERS";

    private ToggledViewPager mFragmentPagerMain;
    private MainPagerAdapter mFragmentPagerMainAdapter;

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
        mPSB = (ImageButton) findViewById(R.id.psb);
        mFragmentPagerMain = (ToggledViewPager) findViewById(R.id.fragment_pager_main);

        mFragmentPagerMainAdapter = new MainPagerAdapter(getSupportFragmentManager(),null);
        mFragmentPagerMain.setAdapter(mFragmentPagerMainAdapter);
        mFragmentPagerMain.setSwipeEnabled(false);

        // setting dummy dots for the moment
        mIndicators.add(new DockIndicator(true));
        mIndicators.add(new DockIndicator(false));
        mIndicators.add(new DockIndicator(false));
        mIndicators.add(new DockIndicator(false));
        mIndicators.add(new DockIndicator(false));
        mIndicators.add(new DockIndicator(false));

        initDock();

        if (savedInstanceState == null) {
            addLoginFragment();
        }
        mPagesIndicatorsList.setAdapter(new DockItemAdapter(this,mIndicators));

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

    }

    @Override
    public void onMicButtonPressed() {

    }

    @Override
    public void onSpeakerButtonPressed() {

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
        mPSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSettingsActivity();
            }
        });
        mPagesIndicatorsList.addItemDecoration(new DockItemMarginDecorator(this,
                R.dimen.dock_indicator_right_margin));
    }

    private void startSettingsActivity(){
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    /**
     * This method is intended to create and load the LoginFragment in the main container
     * layout.
     *
     * */
    private void addLoginFragment(){
        /*getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, LoginFragment.newInstance())
                .commit();*/
        /*mFragmentPagerMainAdapter.addNewFragment(FRAGMENT_LOADING,LoginFragment.newInstance());
        mFragmentPagerMainAdapter.notifyDataSetChanged();
        mFragmentPagerMain.setCurrentItem(0);*/
        switchToOneFragment(FRAGMENT_LOADING,LoginFragment.newInstance(),true);
        this.mDock.setVisibility(View.INVISIBLE);
    }

    /*
* //////////////////////////////////////////////////
* //the methods below are responsible for switching fragments inside mainactivity
* /////////////////////////////////////////////////
*/
    /*private void addTeamFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, TeamFragment.newInstance())
                .addToBackStack(FRAGMENT_LANDING).commit();

        this.mDock.setVisibility(View.VISIBLE);
    }

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

    private void addChatPage(){

        switchToOneFragment(FRAGMENT_CHAT,ChatFragment.newInstance(),true);
        this.mDock.setVisibility(View.VISIBLE);

    }

    /**
     * This method allows the switch between a single fragment shown to
     * the teams screens in the viewpager.
     *
     * @param oldFragmentTag the tag of the single fragment if suppression is needed.
     * */
    private void switchToTeamMembersScreen(String oldFragmentTag){
        mFragmentPagerMainAdapter.removeFragment(oldFragmentTag);
        mFragmentPagerMainAdapter.notifyDataSetChanged();
        mFragmentPagerMain.setSwipeEnabled(true);
        this.mDock.setVisibility(View.VISIBLE);

    }

    /**
     * This method adds a new TeamMembersFragment to the viewpager.
     *
     * @param tag the tag of the new fragment.
     * @param rebuild if the adapter needs to be reset.
     * @param transit transition needed to the freshly added fragment.
     * */
    private void addNewTeamMembersFragment(String tag, boolean rebuild, boolean transit){
        if(rebuild){
            mFragmentPagerMainAdapter.addNewFragment("TEAM_MEMBERS_MARKETING", TeamMembersFragment.newInstance());
            mFragmentPagerMainAdapter.notifyDataSetChanged();
            mFragmentPagerMain.setAdapter(mFragmentPagerMainAdapter);
        }
        mFragmentPagerMainAdapter.addNewFragment(tag, TeamMembersFragment.newInstance());
        mFragmentPagerMainAdapter.notifyDataSetChanged();
        if(transit)
            mFragmentPagerMain.setCurrentItem(mFragmentPagerMainAdapter.goToFragment(tag));
    }

    /**
     * This method allows the switch between the teams members viewpager to a single fragment showing.
     *
     * @param tag the tag of the single fragment's tag.
     * @param fragment the single fragment to add.
     * @param rebuild if the adapter has to be reset.
     * */
    private void switchToOneFragment(String tag, Fragment fragment, boolean rebuild){
        mFragmentPagerMain.setSwipeEnabled(false);
        mFragmentPagerMainAdapter.addNewFragment(tag,fragment);
        mFragmentPagerMainAdapter.notifyDataSetChanged();
        if(rebuild)
            mFragmentPagerMain.setAdapter(mFragmentPagerMainAdapter);
        mFragmentPagerMain.setCurrentItem(mFragmentPagerMainAdapter.goToFragment(tag));
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            this.mDock.setVisibility(View.INVISIBLE); // to remove the dock on the login fragment
        }
    }

    /*
        * //////////////////////////////////////////////////
        * //the functions below are binded to the XML layout files and are called whenever a UI element
        * is engaged by the user
        * /////////////////////////////////////////////////
        */
    @Override
    public void onLoginEnterButtonInteraction() {
        //addLandingPage();
        switchToTeamMembersScreen(FRAGMENT_LOADING);
        addNewTeamMembersFragment("TEAM_MEMBERS_MARKETING",true,false);
        addNewTeamMembersFragment("TEAM_MEMBERS_FRIENDS",false,false);
    }


    @Override
    public void onLoginCreateTeamButtonInteraction() {
        // TODO: implement create a team view
    }

    @Override
    public void onLoginJoinTeamButtonInteraction() {
        // TODO: implement join a team view
    }

    public void onPressProfileButtonInteraction(View v){
    //addNewProfilePage();
        startSettingsActivity();
    }

    public void onPressChatButtonInteraction(View v){
        addChatPage();

    }
    public void onPressProjectButtonInteraction(View v){
        //addProjectPage();
    }
    public void onPressCreateNewProjectButtonInteraction(View v){
        System.out.println("got to onPressNewProjectButtonInteraction reached");
        //addCreateNewProjectPage();
        System.out.println("Exiting function");
    }
    public void onPressChatButton(View v){ addChatPage();}
    public void onPressTeamsButton(View v){
        //addTeamsPage();
        //addTeamFragment();
    }
    public void onPressTeamScreenButton(View v){
        switchToTeamMembersScreen(FRAGMENT_LOADING);
        addNewTeamMembersFragment("TEAM_MEMBERS_MARKETING",true,false);
        addNewTeamMembersFragment("TEAM_MEMBERS_FRIENDS",false,false);
    }


}
