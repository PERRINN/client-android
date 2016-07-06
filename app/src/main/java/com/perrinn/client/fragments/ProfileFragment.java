package com.perrinn.client.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrinn.client.R;

/**
 * This fragment contains and manages the data and views needed for the user profile fragment.
 *
 * @author Alessandro
 * @since 06.07.2016
 */
public class ProfileFragment extends Fragment {
    private TextView mSettingsProfileFirstName;
    private TextView mSettingsProfileLastName;
    private ImageView mSettingsProfilePicture;
    private RecyclerView mSettingsProfileRegisteredDevicesList;
    private RecyclerView mSettingsProfileTrustedIdentifiersList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile,container,false);
        mSettingsProfileFirstName = (TextView) rootView.findViewById(R.id.settings_profile_firstname);
        mSettingsProfileLastName = (TextView) rootView.findViewById(R.id.settings_profile_lastname);
        mSettingsProfilePicture = (ImageView) rootView.findViewById(R.id.settings_profile_picture);
        mSettingsProfileRegisteredDevicesList = (RecyclerView) rootView.findViewById(R.id.settings_profile_registered_devices_list);
        mSettingsProfileTrustedIdentifiersList = (RecyclerView) rootView.findViewById(R.id.settings_profile_trusted_identifiers_list);

        return rootView;

    }
}
