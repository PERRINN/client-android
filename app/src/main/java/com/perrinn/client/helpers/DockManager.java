package com.perrinn.client.helpers;

import android.support.v7.widget.RecyclerView;

import com.perrinn.client.beans.DockIndicator;

import java.util.ArrayList;

/**
 * This class manages the dock information accross the fragments and activities of the app.
 *
 * @since 10.08.2016
 * @author Alessandro
 */
public class DockManager {
    private ArrayList<DockIndicator> mIndicators = new ArrayList<>();

    public DockManager(ArrayList<DockIndicator> indicators) {
        mIndicators = indicators;
    }

    public DockManager() {
    }


    public void addNewDockItem(DockIndicator item){
        if(item != null)
            mIndicators.add(item);
    }

    public void removeDockItem(DockIndicator item){
        if(item != null)
            mIndicators.remove(item);
    }

    public ArrayList<DockIndicator> getIndicators(){
        return mIndicators;
    }




}
