package com.perrinn.client.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.perrinn.client.MainActivity;
import com.perrinn.client.R;
import com.perrinn.client.beans.Team;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Random;

public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final String FRAGMENT_PARAM_TEAM = "com.perrinn.client.fragments.MapsFragment.FRAGMENT_PARAM_TEAM";
    private GoogleMap mMap;
    private MapView mGMap;
    private TextView mMapsTeamName;
    private ImageView mMapsBackgroundHolder;
    private GoogleApiClient mGoogleMapApiClient;
    private Location mCrtLocation;
    private boolean mGrantedPermissions = false;
    private Team mTeam;

    private Random mColorRandomizer = new Random();

    private static final int POSITION_REFRESH_INTERVAL = 60000;
    private static final int POSITION_FAST_REFRESH_INTERVAL = 30000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);
        mMapsTeamName = (TextView) rootView.findViewById(R.id.maps_team_name);
        mMapsBackgroundHolder = (ImageView) rootView.findViewById(R.id.maps_background_holder);
        mGMap = (MapView) rootView.findViewById(R.id.gmap);
        mGMap.onCreate(savedInstanceState);
        mGMap.onResume();
        mGMap.getMapAsync(this);
        Bundle args = getArguments();
        if(args != null){
            mTeam = args.getParcelable(FRAGMENT_PARAM_TEAM);
            mMapsTeamName.setText(mTeam.getName());
            Picasso.with(getContext()).load(mTeam.getBgres()).fit().into(mMapsBackgroundHolder);
        }
        askPermissions();
        return rootView;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        final HashMap<String,LatLng> dummyMembers = new HashMap<>();
        dummyMembers.put("Aiko",new LatLng(51.507351,-0.127758)); // aiko in london
        dummyMembers.put("Vicky",new LatLng(46.204391,6.143158)); // vicky in geneva
        dummyMembers.put("Alan",new LatLng(41.385064,2.173403)); // alan in barcelona
        dummyMembers.put("James", new LatLng(40.416775,-3.703790)); // james in madrid
        dummyMembers.put("Mathilde",new LatLng(52.520007,13.404954)); // mathilde in berlin
        dummyMembers.put("Daniel",new LatLng(47.497912,19.040235)); // daniel in budapest
        dummyMembers.put("Andrea",new LatLng(48.208174,16.373819)); // andrea in vienna
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                placeTeamMembers(dummyMembers);
            }
        });

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mCrtLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleMapApiClient);

        if(mCrtLocation == null){
            showNoLocationFoundDialog();
        }
        if(mGrantedPermissions)
                createLocationRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mCrtLocation = location;
    }

    @Override
    public void onStart() {
        if(mGoogleMapApiClient == null)
            buildGoogleMapApiClient();
        mGoogleMapApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleMapApiClient.disconnect();
        super.onStop();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MainActivity.REQUEST_PERMISSIONS_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mGrantedPermissions = true;
                if (mGoogleMapApiClient == null) {
                    buildGoogleMapApiClient();
                }
                mGoogleMapApiClient.connect();
                mMap.setMyLocationEnabled(true);
            }
        }

    }

    public static MapsFragment newInstance(Team team){
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putParcelable(FRAGMENT_PARAM_TEAM,team);
        fragment.setArguments(args);
        return fragment;
    }

    private void askPermissions(){
        // asking for permission
        if ((ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MainActivity.REQUEST_PERMISSIONS_LOCATION);
            }
        }
    }

    private void buildGoogleMapApiClient(){
        mGoogleMapApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void showNoLocationFoundDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
        alertBuilder.setTitle(getResources().getString(R.string.gmap_no_location_dialog_title))
                .setMessage(getResources().getString(R.string.gmap_no_location_dialog_message))
                .setPositiveButton(getResources().getString(R.string.dialogs_ok_message), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertBuilder.create().show();
    }

    private void placeTeamMembers(HashMap<String,LatLng> members){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(String key:members.keySet()){
            LatLng memberLatLng = members.get(key);
            mMap.addMarker(new MarkerOptions().position(memberLatLng).title(key));
            builder.include(memberLatLng);
            //mMap.addPolyline(new PolylineOptions().add(crtLatLng).add(memberLatLng).geodesic(true));
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),100));
    }

    /**
     * This method registers a new location request with the parameters defined in POSITION_REFRESH_INTERVAL
     * and POSITION_FAST_REFRESH_INTERVAL with a PRIORITY_BALANCED_POWER_ACCURACY priority as this
     * app is not meant to be the new station of drilling
     *
     * */
    private void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(POSITION_REFRESH_INTERVAL);
        mLocationRequest.setFastestInterval(POSITION_FAST_REFRESH_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleMapApiClient, mLocationRequest, this);
        } catch (SecurityException e) {
            System.err.println("An error occurred while trying to create the location update" +
                    "request, see the following stacktrace for more information:");

            e.printStackTrace();
        }
    }
}
