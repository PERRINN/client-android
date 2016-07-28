package com.perrinn.client.fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.perrinn.client.R;
/**
 * A fragment containing the login view.
 */
public class LoginFragment extends Fragment {
    private LoginFragmentInteractionListener mListener;

    private Button mLoginCreateTeamButton;
    private Button mLoginJoinTeamButton;
    private Button mLoginEnterButton;

    /*
    * //////////////////////////////////////////////////
    * //constructor
    * /////////////////////////////////////////////////
    */
    public LoginFragment() {
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
* // launches once view is created, on click listener for text
* /////////////////////////////////////////////////
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        mLoginCreateTeamButton = (Button) rootView.findViewById(R.id.login_create_team_button);
        mLoginJoinTeamButton = (Button) rootView.findViewById(R.id.login_join_team_button);
        mLoginEnterButton = (Button) rootView.findViewById(R.id.login_enter_button);

        mLoginCreateTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLoginCreateTeamButtonInteraction();
            }
        });
        mLoginJoinTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLoginJoinTeamButtonInteraction();
            }
        });
        mLoginEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLoginEnterButtonInteraction();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof LoginFragmentInteractionListener){
            mListener = (LoginFragmentInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    +" must implement the LoginFragmentInteractionListener.");
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
    public static LoginFragment newInstance(){
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    public interface LoginFragmentInteractionListener {
        void onLoginCreateTeamButtonInteraction();
        void onLoginJoinTeamButtonInteraction();
        void onLoginEnterButtonInteraction();
    }

}