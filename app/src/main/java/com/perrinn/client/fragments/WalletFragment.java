package com.perrinn.client.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrinn.client.R;
import com.perrinn.client.adapters.WalletMenuItemAdapter;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

/**
 * Created by alessand.silacci on 27.12.2016.
 */

public class WalletFragment extends Fragment implements WalletMenuItemAdapter.OnWalletMenuItemClickListener{
    private static final String FRAGMENT_PARAM_TEAMTITLE = "com.perrinn.client.fragments.WalletFragment.FRAGMENT_PARAM_TEAMTITLE";
    private static final String FRAGMENT_PARAM_BACKGROUNDRES = "com.perrinn.client.fragments.WalletFragment.FRAGMENT_PARAM_BACKGROUNDRES";

    private ImageView mWalletBackgroundHolder;
    private TextView mWalletTeamName;
    private TextView mWalletNewTransaction;
    private ImageView mWalletIcon;
    private TextView mWalletAmount;
    private TextView mWalletShares;
    private RecyclerView mWalletMenu;
    private OnWalletFragmentInteractionListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWalletTeamName = (TextView) view.findViewById(R.id.wallet_team_name);
        mWalletBackgroundHolder = (ImageView) view.findViewById(R.id.wallet_background_holder);
        mWalletIcon = (ImageView) view.findViewById(R.id.wallet_icon);
        mWalletShares = (TextView) view.findViewById(R.id.wallet_shares);
        mWalletMenu = (RecyclerView) view.findViewById(R.id.wallet_menu);
        LinearLayoutManager lrm = new LinearLayoutManager(getContext());
        mWalletMenu.setLayoutManager(lrm);

        Bundle args = getArguments();
        if(args != null){
            Picasso.with(getContext()).load(args.getInt(FRAGMENT_PARAM_BACKGROUNDRES)).fit().into(mWalletBackgroundHolder);
            mWalletTeamName.setText(args.getString(FRAGMENT_PARAM_TEAMTITLE).toUpperCase());
        }
        String[] mMenuElements = getResources().getStringArray(R.array.wallet_options);
        WalletMenuItemAdapter mAdapter = new WalletMenuItemAdapter(getContext(),mMenuElements,this);
        mWalletShares.setText("" + NumberFormat.getInstance().format(1450987.00) + " shares");
        mWalletMenu.setAdapter(mAdapter);
    }

    public static WalletFragment newInstance(String teamName, int backgroundRes){
        WalletFragment fragment = new WalletFragment();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_PARAM_TEAMTITLE,teamName);
        args.putInt(FRAGMENT_PARAM_BACKGROUNDRES,backgroundRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof WalletFragment.OnWalletFragmentInteractionListener){
            mListener = (WalletFragment.OnWalletFragmentInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    +" must implement the OnWalletFragmentInteractionListener.");
        }
    }

    @Override
    public void onMenuItemSelection(int item) {
        switch(item){
            case 0: // TODO : change this with constants.
                break;
            case 1:
                break;
            case 2:
                mListener.onWalletTransactionHistoryClick();
                break;
            default:
                break;
        }
    }

    public interface OnWalletFragmentInteractionListener {
        void onWalletTransactionHistoryClick();
    }
}
