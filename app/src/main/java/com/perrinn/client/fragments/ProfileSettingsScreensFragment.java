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
public class ProfileSettingsScreensFragment extends Fragment {

    private ToggledViewPager mFragmentPagerProfiles;
    private MainPagerAdapter mFragmentPagerProfilesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_settings_screens,container,false);

        return rootView;
    }
}
