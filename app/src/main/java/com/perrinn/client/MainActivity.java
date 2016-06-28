package com.perrinn.client;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.perrinn.client.R;


import com.perrinn.client.fragments.LandingFragment;
import com.perrinn.client.fragments.LoadingFragment;
import com.perrinn.client.fragments.ProfileFragment;
import com.perrinn.client.fragments.ProjectFragment;


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

    /*
     * This method is intended to create and load the LoadingPageFragment in the main container
     * layout.
     *
     *
     */
    private void addLoadingFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, LoadingFragment.newInstance())
                .commit();
    }


 }
