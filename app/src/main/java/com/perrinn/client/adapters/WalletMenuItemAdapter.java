package com.perrinn.client.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perrinn.client.R;

import java.util.ArrayList;

/**
 * Created by alessand.silacci on 14.01.2017.
 */

public class WalletMenuItemAdapter extends RecyclerView.Adapter<WalletMenuItemAdapter.ViewHolder>{
    private Context mContext;
    private String[] mElements;
    private OnWalletMenuItemClickListener mListener;

    public WalletMenuItemAdapter(Context mContext, String[] mElements, OnWalletMenuItemClickListener mListener){
        this.mContext = mContext;
        this.mElements = mElements;
        this.mListener = mListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_menu_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mWalletMenuItem.setText(mElements[position]);
        holder.mWalletMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onMenuItemSelection(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mElements.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mWalletMenuItem;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mWalletMenuItem = (TextView) itemView.findViewById(R.id.wallet_menu_item);
        }
    }

    public interface OnWalletMenuItemClickListener {
        void onMenuItemSelection(int item);
    }
}
