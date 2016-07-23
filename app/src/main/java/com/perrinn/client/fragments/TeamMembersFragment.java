package com.perrinn.client.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrinn.client.R;

/**
 * Created by Antreas Christofi on 7/22/2016.
 */
public class TeamMembersFragment extends Fragment {

    /*
     *
     *Constructor
     *
     */
    public TeamMembersFragment(){

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

    /*
* //////////////////////////////////////////////////
* // launches once view is created
* /////////////////////////////////////////////////
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_members, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    /* This method creates a new instance of the fragment and return it to the caller.
            *
            * @return LandingFragment the new fragment created.
            * */
    public static TeamMembersFragment newInstance(){
        TeamMembersFragment fragment = new TeamMembersFragment();

        return fragment;
    }
}
