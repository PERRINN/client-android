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
import com.perrinn.client.beans.Event;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

/**
 * Created by alessand.silacci on 27.12.2016.
 */

public class WalletFragment extends MultiScreenChildFragment implements WalletMenuItemAdapter.OnWalletMenuItemClickListener{

    private TextView mWalletNewTransaction;
    private ImageView mWalletIcon;
    private TextView mWalletAmount;
    private TextView mWalletShares;
    private RecyclerView mWalletMenu;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setIncludedFragmentLayout(R.layout.fragment_wallet);
        super.onViewCreated(view, savedInstanceState);

        mWalletIcon = (ImageView) view.findViewById(R.id.wallet_icon);
        mWalletShares = (TextView) view.findViewById(R.id.wallet_shares);
        mWalletMenu = (RecyclerView) view.findViewById(R.id.wallet_menu);
        LinearLayoutManager lrm = new LinearLayoutManager(getContext());
        mWalletMenu.setLayoutManager(lrm);

        String[] mMenuElements = getResources().getStringArray(R.array.wallet_options);
        WalletMenuItemAdapter mAdapter = new WalletMenuItemAdapter(getContext(),mMenuElements,this);
        mWalletShares.setText("" + NumberFormat.getInstance().format(1450987.00) + " shares");
        mWalletMenu.setAdapter(mAdapter);
    }

    @Override
    public void onMenuItemSelection(int item) {
        switch(item){
            case 0: // TODO : change this with constants.
                break;
            case 1:
                break;
            case 2:
                mListener.onEventDispatched(new Event(Event.EVENT_NAVIGATION_WALLET_HISTORY,0,null));
                break;
            default:
                break;
        }
    }
}
