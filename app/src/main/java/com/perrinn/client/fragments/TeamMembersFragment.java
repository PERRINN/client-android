package com.perrinn.client.fragments;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.perrinn.client.GridViewAdapter;
import com.perrinn.client.Item;
import com.perrinn.client.R;

/**
 * Created by Antreas Christofi on 26-07-2016.
 */

public class TeamMembersFragment extends Fragment {
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	GridViewAdapter customGridAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_team_members,container,false);

		//icons
		/*Bitmap chatIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_chat_01);
		Bitmap mailIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_mail);
		Bitmap calendarIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_calendar);
		Bitmap activityIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_activity);
		Bitmap documentsIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_documents);
		Bitmap guestIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_guest);
		Bitmap images01Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_images_01);
		Bitmap images02Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_images_02);
		Bitmap mapsIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_maps_01);
		Bitmap microphoneIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_mic);
		Bitmap speakerIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_speaker);*/
		VectorDrawableCompat chatIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_chat_01,null);
		VectorDrawableCompat mailIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_mail,null);
		VectorDrawableCompat calendarIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_calendar,null);
		VectorDrawableCompat activityIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_activity,null);
		VectorDrawableCompat documentsIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_documents,null);
		VectorDrawableCompat guestIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_guest,null);
		VectorDrawableCompat images01Icon = VectorDrawableCompat.create(getResources(),R.drawable.ic_images_01,null);
		VectorDrawableCompat images02Icon = VectorDrawableCompat.create(getResources(),R.drawable.ic_images_02,null);
		VectorDrawableCompat mapsIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_maps_01,null);
		VectorDrawableCompat microphoneIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_mic,null);
		VectorDrawableCompat speakerIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_speaker,null);



		//add team members
		//FUTURE REFERENCE; item can be nullable, e.g when loading team members
		//any unused slots(say the team has 6 instead of 8 persons) can be loaded as
		//a null pic, empty string item to maintain layout

		gridArray.add(new Item(guestIcon,"Mark"));
		gridArray.add(new Item(guestIcon,"Aiko"));
		gridArray.add(new Item(guestIcon,"Vicky"));
		gridArray.add(new Item(guestIcon,"Alan"));
		gridArray.add(new Item(guestIcon,"James"));
		gridArray.add(new Item(guestIcon,"Mathilde"));
		gridArray.add(new Item(guestIcon,"Daniel"));
		gridArray.add(new Item(guestIcon,"Andrea"));

		//other icons
		gridArray.add(new Item(chatIcon,"Chat"));
		gridArray.add(new Item(mailIcon, "Mail"));
		gridArray.add(new Item(microphoneIcon,"Mic"));
		gridArray.add(new Item(speakerIcon,"Speaker"));

		gridArray.add(new Item(guestIcon,"Links"));
		gridArray.add(new Item(activityIcon,"Activity"));
		gridArray.add(new Item(null, ""));
		gridArray.add(new Item(images02Icon,"Video"));


		gridArray.add(new Item(documentsIcon,"Documents"));
		gridArray.add(new Item(calendarIcon,"Calendar"));
		gridArray.add(new Item(images01Icon,"Images"));
		gridArray.add(new Item(mapsIcon,"Maps"));

		gridView = (GridView) rootView.findViewById(R.id.gridView1);
		customGridAdapter = new GridViewAdapter(getContext(), R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
		return rootView;
	}

	public static TeamMembersFragment newInstance(){
		TeamMembersFragment fragment = new TeamMembersFragment();
		return fragment;
	}

}
