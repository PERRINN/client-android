package com.perrinn.client.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrinn.client.R;
import com.squareup.picasso.Picasso;

/**
 * Created by alessand.silacci on 15.01.2017.
 */

public class MultiScreenChildFragment extends Fragment {

    private static final String FRAGMENT_PARAM_TEAMTITLE = "com.perrinn.client.fragments.MultiScreenChildFragment.FRAGMENT_PARAM_TEAMTITLE";
    private static final String FRAGMENT_PARAM_BACKGROUNDRES = "com.perrinn.client.fragments.MultiScreenChildFragment.FRAGMENT_PARAM_BACKGROUNDRES";
    private ImageView mChildBackgroundHolder;
    private TextView mChildTeamName;
    private View mChildLayoutContent;
    protected int mIncludedLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_multi_screen_child, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mChildBackgroundHolder = (ImageView) view.findViewById(R.id.child_background_holder);
        mChildTeamName = (TextView) view.findViewById(R.id.child_team_name);
        ViewStub stub = (ViewStub) view.findViewById(R.id.child_included_layout);
        stub.setLayoutResource(mIncludedLayout);
        mChildLayoutContent = stub.inflate();
        Bundle args = getArguments();
        if(args != null){
            Picasso.with(getContext()).load(args.getInt(FRAGMENT_PARAM_BACKGROUNDRES)).fit().into(mChildBackgroundHolder);
            mChildTeamName.setText(args.getString(FRAGMENT_PARAM_TEAMTITLE).toUpperCase());
        }
    }

    public void setIncludedFragmentLayout(int includedFragmentLayout){
        this.mIncludedLayout = includedFragmentLayout;
    }

    /**
     * This method creates new child fragments of a specific fragment class.
     *
     * @param teamTitle the team's title.
     * @param bgRes the background resource.
     * @param fragmentClass the child fragment's class.
     * @return a new child fragment that will be used as a child.
     * */
    public static <T extends Fragment> T newInstance(String teamTitle, int bgRes, Class<T> fragmentClass){
        T fragment = null;
        try {
            fragment = fragmentClass.newInstance();
            Bundle args = new Bundle();
            args.putString(FRAGMENT_PARAM_TEAMTITLE,teamTitle);
            args.putInt(FRAGMENT_PARAM_BACKGROUNDRES,bgRes);
            fragment.setArguments(args);
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }
}
