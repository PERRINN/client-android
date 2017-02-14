package com.perrinn.client.fragments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.perrinn.client.adapters.ChatAdapter;
import com.perrinn.client.R;
import com.perrinn.client.beans.Team;
import com.perrinn.client.listeners.InputInteractionListener;
import com.perrinn.client.listeners.SwipeDownGestureListener;
import com.perrinn.client.objects.ChatMessage;
import com.squareup.picasso.Picasso;

/**
 * Created by Antreas Christofi on 26-07-2016.
 */

public class ChatFragment extends Fragment {
    private static final String FRAGMENT_PARAM_TEAM = "com.perrinn.client.fragments.ChatFragment.FRAGMENT_PARAM_TEAM";
    private Button sendBtn;
    private ImageView mChatBackgroundHolder;
    private TextView mChatTeamName;
    private EditText messageET;
    private ListView messagesContainer;
    private ChatAdapter adapter;
    private ArrayList<ChatMessage> chatHistory;

    private LinearLayout mChatInputContainer;

    private InputInteractionListener mListener;
    private GestureDetectorCompat mDetector;
    private Team mTeam;

    private final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mConversationsRef;
    private DatabaseReference mNewConversationsRef;
    private DatabaseReference mInboxRef;
    private DatabaseReference mSendingRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_chat,container,false);
        chatHistory = new ArrayList<ChatMessage>();
        adapter = new ChatAdapter(getContext(), new ArrayList<ChatMessage>());
        messagesContainer=(ListView)rootView.findViewById(R.id.messagesContainer);
        messageET=(EditText)rootView.findViewById(R.id.messageEdit);
        mChatBackgroundHolder = (ImageView) rootView.findViewById(R.id.chat_background_holder);
        mChatTeamName = (TextView) rootView.findViewById(R.id.chat_team_name);
        mChatInputContainer = (LinearLayout) rootView.findViewById(R.id.chat_input_container);
        //sendBtn=(Button)rootView.findViewById(R.id.chatSendButton);
        messagesContainer.setAdapter(adapter);
        mConversationsRef = mDatabase.getReference("conversations");
        mNewConversationsRef = mConversationsRef.push();
        mInboxRef = mConversationsRef.child("lk3f4fj3p4j");
        mSendingRef = mConversationsRef.child("lk3f4fj3p4j");
        mInboxRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMessage newMessage = dataSnapshot.getValue(ChatMessage.class);
                if(newMessage == null) return;
                displayMessage(newMessage);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final InputMethodManager mImm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mDetector = new GestureDetectorCompat(getContext(),new SwipeDownGestureListener(new SwipeDownGestureListener.SwipeDownListener() {
            @Override
            public void onSwipeDown() {
                if(messageET.hasFocus()) {
                    messageET.clearFocus();
                    mImm.hideSoftInputFromWindow(messageET.getWindowToken(), 0);
                }
            }
        }));
        messagesContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return false;
            }
        });
        rootView.setLongClickable(true);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return false;
            }
        });

        Bundle args = getArguments();
        if(args != null){
            mTeam = args.getParcelable(FRAGMENT_PARAM_TEAM);
            mChatTeamName.setText(mTeam.getName().toUpperCase());
            Picasso.with(getContext()).load(mTeam.getBgres()).fit().into(mChatBackgroundHolder);
        }else{
            mChatTeamName.setVisibility(View.GONE);
        }


        /*sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageET.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }

                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setId(1);//dummy
                chatMessage.setMessage(messageText);

                chatMessage.setUsername("Nicolas ");
                chatMessage.setMe(true);


                messageET.setText("");

                displayMessage(chatMessage);
            }
        });*/
        messageET.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    String messageText = messageET.getText().toString();
                    if (TextUtils.isEmpty(messageText)) {
                        return false;
                    }

                    /*ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setId(1);//dummy
                    chatMessage.setMessage(messageText);

                    chatMessage.setUsername("Nicolas ");
                    chatMessage.setMe(true);*/


                    messageET.setText("");
                    ChatMessage msg = new ChatMessage(0,true,messageText,"Nicolas",System.currentTimeMillis());
                    mSendingRef.push().setValue(msg);
                   // displayMessage(chatMessage);
                    return false;
                }

                return true;
            }
        });


        for(int i=0; i<chatHistory.size(); i++) {
            ChatMessage message = chatHistory.get(i);
            displayMessage(message);
        }


        return rootView;
    }


    /*
    * //////////////////////////////////////////////////
    * //function to display messages
    * /////////////////////////////////////////////////
    */
    public void displayMessage(ChatMessage message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
        //scroll();
    }
    /*
    * //////////////////////////////////////////////////
    * //function for scrolling through the chat
    * /////////////////////////////////////////////////
    */
    private void scroll() {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
    }

    public static ChatFragment newInstance(Team team){
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putParcelable(FRAGMENT_PARAM_TEAM,team);
        fragment.setArguments(args);
        return fragment;
    }

    public static ChatFragment newInstance(){
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof InputInteractionListener){
            mListener = (InputInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    +" must implement the InputInteractionListener.");
        }
    }
}
