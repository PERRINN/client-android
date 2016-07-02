package com.perrinn.client.helpers;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * This class is a decoration class for the recyclerview used in the dock. It adds a right margin
 * between the dots.
 *
 * @author Alessandro
 * @since 02.06.2016
 */
public class DockItemMarginDecorator extends RecyclerView.ItemDecoration{
    private int margin;

    public DockItemMarginDecorator(Context context, @DimenRes int dimenId) {
        this.margin = context.getResources().getDimensionPixelSize(dimenId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if(parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount()){
            outRect.right = margin;
        }
    }
}
