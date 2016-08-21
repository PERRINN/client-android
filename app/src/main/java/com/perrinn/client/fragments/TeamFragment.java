package com.perrinn.client.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.perrinn.client.R;
import com.perrinn.client.adapters.TeamAdapter;
import com.perrinn.client.beans.Team;

import java.util.ArrayList;

/**
 * This class manages the TeamFragment, were the user can access the list of teams he's in and
 * also create new ones.
 *
 * @since 30.06.2016
 * @author Alessandro
 */
public class TeamFragment extends Fragment {
    private static final String FRAGMENT_PARAM_TEAMS = "com.perrinn.client.fragments.TeamFragment.FRAGMENT_PARAM_TEAMS";
    private RecyclerView mTeamList;
    private ImageButton mTeamListAddButton;
    private ArrayList<Team> mTeams;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team,container,false);
        mTeamList = (RecyclerView) rootView.findViewById(R.id.team_list);
        mTeamListAddButton = (ImageButton) rootView.findViewById(R.id.team_list_add_button);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mTeamList.setLayoutManager(mLayoutManager);
        Bundle args = getArguments();
        if(args != null){
            mTeams = args.getParcelableArrayList(FRAGMENT_PARAM_TEAMS);
            mTeamList.setAdapter(new TeamAdapter(mTeams,getContext()));
        }


        mTeamListAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddNewTeam();
            }
        });
        return rootView;
    }

    public static TeamFragment newInstance(ArrayList<Team> teams){
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(FRAGMENT_PARAM_TEAMS,teams);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * This method adds a new team to the list and updates the list view.
     *
     * */
    private void addNewTeam(String team){
        //mDummyTeams.add(team);
        //mTeamList.swapAdapter(new TeamAdapter(mDummyTeams,getContext()),true);
    }

    /**
     * This method will prompt an alert dialog asking the user to give the name of the team
     * and it will add the new team to the list.
     *
     * */
    private void showAddNewTeam(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getResources().getString(R.string.team_add_dialog_title));
        View view = getLayoutInflater(null).inflate(R.layout.dialog_team_add,null);
        builder.setView(view);
        final TextInputEditText mDialogAddName = (TextInputEditText) view.findViewById(R.id.dialog_add_name);
        builder.setPositiveButton(getResources().getString(R.string.team_add_dialog_positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mDialogAddName.getText().toString().length() > 0){ // FIXME: improve controls
                    addNewTeam(mDialogAddName.getText().toString());
                }
            }
        }).setNegativeButton(getResources().getString(R.string.dialogs_cancel_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


}
