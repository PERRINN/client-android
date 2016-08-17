package com.perrinn.client.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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
		/*View row = convertView;
		RecordHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
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
		if(item.isUser()) {
			new AsyncBitmapLoader(context, holder.imageItem).execute(item.getUserPic());
			if(item.isSelf()){
				holder.imageItem.setBackground(VectorDrawableCompat.create(context.getResources(),R.drawable.team_members_self_border,null));
			}
		} else {
			VectorDrawableCompat image = item.getImage();
			if(image != null)
				image.setColorFilter(Color.parseColor("#FFFFFFFF"), PorterDuff.Mode.SRC_IN);
			holder.imageItem.setImageDrawable(item.getImage());
		}
		View.OnClickListener itemListener = item.getAttachedListener();
		if(itemListener != null)
			row.setOnClickListener(itemListener);
		return row;*/
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View v = null;
		if(convertView == null){
			v = inflater.inflate(R.layout.row_grid,parent,false);
			TextView mTxtTitle = (TextView) v.findViewById(R.id.item_text);
			ImageView mImageItem = (ImageView) v.findViewById(R.id.item_image);
			Item item = data.get(position);
			mTxtTitle.setText(item.getTitle());
			mTxtTitle.setTextColor(Color.WHITE);
			if(item.isUser()) {
				new AsyncBitmapLoader(context, mImageItem).execute(item.getUserPic());
				if(item.isSelf()){
					mImageItem.setBackground(VectorDrawableCompat.create(context.getResources(),R.drawable.team_members_self_border,null));
				}
			} else {
				VectorDrawableCompat image = item.getImage();
				if(image != null)
					image.setColorFilter(Color.parseColor("#FFFFFFFF"), PorterDuff.Mode.SRC_IN);
				mImageItem.setImageDrawable(item.getImage());
			}
			View.OnClickListener itemListener = item.getAttachedListener();
			if(itemListener != null)
				v.setOnClickListener(itemListener);
		}else{
			v = convertView;
		}
		return v;
	}

	static class RecordHolder {
		TextView txtTitle;
		ImageView imageItem;

	}
}