package com.perrinn.client;

import android.graphics.Bitmap;
import android.graphics.drawable.VectorDrawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.view.View;

/**
 * Created by Antreas Christofi on 26-07-2016.
 */



public class Item {
	VectorDrawableCompat image;
	String title;
	View.OnClickListener listener;

	public Item(VectorDrawableCompat image, String title, View.OnClickListener listener){
		this.image = image;
		this.title = title;
		this.listener = listener;
	}

	public Item(VectorDrawableCompat image, String title) {
		this(image,title,null);
	}
	public VectorDrawableCompat getImage() {
		return image;
	}
	public void setImage(VectorDrawableCompat image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public View.OnClickListener getAttachedListener(){
		return this.listener;
	}


	

}
