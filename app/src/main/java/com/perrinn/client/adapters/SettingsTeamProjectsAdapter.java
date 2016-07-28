package com.perrinn.client.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.perrinn.client.R;

import java.util.ArrayList;

/**
 * Created by alessandrosilacci on 27/07/16.
 */
public class SettingsTeamProjectsAdapter extends RecyclerView.Adapter<SettingsTeamProjectsAdapter.ViewHolder>{
    private ArrayList<String> mProjects;

    public SettingsTeamProjectsAdapter(ArrayList<String> projects) {
        mProjects = projects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_team_project_list_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String item = mProjects.get(position);
        holder.mSettingsTeamProjectListItemName.setText(item);
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private Button mSettingsTeamProjectListItemName;

        public ViewHolder(View itemView) {
            super(itemView);
            mSettingsTeamProjectListItemName = (Button) itemView.findViewById(R.id.settings_team_project_list_item_name);
        }
    }
}
