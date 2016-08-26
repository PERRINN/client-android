package com.perrinn.client.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrinn.client.R;
import com.perrinn.client.beans.Member;
import com.perrinn.client.beans.Team;
import com.squareup.picasso.Picasso;

/**
 * Created by alessandrosilacci on 25/08/16.
 */
public class TeamMateProfileFragment extends Fragment {
    private static final String FRAGMENT_PARAM_MEMBER = "com.perrinn.client.fragments.TeamMateProfileFragment.FRAGMENT_PARAM_MEMBER";
    private static final String FRAGMENT_PARAM_TEAM = "com.perrinn.client.fragments.TeamMateProfileFragment.FRAGMENT_PARAM_TEAM";
    private ImageView mTeamMateProfileBackgroundHolder;
    private TextView mTeamMateProfileTeamName;
    private TextView mTeamMateProfileFirstName;
    private TextView mTeamMateProfileLastName;
    private ImageView mTeamMateProfilePicture;
    private TextView mTeamMateProfileLocalTime;
    private TextView mTeamMateProfileDifferTime;
    private TextView mTeamMateProfilePromote;
    private TextView mTeamMateProfileRemove;
    private Button mTeamMateProfilePchatButton;

    private Member mMember;
    private Team mTeam;
    private OnPrivateChatButtonInteractionListener mListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_mate_profile,container,false);
        mTeamMateProfileBackgroundHolder = (ImageView) rootView.findViewById(R.id.team_mate_profile_background_holder);
        mTeamMateProfileTeamName = (TextView) rootView.findViewById(R.id.team_mate_profile_team_name);
        mTeamMateProfileFirstName = (TextView) rootView.findViewById(R.id.team_mate_profile_firstname);
        mTeamMateProfileLastName = (TextView) rootView.findViewById(R.id.team_mate_profile_lastname);
        mTeamMateProfilePicture = (ImageView) rootView.findViewById(R.id.team_mate_profile_picture);
        mTeamMateProfileLocalTime = (TextView) rootView.findViewById(R.id.team_mate_profile_local_time);
        mTeamMateProfileDifferTime = (TextView) rootView.findViewById(R.id.team_mate_profile_differ_time);
        mTeamMateProfilePromote = (TextView) rootView.findViewById(R.id.team_mate_profile_promote);
        mTeamMateProfileRemove = (TextView) rootView.findViewById(R.id.team_mate_profile_remove);
        mTeamMateProfilePchatButton = (Button) rootView.findViewById(R.id.team_mate_profile_pchat_button);

        Bundle args = getArguments();
        if(args != null){
            mMember = args.getParcelable(FRAGMENT_PARAM_MEMBER);
            mTeam = args.getParcelable(FRAGMENT_PARAM_TEAM);
            mTeamMateProfileFirstName.setText(mMember.getFirstName());
            mTeamMateProfileLastName.setText(mMember.getLastName());
            mTeamMateProfileLocalTime.setText("14:12 "+getResources().getString(R.string.team_mate_profile_local_time));
            mTeamMateProfileDifferTime.setText("-3 "+getResources().getString(R.string.team_mate_profile_time_hours));
            mTeamMateProfileTeamName.setText(mTeam.getName().toUpperCase());
            mTeamMateProfileRemove.setText("Remove from "+mTeam.getName().toUpperCase());
            Picasso.with(getContext()).load(mTeam.getBgres()).fit().into(mTeamMateProfileBackgroundHolder);
            Picasso.with(getContext()).load(mMember.getProfilePicture()).fit().into(mTeamMateProfilePicture);
        }

        mTeamMateProfilePchatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPrivateChatInteraction(mMember);
            }
        });


        return rootView;
    }

    public static TeamMateProfileFragment newInstance(Member member, Team team){
        TeamMateProfileFragment fragment = new TeamMateProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(FRAGMENT_PARAM_MEMBER,member);
        args.putParcelable(FRAGMENT_PARAM_TEAM,team);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnPrivateChatButtonInteractionListener){
            mListener = (OnPrivateChatButtonInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    +" must implement the OnPrivateChatButtonInteractionListener.");
        }
    }

    public interface OnPrivateChatButtonInteractionListener{
        void onPrivateChatInteraction(Member member);
    }
}
