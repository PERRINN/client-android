package com.perrinn.client.fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private TeamsFragmentInteractionListener mListener;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_teams, container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.listViewTeams);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //view IDE message, implement AdapterView and setOnItemClickListener

            @Override
            public void onClick(final AdapterView<?> parent,View v, int position, long arg1) {
                Integer taggedPosition = (Integer) v.getTag();
                ListView lv = (ListView) v.findViewById(R.id.listViewTeams);
                lv.performItemClick(
                        lv.getAdapter().getView(taggedPosition, null, null),
                        taggedPosition,
                        lv.getAdapter().getItemId(taggedPosition));

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
    public static TeamsFragment newInstance(){
        TeamsFragment fragment = new TeamsFragment();

        return fragment;
    }

    public interface TeamsFragmentInteractionListener{
        void onTeamClick(View view);
    }

}