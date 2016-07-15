package com.perrinn.client.fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perrinn.client.R;
/**
 * Fragment to create a new project
 */
public class CreateNewProjectFragment extends Fragment {


    /*
    * //////////////////////////////////////////
    * Constructor
    * //////////////////////////////////////////
    */
    public CreateNewProjectFragment() {
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

        //todo: on create should populate the project owner button with the current user's name/nick
        //todo: when database is available
    }

    /*
     * //////////////////////////////////////////
     * inflate the fragment upon creating the view
     * //////////////////////////////////////////
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_project, container, false);



        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    /*
    * //////////////////////////////////////////////////
    * // Public methods
    * /////////////////////////////////////////////////
    */
    /**
     * This method creates a new instance of the fragment and return it to the caller.
     *
     * @return CreateNewProjectFragment the new fragment created.
     * */
    public static CreateNewProjectFragment newInstance(){
        CreateNewProjectFragment fragment = new CreateNewProjectFragment();

        return fragment;
    }

    //function for inserting members
    public void insertMembersToProject(){

    }

    //function for inserting team
    public void insertTeamsToProject(){

    }

    //grab owner name and put as
    public void getOwnerName(){

    }

}