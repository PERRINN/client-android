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
 * A fragment containing a simple view.
 */
public class LoadingFragment extends Fragment {
    private LoadingFragmentInteractionListener mListener;

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
        TextView mTextEnter = (TextView) rootView.findViewById(R.id.loading_progress_text);
        mTextEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) mListener.onTextInteraction();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof LoadingFragmentInteractionListener){
            mListener = (LoadingFragmentInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    +" must implement the LoadingFragmentInteractionListener.");
        }
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

    public interface LoadingFragmentInteractionListener{
        void onTextInteraction();
    }

}