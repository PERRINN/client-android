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
 * Created by alessandrosilacci on 25/08/16.
 */
public class ChatScreensFragment extends Fragment {
    private static final String FRAGMENT_PARAM_TEAMS = "com.perrinn.client.fragments.ChatScreensFragment.FRAGMENT_PARAM_TEAMS";
    private ToggledViewPager mFragmentPagerChat;
    private MainPagerAdapter mFragmentPagerChatAdapter;
    private ArrayList<Team>  mTeams;
    private MultiScreensListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat_screens,container,false);
        mFragmentPagerChat = (ToggledViewPager) rootView.findViewById(R.id.fragment_pager_chat);
        mFragmentPagerChatAdapter = new MainPagerAdapter(getActivity().getSupportFragmentManager(),null);
        Bundle args = getArguments();
        if(args != null){
            mTeams = args.getParcelableArrayList(FRAGMENT_PARAM_TEAMS);
            for(Team team : mTeams){
                String tName = team.getName();
                addNewChatFragment("FRAGMENT_CHAT_"+tName.toUpperCase(),true,team.isSelected(),team);
            }
        }
        mFragmentPagerChat.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    /**
     * This method adds a new ChatFragment to the viewpager.
     *
     * @param tag the tag of the new fragment.
     * @param rebuild if the adapter needs to be reset.
     * @param transit transition needed to the freshly added fragment.
     * */
    private void addNewChatFragment(String tag, boolean rebuild, boolean transit,Team team){
        mFragmentPagerChatAdapter.addNewFragment(tag, ChatFragment.newInstance(team));
        mFragmentPagerChatAdapter.notifyDataSetChanged();


        if(rebuild){
            mFragmentPagerChat.setAdapter(mFragmentPagerChatAdapter);
        }

        int screenPosition = mFragmentPagerChatAdapter.goToFragment(tag);
        //mIndicators.add(new DockIndicator(transit));

        if(transit) {
            mFragmentPagerChat.setCurrentItem(screenPosition);
            //mListener.onPageCountChanged(mFragmentPagerTeamsAdapter.getCount());
            //mListener.onPageChange(screenPosition);
        }else {
            //mListener.onPageChange(oldPagePosition);
            //mListener.onPageCountChanged(mFragmentPagerTeamsAdapter.getCount());
        }
    }

    public static ChatScreensFragment newInstance(ArrayList<Team> teams){
        ChatScreensFragment fragment = new ChatScreensFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(FRAGMENT_PARAM_TEAMS,teams);
        fragment.setArguments(args);
        return fragment;
    }

    public void setCurrentItem(int position){
        if(position >= 0 && position < mTeams.size()){
            mFragmentPagerChat.setCurrentItem(position);
        }
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
