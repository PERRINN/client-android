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
import com.perrinn.client.adapters.GridViewAdapter;
import com.perrinn.client.adapters.MainPagerAdapter;
import com.perrinn.client.beans.DockIndicator;
import com.perrinn.client.helpers.ToggledViewPager;
import com.perrinn.client.listeners.OnRenderCompleteListener;

/**
 * Created by alessandrosilacci on 10/08/16.
 */
public class TeamScreensFragment extends Fragment {


    private ToggledViewPager mFragmentPagerMain;
    private MainPagerAdapter mFragmentPagerMainAdapter;
    private TeamScreensInteractionListener mListener;

    private int oldPagePosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_screens,container,false);
        mFragmentPagerMain = (ToggledViewPager) rootView.findViewById(R.id.fragment_pager_main);
        mFragmentPagerMainAdapter = new MainPagerAdapter(getActivity().getSupportFragmentManager(),null);


        mFragmentPagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        // dummy screens added
        addNewTeamMembersFragment("TEAM_MEMBERS_MARKETING",true,false,"Marketing","Busy preparing our next event.",R.drawable.team_members_background_placeholder);
        addNewTeamMembersFragment("TEAM_MEMBERS_FRIENDS",false,false,"Friends","Always fun stories to share.",R.drawable.team_members_background2_placeholder);
        return rootView;
    }

    /**
     * This method adds a new TeamMembersFragment to the viewpager.
     *
     * @param tag the tag of the new fragment.
     * @param rebuild if the adapter needs to be reset.
     * @param transit transition needed to the freshly added fragment.
     * */
    private void addNewTeamMembersFragment(String tag, boolean rebuild, boolean transit,String teamTitle, String teamDesc, int backgroundRes){
        mFragmentPagerMainAdapter.addNewFragment(tag, TeamMembersFragment.newInstance(teamTitle,teamDesc,backgroundRes));
        mFragmentPagerMainAdapter.notifyDataSetChanged();


        if(rebuild){
            mFragmentPagerMain.setAdapter(mFragmentPagerMainAdapter);
        }

        int screenPosition = mFragmentPagerMainAdapter.goToFragment(tag);
        //mIndicators.add(new DockIndicator(transit));

        if(transit) {
            mFragmentPagerMain.setCurrentItem(screenPosition);
            mListener.onPageCountChanged(mFragmentPagerMainAdapter.getCount());
            mListener.onPageChange(screenPosition);
        }else {
            mListener.onPageChange(oldPagePosition);
            mListener.onPageCountChanged(mFragmentPagerMainAdapter.getCount());
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListener.onComplete();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TeamScreensInteractionListener){
            mListener = (TeamScreensInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    +" must implement the TeamScreensInteractionListener.");
        }
    }

    public interface TeamScreensInteractionListener extends TeamMembersFragment.OnTeamMembersFragmentInteractionListener, OnRenderCompleteListener {
        void onPageChange(int position);
        void onPageCountChanged(int count);
    }

    public static TeamScreensFragment newInstance(){
        TeamScreensFragment fragment = new TeamScreensFragment();
        return fragment;
    }
}
