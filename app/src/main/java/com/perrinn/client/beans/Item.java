package com.perrinn.client.beans;

import android.graphics.Bitmap;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.view.View;

/**
 * Created by Antreas Christofi on 26-07-2016.
 */



public class Item {
	VectorDrawableCompat image;
	String title;
	View.OnClickListener listener;
	private Integer userPic;
	private boolean user;
	private boolean self;

	public Item(VectorDrawableCompat image, String title, View.OnClickListener listener){
		this.image = image;
		this.title = title;
		this.listener = listener;
		this.user = false;
	}

	public Item(Integer image, String title, View.OnClickListener listener, boolean self) {
		this.userPic = image;
		this.title = title;
		this.listener = listener;
		this.user = true;
		this.self = self;
	}
	public Item(VectorDrawableCompat image, String title) {
		this(image,title,null);
	}

	public Item(Integer image, String title,boolean self) {
		this(image,title,null,self);
	}

	public Item(){}


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

	public Integer getUserPic() {
		return userPic;
	}

	public void setUserPic(Integer userPic) {
		this.userPic = userPic;
	}

	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	public boolean isSelf() {
		return self;
	}

	public void setSelf(boolean self) {
		this.self = self;
	}
}
