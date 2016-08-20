package com.perrinn.client.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrinn.client.R;
import com.perrinn.client.adapters.SettingsTeamProjectsAdapter;
import com.perrinn.client.loaders.AsyncBitmapLoader;

import java.util.ArrayList;

/**
 * Created by alessandrosilacci on 27/07/16.
 */
public class TeamSettingsFragment extends Fragment{
    private static final String FRAGMENT_PARAM_TEAMTITLE = "com.perrinn.client.fragments.TeamSettingsFragment.FRAGMENT_PARAM_TEAMTITLE";
    private static final String FRAGMENT_PARAM_BACKGROUNDRES = "com.perrinn.client.fragments.TeamSettingsFragment.FRAGMENT_PARAM_BACKGROUNDRES";

    private TextView mSettingsTeamTeamName;
    private TextView mSettingsTeamEditName;
    private TextView mSettingsTeamAddMember;
    private ImageView mSettingsTeamPicture;
    private ImageButton mSettingsTeamChangePictureButton;
    private TextView mSettingsTeamTeamAddress;
    private TextView mSettingsTeamOrgName;
    private TextView mSettingsTeamHintShare;
    private RecyclerView mSettingsTeamProjectsList;
    private TextView mSettingsTeamAddProject;
    private ImageView mSettingsTeamBackgroundHolder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_settings_team,container,false);
        mSettingsTeamTeamName = (TextView) rootView.findViewById(R.id.settings_team_team_name);
        mSettingsTeamEditName = (TextView) rootView.findViewById(R.id.settings_team_edit_name);
        mSettingsTeamAddMember = (TextView) rootView.findViewById(R.id.settings_team_add_member);
        mSettingsTeamPicture = (ImageView) rootView.findViewById(R.id.settings_team_picture);
        mSettingsTeamChangePictureButton = (ImageButton) rootView.findViewById(R.id.settings_team_change_picture_button);
        mSettingsTeamTeamAddress = (TextView) rootView.findViewById(R.id.settings_team_team_address);
        mSettingsTeamOrgName = (TextView) rootView.findViewById(R.id.settings_team_org_name);
        mSettingsTeamHintShare = (TextView) rootView.findViewById(R.id.settings_team_hint_share);
        mSettingsTeamProjectsList = (RecyclerView) rootView.findViewById(R.id.settings_team_projects_list);
        mSettingsTeamAddProject = (TextView) rootView.findViewById(R.id.settings_team_add_project);
        mSettingsTeamBackgroundHolder = (ImageView) rootView.findViewById(R.id.settings_team_background_holder);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        Bundle args = getArguments();
        if(args != null){
            mSettingsTeamTeamName.setText((args.getString(FRAGMENT_PARAM_TEAMTITLE)).toUpperCase());
            new AsyncBitmapLoader(getContext(),mSettingsTeamBackgroundHolder).execute(args.getInt(FRAGMENT_PARAM_BACKGROUNDRES));
            new AsyncBitmapLoader(getContext(),mSettingsTeamPicture).execute(args.getInt(FRAGMENT_PARAM_BACKGROUNDRES));
        }
        mSettingsTeamTeamAddress.setText(("hj36 9vd").toUpperCase());
        mSettingsTeamOrgName.setText("Family and Friends");
        mSettingsTeamHintShare.setText(getResources().getString(R.string.settings_team_hint_share)+" "+"Family and friends"+" team.");
        mSettingsTeamProjectsList.setLayoutManager(mLayoutManager);

        // dummy data set
        ArrayList<String> projects = new ArrayList<>();
        projects.add("developing local school");
        projects.add("skill map");
        projects.add("perrinn f1");

        mSettingsTeamProjectsList.setAdapter(new SettingsTeamProjectsAdapter(projects));

        return rootView;
    }

    public static TeamSettingsFragment newInstance(String teamTitle, int backgroundRes){
        TeamSettingsFragment fragment = new TeamSettingsFragment();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_PARAM_TEAMTITLE,teamTitle);
        args.putInt(FRAGMENT_PARAM_BACKGROUNDRES,backgroundRes);
        fragment.setArguments(args);
        return fragment;
    }
}
