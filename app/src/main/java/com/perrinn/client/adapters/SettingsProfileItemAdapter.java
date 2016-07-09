package com.perrinn.client.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perrinn.client.R;
import com.perrinn.client.beans.SettingsProfileListItem;

import java.util.ArrayList;

/**
 * This class is an adapter for the lists of the different types of items present in the settings
 * profile fragment.
 *
 * @author Alessandro
 * @since 07.07.2016
 */
public class SettingsProfileItemAdapter extends RecyclerView.Adapter<SettingsProfileItemAdapter.ViewHolder>{
    private ArrayList<SettingsProfileListItem> mItems;
    private Context mContext;

    public SettingsProfileItemAdapter(Context context, ArrayList<SettingsProfileListItem> items) {
        this.mItems = items;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_profile_list_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SettingsProfileListItem item = mItems.get(position);
        holder.mSettingsProfileListItemName.setText(item.getName());
        if(item.getState() != null) {
            holder.mSettingsProfileListItemState.setText(item.getState());
            if (item.isImportant()) {
                holder.mSettingsProfileListItemState.setTextColor(ContextCompat.getColor(mContext, R.color.colorSettingsProfileItemImportant));
            } else {
                holder.mSettingsProfileListItemState.setTextColor(ContextCompat.getColor(mContext, R.color.colorSettingsProfileItem));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mSettingsProfileListItemName;
        public TextView mSettingsProfileListItemState;

        public ViewHolder(View itemView) {
            super(itemView);
            mSettingsProfileListItemName = (TextView) itemView.findViewById(R.id.settings_profile_list_item_name);
            mSettingsProfileListItemState = (TextView) itemView.findViewById(R.id.settings_profile_list_item_state);
        }

    }
}
