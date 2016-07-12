package com.perrinn.client.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrinn.client.R;

import java.util.ArrayList;

/**
 * This class is an adapter for the team list. It will render each the team list items using the
 * layout specified.
 *
 * @since 30.06.2016
 * @author Alessandro
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder>{
    private ArrayList<String> mTeams;
    private Context mContext;

    public TeamAdapter(ArrayList<String> mTeams, Context mContext) {
        this.mTeams = mTeams;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String team = mTeams.get(position);
        holder.mTeamListItemName.setText(team.toUpperCase());
    }

    @Override
    public int getItemCount() {
        return mTeams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mTeamListItemIndicator;
        public TextView mTeamListItemName;

        public ViewHolder(View itemView) {
            super(itemView);
            mTeamListItemIndicator = (ImageView) itemView.findViewById(R.id.team_list_item_indicator);
            mTeamListItemName = (TextView) itemView.findViewById(R.id.team_list_item_name);
        }
    }
}
