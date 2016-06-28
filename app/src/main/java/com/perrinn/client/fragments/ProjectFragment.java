package com.perrinn.client.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrinn.client.R;

/**
 * A fragment containing a simple view.
 */
public class ProjectFragment extends Fragment {

    public ProjectFragment() {
    }

    /*
    * //////////////////////////////////////////////////
    * // Overrided methods
    * /////////////////////////////////////////////////
    */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_project, container, false);

        return rootView;
    }

    /*
    * //////////////////////////////////////////////////
    * // Public methods
    * /////////////////////////////////////////////////
    */

    /**
     * This method creates a new instance of the fragment and return it to the caller.
     *
     * @return ProjectFragment the new fragment created.
     * */
    public static ProjectFragment newInstance(){
        ProjectFragment fragment = new ProjectFragment();
        return fragment;
    }

}