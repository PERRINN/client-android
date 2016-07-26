package com.perrinn.client;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.GridView;

import com.perrinn.client.CustomGridViewAdapter;
import com.perrinn.client.Item;
import com.perrinn.client.R;


public class testActivity extends Activity {
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	 CustomGridViewAdapter customGridAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		//set grid view item
		Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.in_message_bg);
		Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.out_message_bg);
		
		gridArray.add(new Item(homeIcon,"Home"));
		gridArray.add(new Item(userIcon,"User"));
		gridArray.add(new Item(homeIcon,"House"));
		gridArray.add(new Item(userIcon,"Friend"));
		gridArray.add(new Item(homeIcon,"Home"));
		gridArray.add(new Item(userIcon,"Personal"));
		gridArray.add(new Item(homeIcon,"Home"));
		gridArray.add(new Item(userIcon,"User"));
		gridArray.add(new Item(homeIcon,"Building"));
		gridArray.add(new Item(userIcon,"User"));
		gridArray.add(new Item(homeIcon,"Home"));
		gridArray.add(new Item(userIcon,"xyz"));
		
		
		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
	}

}
