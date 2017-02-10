package com.perrinn.client.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.google.android.gms.wallet.Wallet;
import com.perrinn.client.R;
import com.perrinn.client.beans.Transaction;
import com.perrinn.client.fragments.WalletScreensFragment;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by alessand.silacci on 30.12.2016.
 */

public class WalletTransactionItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_VIEW_HOLDER_TYPE = 0;
    private static final int TRANSACTION_VIEW_HOLDER_TYPE = 1;
    private ArrayList<WalletTransactionSection> mSections;
    private ArrayList<Transaction> mTransactions;
    private Context mContext;
    private int transactionOffset = 0;
    private int lastSectionPos = 0;

    public WalletTransactionItemAdapter(ArrayList<WalletTransactionSection> mSections, ArrayList<Transaction> mTransactions, Context mContext) {
        this.mSections = mSections;
        this.mTransactions = mTransactions;
        this.mContext = mContext;
        this.lastSectionPos = this.mSections.get(this.mSections.size()-1).getPosition();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        if(viewType == HEADER_VIEW_HOLDER_TYPE){
            View v = LayoutInflater.from(mContext).inflate(R.layout.wallet_transaction_header, parent, false);
            vh = new HeaderViewHolder(v);
        } else {
            View v = LayoutInflater.from(mContext).inflate(R.layout.wallet_transaction_item, parent, false);
            vh = new TransactionViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        if(viewType == HEADER_VIEW_HOLDER_TYPE){
            int sectionPos = transactionOffset-1;
            WalletTransactionSection sec = mSections.get(sectionPos);
            HeaderViewHolder headerVH = (HeaderViewHolder) holder;
            headerVH.mWalletTransactionHeaderTitle.setText(sec.getName());
            if(sectionPos > 0) {
                headerVH.mWalletTransactionHeaderAmount.setText(NumberFormat.getInstance().format(sec.getAmount()));
            }
        } else {
            Transaction transac = mTransactions.get(position-transactionOffset);
            TransactionViewHolder transactionVH = (TransactionViewHolder) holder;
            transactionVH.mWalletTransactionItemIban.setText(transac.getIban().toUpperCase());
            transactionVH.mWalletTransactionItemValue.setText(transac.getValue().toUpperCase());
            double amount = transac.getAmount();
            String strAmount = "+" + NumberFormat.getInstance().format(amount);
            if(amount < 0){
                strAmount = "("+amount+")";
            }
            transactionVH.mWalletTransactionItemAmount.setText(strAmount);
        }
    }

    @Override
    public int getItemCount() {
        return mSections.size()+mTransactions.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(lastSectionPos < position){
            return TRANSACTION_VIEW_HOLDER_TYPE;
        }

        for(WalletTransactionSection sec : mSections){
            if(sec.getPosition() == position){
                transactionOffset++;
                return HEADER_VIEW_HOLDER_TYPE;
            }
        }
        return TRANSACTION_VIEW_HOLDER_TYPE;
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        public TextView mWalletTransactionHeaderTitle;
        public TextView mWalletTransactionHeaderAmount;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.mWalletTransactionHeaderTitle = (TextView) itemView.findViewById(R.id.wallet_transaction_header_title);
            this.mWalletTransactionHeaderAmount = (TextView) itemView.findViewById(R.id.wallet_transaction_header_amount);
        }
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder{
        public TextView mWalletTransactionItemIban;
        public TextView mWalletTransactionItemValue;
        public TextView mWalletTransactionItemAmount;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            this.mWalletTransactionItemIban = (TextView) itemView.findViewById(R.id.wallet_transaction_item_iban);
            this.mWalletTransactionItemValue = (TextView) itemView.findViewById(R.id.wallet_transaction_item_value);
            this.mWalletTransactionItemAmount = (TextView) itemView.findViewById(R.id.wallet_transaction_item_amount);
        }
    }

    public static class WalletTransactionSection {
        private int position;
        private String name;
        private double amount;
        public WalletTransactionSection(int position, String name, double amount){
            this.position = position;
            this.name = name;
            this.amount = amount;
        }

        public int getPosition() {
            return position;
        }

        public String getName() {
            return name;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }

}
