package com.perrinn.client.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.perrinn.client.R;
import com.perrinn.client.adapters.SettingsProfileItemAdapter;
import com.perrinn.client.beans.SettingsProfileListItem;

import org.w3c.dom.Text;

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
    private TextView mSettingsProfileFirstName;
    private TextView mSettingsProfileLastName;
    private ImageView mSettingsProfilePicture;
    private ImageButton mSettingsProfileChangePictureButton;
    private RecyclerView mSettingsProfileRegisteredDevicesList;
    private RelativeLayout mSettingsProfileLocation;
    private TextView mSettingsProfileLocationState;
    private TextView mSettingsProfileResignTL;
    private TextView mSettingsProfileLeaveTeam;

    private boolean sharedLocation = false;

    private static final int REQUEST_PERMISSIONS = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile,container,false);
        mSettingsProfileTeamName = (TextView) rootView.findViewById(R.id.settings_profile_team_name);
        mSettingsProfileFirstName = (TextView) rootView.findViewById(R.id.settings_profile_firstname);
        mSettingsProfileLastName = (TextView) rootView.findViewById(R.id.settings_profile_lastname);
        mSettingsProfilePicture = (ImageView) rootView.findViewById(R.id.settings_profile_picture);
        mSettingsProfileChangePictureButton = (ImageButton) rootView.findViewById(R.id.settings_profile_change_picture_button);
        mSettingsProfileRegisteredDevicesList = (RecyclerView) rootView.findViewById(R.id.settings_profile_registered_devices_list);
        mSettingsProfileLocation = (RelativeLayout) rootView.findViewById(R.id.settings_profile_location);
        mSettingsProfileLocationState = (TextView) rootView.findViewById(R.id.settings_profile_location_state);
        mSettingsProfileResignTL = (TextView) rootView.findViewById(R.id.settings_profile_resign_tl);
        mSettingsProfileLeaveTeam = (TextView) rootView.findViewById(R.id.settings_profile_leave_team);

        RecyclerView.LayoutManager mRDLayoutManager = new LinearLayoutManager(getContext());

        mSettingsProfileRegisteredDevicesList.setLayoutManager(mRDLayoutManager);

        mSettingsProfileFirstName.setText("Nicolas");
        mSettingsProfileLastName.setText("Perrin");
        mSettingsProfileTeamName.setText(("Our little family").toUpperCase());

        mSettingsProfileChangePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermissions();
                showChangeProfilePictureDialog();
            }
        });

        // dummy data for the registered devices
        ArrayList<SettingsProfileListItem> mRegisteredDevices = new ArrayList<>();
        mRegisteredDevices.add(new SettingsProfileListItem("IPhone","Main",true));

        mSettingsProfileRegisteredDevicesList.setAdapter(new SettingsProfileItemAdapter(getContext(),mRegisteredDevices));
        mSettingsProfileLeaveTeam.setText(getResources().getString(R.string.settings_profile_leave)+" "+("Our little family").toUpperCase());

        mSettingsProfileLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSharedLocationState();
            }
        });
        return rootView;

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSIONS){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED);
            else getActivity().finish();
        }

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

    private void askPermissions(){
        // asking for permission
        if ((ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                ) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.CAMERA,android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        }
    }


    /**
     * This method will toggle the share of the location of the user. It will change the sharedLocation
     * attribute value.
     *
     * */
    private void changeSharedLocationState(){
        sharedLocation = !sharedLocation;
        if(sharedLocation){
            mSettingsProfileLocationState.setText(getResources().getString(R.string.settings_profile_location_shared));
            mSettingsProfileLocationState.setTextColor(ContextCompat.getColor(getContext(),R.color.colorSettingsProfileShared));
        }else{
            mSettingsProfileLocationState.setText(getResources().getString(R.string.settings_profile_location_nshared));
            mSettingsProfileLocationState.setTextColor(ContextCompat.getColor(getContext(),R.color.colorSettingsProfileNShared));
        }
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
