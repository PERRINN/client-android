package com.perrinn.client.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrinn.client.R;

/**
 * A fragment containing a simple view.
 */
public class LandingFragment extends Fragment {

    public LandingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
   /*
    *Set onclicklistener for projects button, when pressed then replace the current fragment
    * with the project fragment
    */
        View.OnClickListener projectButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectFragment fragment = new ProjectFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment); //Container -> R.id.contentFragment
                transaction.commit();
            }
        };

   /*
    *Set onclicklistener for projects button, when pressed then replace the current fragment
    * with the profile fragment
    */
        View.OnClickListener profileButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment fragment = new ProfileFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment); //Container -> R.id.contentFragment
                transaction.commit();
            }
        };

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_landing, container, false);

        return rootView;
    }

    /**
     * This method creates a new instance of the fragment and return it to the caller.
     *
     * @return LoadingFragment the new fragment created.
     * */
    public static LandingFragment newInstance(){
        LandingFragment fragment = new LandingFragment();
        return fragment;
    }

}