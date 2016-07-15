package com.perrinn.client.fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.perrinn.client.R;
/**
 * A fragment that displays the projects page.
 */
public class ProjectFragment extends Fragment {

    public ProjectFragment() {
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
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_projects, container, false);

       ListView lv = (ListView) rootView.findViewById(R.id.listViewProjects);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                String value = (String)parent.getItemAtPosition(i);
                Log.d("val", value);

                //todo:don't know where the click should lead, for now it leads to chat but
                //todo:will be changed once i have more info

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
     * @return LoadingFragment the new fragment created.
     * */
    public static ProjectFragment newInstance(){
        ProjectFragment fragment = new ProjectFragment();

        return fragment;
    }

}