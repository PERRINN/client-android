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
import com.perrinn.client.beans.Event;
import com.perrinn.client.beans.Team;
import com.perrinn.client.helpers.ToggledViewPager;
import com.perrinn.client.listeners.MultiScreensListener;
import com.perrinn.client.listeners.OnEventDispatchedListener;

import java.util.ArrayList;

/**
 * Created by alessand.silacci on 15.01.2017.
 */

public class MultiScreenFragment<T extends MultiScreenChildFragment>  extends Fragment implements
OnEventDispatchedListener{
    private static final String FRAGMENT_PARAM_TEAMINDEX = "com.perrinn.fragments.MultiScreenFragment.FRAGMENT_PARAM_TEAMINDEX";
    private static final String FRAGMENT_PARAM_TEAMS = "com.perrinn.fragments.MultiScreenFragment.FRAGMENT_PARAM_TEAMS";
    private Class<T> childType;

    protected ToggledViewPager mFragmentViewPager;
    protected MainPagerAdapter mMainAdapter;

    protected ArrayList<Team> mTeams;
    protected OnEventDispatchedListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_multi_screen,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentViewPager = (ToggledViewPager) view.findViewById(R.id.fragment_pager);
        mMainAdapter = new MainPagerAdapter(getActivity().getSupportFragmentManager(),null);
        Bundle args = getArguments();
        int selectedTeam = 0;
        if(args != null){
            selectedTeam = args.getInt(FRAGMENT_PARAM_TEAMINDEX);
            mTeams = args.getParcelableArrayList(FRAGMENT_PARAM_TEAMS);
            for(Team team:mTeams){
                String tName = team.getName();
                addNewChildFragment("FRAGMENT_TEAM_SETTINGS_"+tName.toUpperCase(),true,team.isSelected(),tName,team.getBgres());
            }
        }
        addNewChildFragment("FRAGMENT_TEAM_SETTINGS_FRIENDS",false,selectedTeam == 1,"Friends",R.drawable.team_members_background2_placeholder);
        mFragmentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mListener.onEventDispatched(new Event(Event.EVENT_PAGE_CHANGED,position,null));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * This method adds a new child fragment defined by the class T to the viewpager.
     *
     * @param tag the tag of the new fragment.
     * @param rebuild if the adapter needs to be reset.
     * @param transit transition needed to the freshly added fragment.
     * */
    private void addNewChildFragment(String tag, boolean rebuild, boolean transit, String teamTitle, int backgroundRes){
        mMainAdapter.addNewFragment(tag, T.<T>newInstance(teamTitle,backgroundRes,childType));
        mMainAdapter.notifyDataSetChanged();


        if(rebuild){
            mFragmentViewPager.setAdapter(mMainAdapter);
        }

        int screenPosition = mMainAdapter.goToFragment(tag);
        //mIndicators.add(new DockIndicator(transit));

        if(transit) {
            mFragmentViewPager.setCurrentItem(screenPosition);
            mListener.onEventDispatched(new Event(Event.EVENT_PAGE_COUNT_CHANGED,mMainAdapter.getCount(),null));
            mListener.onEventDispatched(new Event(Event.EVENT_PAGE_CHANGED,screenPosition,null));
        }else {
            //mListener.onPageChange(oldPagePosition);
            //mListener.onPageCountChanged(mFragmentPagerTeamsAdapter.getCount());
            mListener.onEventDispatched(new Event(Event.EVENT_PAGE_COUNT_CHANGED,mMainAdapter.getCount(),null));
        }
    }

    /**
     * This method creates a new multi-screened fragment containing a certain fragment as child.
     *
     * @param teamIndex the actual selected team index.
     * @param teams the array of all teams.
     * @param childType the child class of the fragment contained as child.
     * @return a multiscreen fragment that can contain the mentioned child class fragments.
     * */
    public static <E extends MultiScreenChildFragment> MultiScreenFragment<E> newInstance(
            int teamIndex, ArrayList<Team> teams, Class<E> childType){
        MultiScreenFragment<E> fragment = new MultiScreenFragment<>();
        fragment.childType = childType;
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_PARAM_TEAMINDEX,teamIndex);
        args.putParcelableArrayList(FRAGMENT_PARAM_TEAMS,teams);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnEventDispatchedListener){
            mListener = (OnEventDispatchedListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    +" must implement the OnEventDispatchedListener.");
        }
    }

    @Override
    public void onEventDispatched(Event event) {
        mListener.onEventDispatched(event);
    }
}
