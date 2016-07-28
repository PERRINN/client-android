package com.perrinn.client;

import java.util.ArrayList;


import android.graphics.Color;
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
		setContentView(R.layout.activity_team_members);

		//icons
		Bitmap chatIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_chat_01);
		Bitmap mailIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_mail);
		Bitmap calendarIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_calendar);
		Bitmap activityIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_activity);
		Bitmap documentsIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_documents);
		Bitmap guestIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_guest);
		Bitmap images01Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_images_01);
		Bitmap images02Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_images_02);
		Bitmap mapsIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_maps_01);
		Bitmap microphoneIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_mic);
		Bitmap speakerIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_speaker);

		//add team members


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

		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new GridViewAdapter(this, R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
	}

}
