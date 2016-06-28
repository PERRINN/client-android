package com.perrinn.client.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.perrinn.client.R;
import android.app.Fragment;
/**
 * A fragment containing a simple view.
 */
public class LoadingFragment extends android.app.Fragment {
    public LoadingFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_loading, container, false);

        return rootView;
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
    public static LoadingFragment newInstance(){
        LoadingFragment fragment = new LoadingFragment();

        return fragment;
    }


}