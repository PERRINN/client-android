package com.perrinn.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import com.perrinn.client.fragments.LoadingFragment;
import com.perrinn.client.fragments.TeamFragment;


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
