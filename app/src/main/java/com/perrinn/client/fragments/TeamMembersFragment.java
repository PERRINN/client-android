package com.perrinn.client.fragments;

import java.util.ArrayList;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.perrinn.client.activities.SettingsActivity;
import com.perrinn.client.adapters.GridViewAdapter;
import com.perrinn.client.beans.Item;
import com.perrinn.client.R;
import com.perrinn.client.loaders.AsyncBitmapLoader;

/**
 * Created by Antreas Christofi on 26-07-2016.
 */

public class TeamMembersFragment extends Fragment {
	private static final String FRAGMENT_PARAM_TITLE = "com.perrinn.client.fragments.TeamMembersFragment.FRAGMENT_PARAM_TITLE";
	private static final String FRAGMENT_PARAM_DESC = "com.perrinn.client.fragments.TeamMembersFragment.FRAGMENT_PARAM_DESC";
	private static final String FRAGMENT_PARAM_BACKGROUND = "com.perrinn.client.fragments.TeamMembersFragment.FRAGMENT_PARAM_BACKGROUND";
	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	GridViewAdapter customGridAdapter;

	private TextView mTextViewTeamName;
	private TextView mTextViewMessageToTeam;
	private ImageView mTeamMembersBackground;
	private OnTeamMembersFragmentInteractionListener mListener;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_team_members,container,false);
		mTextViewTeamName = (TextView) rootView.findViewById(R.id.textViewTeamName);
		mTextViewMessageToTeam = (TextView) rootView.findViewById(R.id.textViewMessageToTeam);
		mTeamMembersBackground = (ImageView) rootView.findViewById(R.id.team_members_background);
		Bundle args = getArguments();
		if(args != null){
			mTextViewTeamName.setText(args.getString(FRAGMENT_PARAM_TITLE));
			mTextViewMessageToTeam.setText(args.getString(FRAGMENT_PARAM_DESC));
			new AsyncBitmapLoader(getContext(),mTeamMembersBackground).execute(args.getInt(FRAGMENT_PARAM_BACKGROUND));
		}
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
		VectorDrawableCompat linksIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_link_01,null);
		VectorDrawableCompat images01Icon = VectorDrawableCompat.create(getResources(),R.drawable.ic_images_01,null);
		VectorDrawableCompat images02Icon = VectorDrawableCompat.create(getResources(),R.drawable.ic_images_02,null);
		VectorDrawableCompat mapsIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_maps_01,null);
		VectorDrawableCompat microphoneIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_mic,null);
		VectorDrawableCompat speakerIcon = VectorDrawableCompat.create(getResources(),R.drawable.ic_speaker,null);

		//team members pictures
		/*Bitmap user1 = BitmapFactory.decodeResource(getResources(),R.drawable.placeholder_team_members_1);
		Bitmap user2 = BitmapFactory.decodeResource(getResources(),R.drawable.placeholder_team_members_2);
		Bitmap user3 = BitmapFactory.decodeResource(getResources(),R.drawable.placeholder_team_members_3);
		Bitmap user4 = BitmapFactory.decodeResource(getResources(),R.drawable.placeholder_team_members_4);
		Bitmap user5 = BitmapFactory.decodeResource(getResources(),R.drawable.placeholder_team_members_5);
		Bitmap user6 = BitmapFactory.decodeResource(getResources(),R.drawable.placeholder_team_members_6);
		Bitmap user7 = BitmapFactory.decodeResource(getResources(),R.drawable.placeholder_team_members_7);
		Bitmap user8 = BitmapFactory.decodeResource(getResources(),R.drawable.placeholder_team_members_8);
		*/

		//add team members
		//FUTURE REFERENCE; item can be nullable, e.g when loading team members
		//any unused slots(say the team has 6 instead of 8 persons) can be loaded as
		//a null pic, empty string item to maintain layout

		gridArray.add(new Item(R.drawable.placeholder_team_members_1, "Mark*", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onSelfPicturePressed();
			}
		},true));
		gridArray.add(new Item(R.drawable.placeholder_team_members_2,"Aiko*",new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				mListener.onColleaguePicturePressed(0);
			}
		},false));
		gridArray.add(new Item(R.drawable.placeholder_team_members_5,"Vicky",new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				mListener.onColleaguePicturePressed(0);
			}
		},false));
		gridArray.add(new Item(R.drawable.placeholder_team_members_3,"Alan",new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				mListener.onColleaguePicturePressed(0);
			}
		},false));
		gridArray.add(new Item(R.drawable.placeholder_team_members_4,"James",new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				mListener.onColleaguePicturePressed(0);
			}
		},false));
		gridArray.add(new Item(R.drawable.placeholder_team_members_6,"Mathilde",new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				mListener.onColleaguePicturePressed(0);
			}
		},false));
		gridArray.add(new Item(R.drawable.placeholder_team_members_7,"Daniel",new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				mListener.onColleaguePicturePressed(0);
			}
		},false));
		gridArray.add(new Item(R.drawable.placeholder_team_members_8,"Andrea",new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				mListener.onColleaguePicturePressed(0);
			}
		},false));

		//other icons
		gridArray.add(new Item(chatIcon, "Chat", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onChatButtonPressed();
			}
		}));
		gridArray.add(new Item(mailIcon, "Mail", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onMailButtonPressed();
			}
		}));
		gridArray.add(new Item(microphoneIcon, "Mic", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onMicButtonPressed();
			}
		}));
		gridArray.add(new Item(speakerIcon, "Speaker", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onSpeakerButtonPressed();
			}
		}));

		gridArray.add(new Item(linksIcon, "Links", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onGuestButtonPressed();
			}
		}));
		gridArray.add(new Item(activityIcon, "Activity", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onActivityButtonPressed();
			}
		}));
		gridArray.add(new Item());
		gridArray.add(new Item(images02Icon, "Video", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onImages02ButtonPressed();
			}
		}));


		gridArray.add(new Item(documentsIcon, "Documents", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onDocumentsButtonPressed();
			}
		}));
		gridArray.add(new Item(calendarIcon, "Calendar", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onCalendarButtonPressed();
			}
		}));
		gridArray.add(new Item(images01Icon, "Images", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onImages01ButtonPressed();
			}
		}));
		gridArray.add(new Item(mapsIcon, "Maps", new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onMapsButtonPressed();
			}
		}));

		gridView = (GridView) rootView.findViewById(R.id.gridView1);
		customGridAdapter = new GridViewAdapter(getContext(), R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
		return rootView;
	}

	public static TeamMembersFragment newInstance(String teamTitle, String teamDesc, int backgroundRes){
		TeamMembersFragment fragment = new TeamMembersFragment();
		Bundle params = new Bundle();
		params.putString(FRAGMENT_PARAM_TITLE,teamTitle);
		params.putString(FRAGMENT_PARAM_DESC,teamDesc);
		params.putInt(FRAGMENT_PARAM_BACKGROUND,backgroundRes);
		fragment.setArguments(params);
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if(context instanceof OnTeamMembersFragmentInteractionListener){
			mListener = (OnTeamMembersFragmentInteractionListener) context;
		}else{
			throw new RuntimeException(context.toString()
					+" must implement the OnTeamMembersFragmentInteractionListener.");
		}
	}

	public interface OnTeamMembersFragmentInteractionListener{
		void onSelfPicturePressed();
		void onColleaguePicturePressed(int colleagueID);
		void onChatButtonPressed();
		void onMailButtonPressed();
		void onCalendarButtonPressed();
		void onActivityButtonPressed();
		void onDocumentsButtonPressed();
		void onGuestButtonPressed();
		void onImages01ButtonPressed();
		void onImages02ButtonPressed();
		void onMapsButtonPressed();
		void onMicButtonPressed();
		void onSpeakerButtonPressed();
	}

}
