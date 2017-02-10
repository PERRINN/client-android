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
 * Created by alessand.silacci on 13.01.2017.
 */

public class WalletTransacHistScreensFragment extends Fragment {
    private static final String FRAGMENT_PARAM_TEAMINDEX = "com.perrinn.client.fragments.WalletTransacHistScreensFragment.FRAGMENT_PARAM_TEAMINDEX";
    private static final String FRAGMENT_PARAM_TEAMS = "com.perrinn.client.fragments.WalletTransacHistScreensFragment.FRAGMENT_PARAM_TEAMS";
    private ToggledViewPager mFragmentPagerWalletTransac;
    private MainPagerAdapter mFragmentPagerWalletTransacAdapter;
    private MultiScreensListener mListener;
    private ArrayList<Team> mTeams;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet_transac_hist_screens, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mFragmentPagerWalletTransac = (ToggledViewPager) view.findViewById(R.id.fragment_pager_wallet_transac);
        mFragmentPagerWalletTransacAdapter = new MainPagerAdapter(getActivity().getSupportFragmentManager(),null);
        Bundle args = getArguments();
        if(args != null){
            mTeams = args.getParcelableArrayList(FRAGMENT_PARAM_TEAMS);
            for(Team team : mTeams){
                String tName = team.getName();
                addNewWalletTransacFragment("FRAGMENT_WALLET_TRANSAC_"+tName,true,team.isSelected(),tName,team.getBgres());
            }
        }
        mFragmentPagerWalletTransac.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
    }

    /**
     * This method adds a new WalletFragment to the viewpager.
     *
     * @param tag the tag of the new fragment.
     * @param rebuild if the adapter needs to be reset.
     * @param transit transition needed to the freshly added fragment.
     * */
    private void addNewWalletTransacFragment(String tag, boolean rebuild, boolean transit, String teamTitle, int backgroundRes){
        mFragmentPagerWalletTransacAdapter.addNewFragment(tag, WalletTransacHistFragment.newInstance(teamTitle,backgroundRes));
        mFragmentPagerWalletTransacAdapter.notifyDataSetChanged();


        if(rebuild){
            mFragmentPagerWalletTransac.setAdapter(mFragmentPagerWalletTransacAdapter);
        }

        int screenPosition = mFragmentPagerWalletTransacAdapter.goToFragment(tag);
        //mIndicators.add(new DockIndicator(transit));

        if(transit) {
            mFragmentPagerWalletTransac.setCurrentItem(screenPosition);
            //mListener.onPageCountChanged(mFragmentPagerTeamsAdapter.getCount());
            //mListener.onPageChange(screenPosition);
        }else {
            //mListener.onPageChange(oldPagePosition);
            //mListener.onPageCountChanged(mFragmentPagerTeamsAdapter.getCount());
        }
    }

    public static WalletTransacHistScreensFragment newInstance(int teamIndex, ArrayList<Team> teams){
        WalletTransacHistScreensFragment fragment = new WalletTransacHistScreensFragment();
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
