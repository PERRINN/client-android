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
import com.perrinn.client.beans.Team;

import java.util.ArrayList;

/**
 * This class is an adapter for the team list. It will render each the team list items using the
 * layout specified.
 *
 * @since 30.06.2016
 * @author Alessandro
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder>{
    private ArrayList<Team> mTeams;
    private Context mContext;
    private OnTeamListItemInteractionListener mListener;

    public TeamAdapter(ArrayList<Team> mTeams, Context mContext,OnTeamListItemInteractionListener listener) {
        this.mTeams = mTeams;
        this.mContext = mContext;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Team team = mTeams.get(position);
        holder.mTeamListItemName.setText(team.getName().toUpperCase());
        if(team.isSelected()){
            holder.mTeamListItemName.setBackgroundResource(R.drawable.team_list_selected_item);
        }
        final int[] pos = {position};
        if(mListener != null)
            holder.mTeamListItemName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onTeamListItemInteraction(pos[0]);
                }
            });
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

    public interface OnTeamListItemInteractionListener{
        void onTeamListItemInteraction(int teamIndex);
    }
}
