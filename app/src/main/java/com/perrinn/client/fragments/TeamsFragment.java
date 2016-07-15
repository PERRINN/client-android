package com.perrinn.client.fragments;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.perrinn.client.MainActivity;
import com.perrinn.client.R;
/**
 * A fragment containing a simple view.
 */
public class TeamsFragment extends Fragment {

    public TeamsFragment() {
    }
    /*
    * //////////////////////////////////////////////////
    * // Overrided methods
    * /////////////////////////////////////////////////
    */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    /*
     * //////////////////////////////////////////
     * inflate the fragment upon creating the view
     * //////////////////////////////////////////
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {

        final View rootView = inflater.inflate(R.layout.fragment_teams, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.listViewTeams);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                String value = (String)parent.getItemAtPosition(i);
                Log.d("val", value);

                //todo:get members list
                //todo: should lead to another unspecified view for now

                /*
                ChatFragment ChatFrag = new ChatFragment();
                FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                fragTransaction.replace(R.id.fragment_container,ChatFrag );
                fragTransaction.addToBackStack(null);
                fragTransaction.commit();
                */
            }

        });
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    /*
    * //////////////////////////////////////////////////
    * // Public methods
    * /////////////////////////////////////////////////
    */
    /**
     * This method creates a new instance of the fragment and return it to the caller.
     *
     * @return TeamsFragment the new fragment created.
     * */
    public static TeamsFragment newInstance(){
        TeamsFragment fragment = new TeamsFragment();

        return fragment;
    }

}