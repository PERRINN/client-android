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
 * Created by alessand.silacci on 27.12.2016.
 */

public class WalletScreensFragment extends Fragment {
    private static final String FRAGMENT_PARAM_TEAMINDEX = "com.perrinn.client.fragments.WalletScreensFragment.FRAGMENT_PARAM_TEAMINDEX";
    private static final String FRAGMENT_PARAM_TEAMS = "com.perrinn.client.fragments.WalletScreensFragment.FRAGMENT_PARAM_TEAMS";
    private ToggledViewPager mFragmentPagerWallets;
    private MainPagerAdapter mFragmentPagerWalletsAdapter;
    private MultiScreensListener mListener;
    private ArrayList<Team> mTeams;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wallet_screens,container,false);
        mFragmentPagerWallets = (ToggledViewPager) rootView.findViewById(R.id.fragment_pager_wallet);
        mFragmentPagerWalletsAdapter = new MainPagerAdapter(getActivity().getSupportFragmentManager(),null);
        Bundle args = getArguments();
        int selectedIndex = 0;
        if(args != null){
            selectedIndex = args.getInt(FRAGMENT_PARAM_TEAMINDEX);
            mTeams = args.getParcelableArrayList(FRAGMENT_PARAM_TEAMS);
            for(Team team : mTeams){
                String tName = team.getName();
                addNewWalletFragment("FRAGMENT_WALLET_"+tName,true,team.isSelected(),tName,team.getBgres());
            }
        }
        mFragmentPagerWallets.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
     * This method adds a new WalletFragment to the viewpager.
     *
     * @param tag the tag of the new fragment.
     * @param rebuild if the adapter needs to be reset.
     * @param transit transition needed to the freshly added fragment.
     * */
    private void addNewWalletFragment(String tag, boolean rebuild, boolean transit, String teamTitle, int backgroundRes){
        mFragmentPagerWalletsAdapter.addNewFragment(tag, WalletFragment.newInstance(teamTitle,backgroundRes));
        mFragmentPagerWalletsAdapter.notifyDataSetChanged();


        if(rebuild){
            mFragmentPagerWallets.setAdapter(mFragmentPagerWalletsAdapter);
        }

        int screenPosition = mFragmentPagerWalletsAdapter.goToFragment(tag);
        //mIndicators.add(new DockIndicator(transit));

        if(transit) {
            mFragmentPagerWallets.setCurrentItem(screenPosition);
            //mListener.onPageCountChanged(mFragmentPagerTeamsAdapter.getCount());
            //mListener.onPageChange(screenPosition);
        }else {
            //mListener.onPageChange(oldPagePosition);
            //mListener.onPageCountChanged(mFragmentPagerTeamsAdapter.getCount());
        }
    }

    public static WalletScreensFragment newInstance(int teamIndex, ArrayList<Team> teams){
        WalletScreensFragment fragment = new WalletScreensFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENT_PARAM_TEAMINDEX,teamIndex);
        args.putParcelableArrayList(FRAGMENT_PARAM_TEAMS,teams);
        fragment.setArguments(args);
        return fragment;
    }

    public void setCurrentItem(int position){
        if(position >= 0 && position < mTeams.size()){
            mFragmentPagerWallets.setCurrentItem(position);
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

    public interface OnWalletScreensInteractionInteractionListener extends WalletFragment.OnWalletFragmentInteractionListener{

    }
}
