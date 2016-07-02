package com.perrinn.client.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.perrinn.client.R;
import com.perrinn.client.beans.DockIndicator;

import java.util.ArrayList;

/**
 * This class is an adapter for the little dots indicators present on the dock.
 *
 * @author Alessandro
 * @since 02.06.2016
 */
public class DockItemAdapter extends RecyclerView.Adapter<DockItemAdapter.ViewHolder>{
    private ArrayList<DockIndicator> mIndicators;
    private Context mContext;
    private OnDockIndicatorClickListener mListener;

    public DockItemAdapter(Context context, ArrayList<DockIndicator> indicators){
        this.mContext = context;
        this.mIndicators = indicators;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dock_indicator_item,
                parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DockIndicator[] item = {mIndicators.get(position)};
        final int[] pos = {position};
        if(item[0].isActive())
            DrawableCompat.setTint(holder.mDockIndicatorButton.getDrawable(),
                    ContextCompat.getColor(mContext,R.color.colorDockIndicatorActive));
        else
            DrawableCompat.setTint(holder.mDockIndicatorButton.getDrawable(),
                    ContextCompat.getColor(mContext,R.color.colorDockIndicatorInactive));
        if(mListener != null)
            holder.mDockIndicatorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(item[0],pos[0]);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mIndicators.size();
    }

    /**
     * This method registers a listener to the click events on the dots of the dock.
     *
     * @param listener the listener to register.
     * */
    public void setOnDockIndicatorClickListener(OnDockIndicatorClickListener listener){
        this.mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageButton mDockIndicatorButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mDockIndicatorButton = (ImageButton) itemView.findViewById(R.id.dock_indicator_button);
        }
    }

    public interface OnDockIndicatorClickListener {
        void onClick(DockIndicator indicator, int position);
    }
}
