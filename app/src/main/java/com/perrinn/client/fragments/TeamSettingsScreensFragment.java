package com.perrinn.client.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrinn.client.R;
import com.perrinn.client.adapters.MainPagerAdapter;
import com.perrinn.client.beans.Team;
import com.perrinn.client.helpers.ToggledViewPager;
import com.perrinn.client.listeners.MultiScreensListener;

import java.util.ArrayList;

/**
 * Created by alessandrosilacci on 20/08/16.
 */
public class TeamSettingsScreensFragment extends Fragment {
    private static final String FRAGMENT_PARAM_TEAMINDEX = "com.perrinn.fragments.TeamSettingsScreensFragment.FRAGMENT_PARAM_TEAMINDEX";
    private static final String FRAGMENT_PARAM_TEAMS = "com.perrinn.fragments.TeamSettingsScreensFragment.FRAGMENT_PARAM_TEAMS";
    private ToggledViewPager mFragmentPagerTeams;
    private MainPagerAdapter mFragmentPagerTeamsAdapter;
    private MultiScreensListener mListener;
    private ArrayList<Team> mTeams;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_settings_screens,container,false);
        mFragmentPagerTeams = (ToggledViewPager) rootView.findViewById(R.id.fragment_pager_teams);
        mFragmentPagerTeamsAdapter = new MainPagerAdapter(getActivity().getSupportFragmentManager(),null);
        Bundle args = getArguments();
        int selectedTeam = 0;
        if(args != null){
            selectedTeam = args.getInt(FRAGMENT_PARAM_TEAMINDEX);
            mTeams = args.getParcelableArrayList(FRAGMENT_PARAM_TEAMS);
            for(Team team:mTeams){
                String tName = team.getName();
                addNewTeamSettingsFragment("FRAGMENT_TEAM_SETTINGS_"+tName.toUpperCase(),true,team.isSelected(),tName,team.getBgres());
            }
        }
        addNewTeamSettingsFragment("FRAGMENT_TEAM_SETTINGS_FRIENDS",false,selectedTeam == 1,"Friends",R.drawable.team_members_background2_placeholder);
        mFragmentPagerTeams.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mListener.onPageChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * This method adds a new TeamSettingsFragment to the viewpager.
     *
     * @param tag the tag of the new fragment.
     * @param rebuild if the adapter needs to be reset.
     * @param transit transition needed to the freshly added fragment.
     * */
    private void addNewTeamSettingsFragment(String tag, boolean rebuild, boolean transit, String teamTitle, int backgroundRes){
        mFragmentPagerTeamsAdapter.addNewFragment(tag, TeamSettingsFragment.newInstance(teamTitle,backgroundRes));
        mFragmentPagerTeamsAdapter.notifyDataSetChanged();


        if(rebuild){
            mFragmentPagerTeams.setAdapter(mFragmentPagerTeamsAdapter);
        }

        int screenPosition = mFragmentPagerTeamsAdapter.goToFragment(tag);
        //mIndicators.add(new DockIndicator(transit));

        if(transit) {
            mFragmentPagerTeams.setCurrentItem(screenPosition);
            //mListener.onPageCountChanged(mFragmentPagerTeamsAdapter.getCount());
            //mListener.onPageChange(screenPosition);
        }else {
            //mListener.onPageChange(oldPagePosition);
            //mListener.onPageCountChanged(mFragmentPagerTeamsAdapter.getCount());
        }
    }

    public static TeamSettingsScreensFragment newInstance(int teamIndex, ArrayList<Team> teams){
        TeamSettingsScreensFragment fragment = new TeamSettingsScreensFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_PARAM_TEAMINDEX,teamIndex);
        args.putParcelableArrayList(FRAGMENT_PARAM_TEAMS,teams);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MultiScreensListener){
            mListener = (MultiScreensListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    +" must implement the MultiScreensListener.");
        }
    }
}
