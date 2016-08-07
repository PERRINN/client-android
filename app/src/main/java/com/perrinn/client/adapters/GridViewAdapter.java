package com.perrinn.client.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrinn.client.R;
import com.perrinn.client.beans.Item;
import com.perrinn.client.loaders.AsyncBitmapLoader;

/**
 * Created by Antreas Christofi on 26-07-2016.
 */


public class GridViewAdapter extends ArrayAdapter<Item> {
	Context context;
	int layoutResourceId;
	ArrayList<Item> data = new ArrayList<Item>();

	public GridViewAdapter(Context context, int layoutResourceId,
						   ArrayList<Item> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
			holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
			holder.txtTitle.setTextColor(Color.WHITE);

			holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		Item item = data.get(position);
		holder.txtTitle.setText(item.getTitle());
		if(item.isUser())
			new AsyncBitmapLoader(context,holder.imageItem).execute(item.getUserPic());
		else {
			VectorDrawableCompat image = item.getImage();
			if(image != null)
				image.setColorFilter(Color.parseColor("#FFFFFFFF"), PorterDuff.Mode.SRC_IN);
			holder.imageItem.setImageDrawable(item.getImage());
		}
		View.OnClickListener itemListener = item.getAttachedListener();
		if(itemListener != null)
			row.setOnClickListener(itemListener);
		return row;

	}

	static class RecordHolder {
		TextView txtTitle;
		ImageView imageItem;

	}
}