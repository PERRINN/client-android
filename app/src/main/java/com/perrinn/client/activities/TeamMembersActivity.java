package com.perrinn.client.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.perrinn.client.R;

public class TeamMembersActivity extends Activity {


    Intent intent = getIntent();

    private Integer[] mThumbIds = {
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
            R.drawable.in_message_bg,
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_team_members);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new MyAdapter(this));
    }

    public class MyAdapter extends BaseAdapter {

        private Context mContext;

        public MyAdapter(Context c) {
            mContext = c;
        }

        @Override
        public int getCount() {
            return mThumbIds.length;
        }

        @Override
        public Object getItem(int arg0) {
            return mThumbIds[arg0];
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View grid;

            if(convertView==null){
                grid = new View(mContext);
                LayoutInflater inflater=getLayoutInflater();
                grid=inflater.inflate(R.layout.mygrid_layout, parent, false);
            }else{
                grid = (View)convertView;
            }

            ImageView imageView = (ImageView)grid.findViewById(R.id.image);
            imageView.setImageResource(mThumbIds[position]);

            return grid;
        }

    }

}