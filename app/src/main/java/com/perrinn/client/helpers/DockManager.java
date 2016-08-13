package com.perrinn.client.helpers;

import android.support.v7.widget.RecyclerView;

import com.perrinn.client.beans.DockIndicator;

import java.util.ArrayList;

/**
 * Created by alessandrosilacci on 10/08/16.
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
