package com.perrinn.client.helpers;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;

import com.perrinn.client.beans.DockIndicator;
import com.perrinn.client.beans.Member;
import com.perrinn.client.beans.Team;
import com.perrinn.client.listeners.OnTeamSelectionChangeListener;

import java.util.ArrayList;

/**
 * This class manages the dock information accross the fragments and activities of the app.
 *
 * @since 10.08.2016
 * @author Alessandro
 */
public class DockManager implements OnTeamSelectionChangeListener,Parcelable{
    private ArrayList<Team> mTeams = new ArrayList<>();
    private int selectedIndex;

    public DockManager(ArrayList<Team> teams){
        this.mTeams = teams;
        this.selectedIndex = 0;
    }

    public DockManager() {

    }

    public DockManager(Parcel source) {
        this.selectedIndex = source.readInt();
        source.readTypedList(mTeams, Team.CREATOR);

    }

    public ArrayList<Team> getmTeams() {
        return mTeams;
    }

    public void setmTeams(ArrayList<Team> mTeams) {
        this.mTeams = mTeams;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }


    @Override
    public void onTeamSelected(int index) {
        if((this.mTeams.size() == 0  && this.mTeams.size() < index)|| this.selectedIndex == index)
            return;
        this.mTeams.get(selectedIndex).setSelected(false);
        this.mTeams.get(index).setSelected(true);
        this.selectedIndex = index;
    }

    @Override
    public int describeContents() {
        return 1+(mTeams != null ? mTeams.size():0);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(selectedIndex);
        dest.writeTypedList(mTeams);
    }

    public static final Parcelable.Creator<DockManager> CREATOR = new Parcelable.Creator<DockManager>(){

        @Override
        public DockManager createFromParcel(Parcel source) {
            return new DockManager(source);
        }

        @Override
        public DockManager[] newArray(int size) {
            return new DockManager[size];
        }
    };
}
