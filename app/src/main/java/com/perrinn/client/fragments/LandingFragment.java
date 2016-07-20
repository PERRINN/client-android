package com.perrinn.client.fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perrinn.client.R;
/**
 * A fragment containing a view created, the home view.
 */
public class LandingFragment extends Fragment {
    private LandingFragmentInteractionListener mListener;
    /*
    * //////////////////////////////////////////////////
    * //constructor
    * /////////////////////////////////////////////////
    */
    public LandingFragment() {
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
* //////////////////////////////////////////////////
* // launches once view is created
* /////////////////////////////////////////////////
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_landing, container, false);
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
     * @return LandingFragment the new fragment created.
     * */
    public static LandingFragment newInstance(){
        LandingFragment fragment = new LandingFragment();

        return fragment;
    }

    public interface LandingFragmentInteractionListener{
        void onTextInteraction();
    }

}