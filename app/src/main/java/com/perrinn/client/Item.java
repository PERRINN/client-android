package com.perrinn.client;

import android.graphics.Bitmap;
import android.graphics.drawable.VectorDrawable;
import android.support.graphics.drawable.VectorDrawableCompat;

/**
 * Created by Antreas Christofi on 26-07-2016.
 */



public class Item {
	VectorDrawableCompat image;
	String title;
	
	public Item(VectorDrawableCompat image, String title) {
		super();
		this.image = image;
		this.title = title;
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


	

}
