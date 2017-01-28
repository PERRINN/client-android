package com.perrinn.client.fragments;

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
import com.perrinn.client.adapters.WalletTransactionItemAdapter;
import com.perrinn.client.beans.Transaction;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alessand.silacci on 13.01.2017.
 */

public class WalletTransacHistFragment extends MultiScreenChildFragment {
    private RecyclerView mWalletHistory;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setIncludedFragmentLayout(R.layout.fragment_wallet_transac_hist);
        super.onViewCreated(view, savedInstanceState);
        mWalletHistory = (RecyclerView) view.findViewById(R.id.wallet_history);
        LinearLayoutManager lrm = new LinearLayoutManager(getContext());
        mWalletHistory.setLayoutManager(lrm);

        ArrayList<WalletTransactionItemAdapter.WalletTransactionSection> sections = new ArrayList<>();
        sections.add(new WalletTransactionItemAdapter.WalletTransactionSection(4,"12/12/2016",19252.43));
        sections.add(new WalletTransactionItemAdapter.WalletTransactionSection(6,"11/12/2016",18252.43));
        sections.add(new WalletTransactionItemAdapter.WalletTransactionSection(10,"10/12/2016",16252.43));

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("XXXXXXX XXX XXX XX XXX", "PU54 6BN", -500.0, new Date()));
        transactions.add(new Transaction("XXXXXXX", "PU54 6BN", -500.0, new Date()));
        transactions.add(new Transaction("XXXXXXX XXXX XXX XXXXXX", "PU54 6BN", -500.0, new Date()));
        transactions.add(new Transaction("XXXXXXX XXXX XXXX", "PU54 6BN", 1000.0, new Date()));
        transactions.add(new Transaction("XXXXXXX XXXX XXX XXXX", "PU54 6BN", 500.0, new Date()));
        transactions.add(new Transaction("XXXXXXX XXXXXXX", "PU54 6BN", 500.0, new Date()));
        transactions.add(new Transaction("XXXXXXX", "PU54 6BN", 500.0, new Date()));
        transactions.add(new Transaction("XXXXXXX XX XXXXXXX", "PU54 6BN", 500.0, new Date()));


        mWalletHistory.setAdapter(new WalletTransactionItemAdapter(sections, transactions, getContext()));

    }
}
