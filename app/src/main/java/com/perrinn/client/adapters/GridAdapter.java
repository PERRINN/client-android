package com.perrinn.client.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.perrinn.client.R;

/**
 * Created by Antreas Christofi on 7/24/2016.
 */
public class GridAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public GridAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageButton for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton imageButton;


        if (convertView == null) {
            imageButton = new ImageButton(mContext);
            imageButton.setLayoutParams(new GridView.LayoutParams(105, 105));
            imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //TODO fix padding
            imageButton.setPadding(1, 1, 1, 1);
        }
        else
        {
            imageButton = (ImageButton) convertView;
        }
        imageButton.setImageResource(mThumbIds[position]);
        return imageButton;
    }

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.in_message_bg, R.drawable.in_message_bg,
            R.drawable.in_message_bg, R.drawable.in_message_bg,
            R.drawable.in_message_bg, R.drawable.in_message_bg,
            R.drawable.in_message_bg, R.drawable.in_message_bg,
            R.drawable.in_message_bg, R.drawable.in_message_bg,
            R.drawable.in_message_bg, R.drawable.in_message_bg,
            R.drawable.in_message_bg, R.drawable.in_message_bg,
            R.drawable.in_message_bg, R.drawable.in_message_bg,
            R.drawable.in_message_bg, R.drawable.in_message_bg,
            R.drawable.in_message_bg, R.drawable.in_message_bg
    };

}

