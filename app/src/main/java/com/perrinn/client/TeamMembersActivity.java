package com.perrinn.client;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.GridView;

/**
 * Created by Antreas Christofi on 26-07-2016.
 */

public class TeamMembersActivity extends Activity {
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	 GridViewAdapter customGridAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teamMembers);
		
		//set team members
		Bitmap t1Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.in_message_bg);
		Bitmap t2Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.in_message_bg);
		Bitmap t3Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.in_message_bg);
		Bitmap t4Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.in_message_bg);
		Bitmap t5Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.in_message_bg);
		Bitmap t6Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.in_message_bg);
		Bitmap t7Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.in_message_bg);
		Bitmap t8Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.in_message_bg);
		//icons
		Bitmap chatIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_chat_01);
		Bitmap calendarIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_calendar);
		Bitmap activityIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_activity);
		Bitmap documentsIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_documents);
		Bitmap guestIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_guest);
		Bitmap images01Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_images_01);
		Bitmap images02Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_images_02);
		Bitmap mapsIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_maps_01);
		Bitmap microphoneIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_mic);
		Bitmap plusIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_plus);
		Bitmap project01Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_project_01);
		Bitmap project02Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_project_02);
		Bitmap public01Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_public_01);
		Bitmap speakerIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_speaker);

		//add team members
		gridArray.add(new Item(t1Icon,"Member1"));
		gridArray.add(new Item(t2Icon,"Member2"));
		gridArray.add(new Item(t3Icon,"Member3"));
		gridArray.add(new Item(t4Icon,"Member4"));
		gridArray.add(new Item(t5Icon,"Member5"));
		gridArray.add(new Item(t6Icon,"Member6"));
		gridArray.add(new Item(t7Icon,"Member7"));
		gridArray.add(new Item(t8Icon,"Member8"));

		//other icons
		gridArray.add(new Item(chatIcon,"Chat"));
		gridArray.add(new Item(calendarIcon,"Calendar"));
		gridArray.add(new Item(activityIcon,"Activity"));
		gridArray.add(new Item(documentsIcon,"Documents"));
		gridArray.add(new Item(guestIcon,"Guest"));
		gridArray.add(new Item(images01Icon,"Images1"));
		gridArray.add(new Item(images02Icon,"Images2"));
		gridArray.add(new Item(mapsIcon,"Maps"));
		gridArray.add(new Item(microphoneIcon,"Mic"));
		gridArray.add(new Item(plusIcon,"Plus"));
		gridArray.add(new Item(project01Icon,"Project1"));
		gridArray.add(new Item(project02Icon,"Project2"));
		gridArray.add(new Item(public01Icon,"Public1"));
		gridArray.add(new Item(speakerIcon,"Speaker"));


		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new GridViewAdapter(this, R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
	}

}
