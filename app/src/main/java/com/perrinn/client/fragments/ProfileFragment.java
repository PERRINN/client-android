package com.perrinn.client.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrinn.client.R;
import com.perrinn.client.adapters.SettingsProfileItemAdapter;
import com.perrinn.client.beans.SettingsProfileListItem;

import java.util.ArrayList;

/**
 * This fragment contains and manages the data and views needed for the user profile fragment.
 *
 * @author Alessandro
 * @since 06.07.2016
 */
public class ProfileFragment extends Fragment {
    private static final int REQUEST_CAMERA = 0;
    private static final int REQUEST_GALLERY = 1;

    private TextView mSettingsProfileTeamName;
    private TextView mSettingsProfileTeamDesc;
    private TextView mSettingsProfileFirstName;
    private TextView mSettingsProfileLastName;
    private ImageView mSettingsProfilePicture;
    private ImageButton mSettingsProfileChangePictureButton;
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
        mSettingsProfileTeamName = (TextView) rootView.findViewById(R.id.settings_profile_team_name);
        mSettingsProfileTeamDesc = (TextView) rootView.findViewById(R.id.settings_profile_team_desc);
        mSettingsProfileFirstName = (TextView) rootView.findViewById(R.id.settings_profile_firstname);
        mSettingsProfileLastName = (TextView) rootView.findViewById(R.id.settings_profile_lastname);
        mSettingsProfilePicture = (ImageView) rootView.findViewById(R.id.settings_profile_picture);
        mSettingsProfileChangePictureButton = (ImageButton) rootView.findViewById(R.id.settings_profile_change_picture_button);
        mSettingsProfileRegisteredDevicesList = (RecyclerView) rootView.findViewById(R.id.settings_profile_registered_devices_list);
        mSettingsProfileTrustedIdentifiersList = (RecyclerView) rootView.findViewById(R.id.settings_profile_trusted_identifiers_list);
        RecyclerView.LayoutManager mRDLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager mTILayoutManager = new LinearLayoutManager(getContext());

        mSettingsProfileRegisteredDevicesList.setLayoutManager(mRDLayoutManager);
        mSettingsProfileTrustedIdentifiersList.setLayoutManager(mTILayoutManager);

        mSettingsProfileFirstName.setText("Nicolas");
        mSettingsProfileLastName.setText("Perrin");
        mSettingsProfileTeamName.setText(("Our little family").toUpperCase());
        mSettingsProfileTeamDesc.setText("Getting ready for our next holidays");

        mSettingsProfileChangePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeProfilePictureDialog();
            }
        });

        // dummy data for the registered devices
        ArrayList<SettingsProfileListItem> mRegisteredDevices = new ArrayList<>();
        mRegisteredDevices.add(new SettingsProfileListItem("Nicolas phone","Main",true));
        mRegisteredDevices.add(new SettingsProfileListItem("Nicolas PC",null,false));
        mRegisteredDevices.add(new SettingsProfileListItem("Home Tablet",null,false));

        // dummy data for the trusted identifiers
        ArrayList<SettingsProfileListItem> mTrustedIdentifiers = new ArrayList<>();
        mTrustedIdentifiers.add(new SettingsProfileListItem("Anne","Mutual",false));
        mTrustedIdentifiers.add(new SettingsProfileListItem("Philip","Mutual",false));

        mSettingsProfileRegisteredDevicesList.setAdapter(new SettingsProfileItemAdapter(getContext(),mRegisteredDevices));
        mSettingsProfileTrustedIdentifiersList.setAdapter(new SettingsProfileItemAdapter(getContext(),mTrustedIdentifiers));

        return rootView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CAMERA && resultCode == getActivity().RESULT_OK){
            // TODO: implement success picture taking code
        }else if(requestCode == REQUEST_GALLERY && resultCode == getActivity().RESULT_OK){
            // TODO: implement success picture choosing code
        }
    }

    /**
     * This method will create a new settings profile fragment and bundle the additional parameters
     * needed by the fragment.
     *
     * */
    public static ProfileFragment newInstance(){
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    /**
     * This method will prompt the user a Dialog where he can choose the method to use for changing
     * his profile picture.
     *
     * */
    private void showChangeProfilePictureDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setItems(R.array.settings_profile_change_picture_choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changePicture(which == 0);
            }
        });
        builder.create().show();
    }

    /**
     * This method will start the activities needed to get the new profile picture of the user
     * and wait for a result.
     *
     * @param useCamera true to start the camera activity and false to start the gallery.
     * */
    private void changePicture(boolean useCamera){
        Intent intent = null;
        if(useCamera){
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                startActivityForResult(intent,REQUEST_CAMERA);
            }
        }else{
            intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select a picture"),REQUEST_GALLERY);
        }
    }
}
