package com.perrinn.client;



import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.perrinn.client.fragments.CreateNewProjectFragment;
import com.perrinn.client.fragments.LandingFragment;
import com.perrinn.client.fragments.LoadingFragment;
import com.perrinn.client.fragments.ProfileFragment;
import com.perrinn.client.fragments.ProjectFragment;
import com.perrinn.client.fragments.TeamsFragment;

public class MainActivity extends AppCompatActivity implements LoadingFragment.LoadingFragmentInteractionListener {
    /*
     * //////////////////////////////////////////
     * strings for the getsupportfragmentmanagers
     * //////////////////////////////////////////
     */
    private static final String FRAGMENT_LOADING = "com.perrinn.client.fragments.LOADING_FRAGMENT";
    private static final String FRAGMENT_LANDING = "com.perrinn.client.fragments.LANDING_FRAGMENT";
    private static final String FRAGMENT_PROJECT_PAGE = "com.perrinn.client.fragments.PROJECT_FRAGMENT";
    private static final String FRAGMENT_PROFILE = "com.perrinn.client.fragments.PROJECT_PROFILE";
    private static final String FRAGMENT_CREATE_NEW_PROJECT = "com.perrinn.client.fragments.FRAGMENT_NEW_PROJECT";
    private static final String FRAGMENT_TEAMS = "com.perrinn.client.fragments.FRAGMENT_TEAMS";
    private static final String FRAGMENT_CHAT = "com.perrinn.client.fragments.FRAGMENT_CHAT_SCREEN";
   /*
    * //////////////////////////////////////////////////
    * Overrided methods
    * /////////////////////////////////////////////////
    */

    /*
     * //////////////////////////////////////////
     *on create method when the view is created
     * //////////////////////////////////////////
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            addLoadingFragment();

        }
    }

    /*
     * //////////////////////////////////////////
     * create options menu
     * //////////////////////////////////////////
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }

    /*
     * //////////////////////////////////////////
     * options item menu
     * //////////////////////////////////////////
     */
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

    /*
     * these methods are responsible for switching fragments
     * within one activity, main activity
     *
     */
    private void addLoadingFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,LoadingFragment.newInstance(),FRAGMENT_LOADING).commit();
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
                .addToBackStack(null).commit();
    }

    private void addTeamsPage(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, TeamsFragment.newInstance(), FRAGMENT_TEAMS)
                .addToBackStack(FRAGMENT_LANDING).commit();
    }




    /*
     * //////////////////////////////////////////
     * below are the methods that are binded to
     * the XML layouts for when elements are interacted with
     * //////////////////////////////////////////
     */
    @Override
    public void onTextInteraction() {
        addLandingPage();
    }
    public void onPressProfileButtonInteraction(View v){
        addNewProfilePage();
    }
    public void onPressProjectButtonInteraction(View v){addProjectPage();}
    public void onPressCreateNewProjectButtonInteraction(View v){addCreateNewProjectPage();}
    public void onPressTeamsButton(View v){addTeamsPage();}
}

