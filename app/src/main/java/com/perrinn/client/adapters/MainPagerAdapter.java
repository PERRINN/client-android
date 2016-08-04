package com.perrinn.client.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This adapter is used to populate the viewpager that is used in the main activity.
 *
 * @since 04.06.2016
 * @author Alessandro
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter{
    private ArrayList<String> mTags = new ArrayList<>();
    private HashMap<String,Fragment> mFragments;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainPagerAdapter(FragmentManager fm, HashMap<String,Fragment> fragments){
        this(fm);
        if(fragments != null) {
            this.mFragments = fragments;
            this.mTags.addAll(mFragments.keySet()); // O(n) so big thing if a lot of fragments is given at start.
        }else
            mFragments = new HashMap<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragments.get(mTags.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    /**
     * This method is basically adding new fragments to the set of fragments in the viewpager.
     *
     * @param tag the tag that will be used for the fragment.
     * @param fragment the new fragment to add.
     * */
    public void addNewFragment(String tag, Fragment fragment){
        if(goToFragment(tag) == -1) {
            mFragments.put(tag, fragment);
            mTags.add(tag);
        }else goToFragment(tag);
    }

    public void removeFragment(String tag){
        if(goToFragment(tag) != -1) {
            mFragments.remove(tag);
            mTags.remove(tag);
        }
    }

    /**
     * This method gets the position of the fragment with the specified tag.
     *
     * @param tag the tag of the fragment.
     * @return int the position of the fragment or -1 if none found.
     * */
    public int goToFragment(String tag){
        return mFragments.containsKey(tag) ? mTags.indexOf(tag):-1;
    }
}
