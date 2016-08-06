package com.perrinn.client.fragments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.perrinn.client.fragments.ChatFragment;
import com.perrinn.client.R;
/**
 * A fragment containing a simple view that lists the projects the user is involved in.
 */
public class ProjectFragment extends Fragment {
    private ProjectFragmentInteractionListener mListener;
    /*
    * //////////////////////////////////////////////////
    * //constructor
    * /////////////////////////////////////////////////
    */
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
* //////////////////////////////////////////////////
* //launches once the view is created
* /////////////////////////////////////////////////
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return null;
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
     * @return ProjectFragment the new fragment created.
     * */
    public static ProjectFragment newInstance(){
        ProjectFragment fragment = new ProjectFragment();

        return fragment;
    }

    public interface ProjectFragmentInteractionListener{
        void onTextInteraction();
    }

}