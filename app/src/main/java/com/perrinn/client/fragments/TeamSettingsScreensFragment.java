package com.perrinn.client.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perrinn.client.R;
import com.perrinn.client.adapters.MainPagerAdapter;
import com.perrinn.client.helpers.ToggledViewPager;

/**
 * Created by alessandrosilacci on 20/08/16.
 */
public class TeamSettingsScreensFragment extends Fragment {
    private static final String FRAGMENT_PARAM_TEAMINDEX = "com.perrinn.fragments.TeamSettingsScreensFragment.FRAGMENT_PARAM_TEAMINDEX";
    private ToggledViewPager mFragmentPagerTeams;
    private MainPagerAdapter mFragmentPagerTeamsAdapter;


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
        }
        addNewTeamSettingsFragment("FRAGMENT_TEAM_SETTINGS_MARKETING",true,selectedTeam == 0,"Marketing",R.drawable.team_members_background_placeholder);
        addNewTeamSettingsFragment("FRAGMENT_TEAM_SETTINGS_FRIENDS",false,selectedTeam == 1,"Friends",R.drawable.team_members_background2_placeholder);
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

    public static TeamSettingsScreensFragment newInstance(int teamIndex){
        TeamSettingsScreensFragment fragment = new TeamSettingsScreensFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_PARAM_TEAMINDEX,teamIndex);
        fragment.setArguments(args);
        return fragment;
    }
}
