package com.perrinn.client.fragments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import android.content.Context;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.perrinn.client.adapters.ChatAdapter;
import com.perrinn.client.R;
import com.perrinn.client.listeners.InputInteractionListener;
import com.perrinn.client.listeners.SwipeDownGestureListener;
import com.perrinn.client.objects.ChatMessage;

/**
 * Created by Antreas Christofi on 26-07-2016.
 */

public class ChatFragment extends Fragment {

    private Button sendBtn;
    private EditText messageET;
    private ListView messagesContainer;
    private ChatAdapter adapter;
    private ArrayList<ChatMessage> chatHistory;

    private LinearLayout mChatInputContainer;

    private InputInteractionListener mListener;
    private GestureDetectorCompat mDetector;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_chat,container,false);
        chatHistory = new ArrayList<ChatMessage>();
        adapter = new ChatAdapter(getContext(), new ArrayList<ChatMessage>());
        messagesContainer=(ListView)rootView.findViewById(R.id.messagesContainer);
        messageET=(EditText)rootView.findViewById(R.id.messageEdit);
        mChatInputContainer = (LinearLayout) rootView.findViewById(R.id.chat_input_container);
        //sendBtn=(Button)rootView.findViewById(R.id.chatSendButton);
        messagesContainer.setAdapter(adapter);
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
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int delta =  rootView.getRootView().getHeight() - rootView.getHeight();
                if(delta > 396){ // FIXME: density is actually calculated on only one device, might change.
                    mListener.onKeyboardStateChanged(false);
                }else{
                    mListener.onKeyboardStateChanged(true);
                }
            }
        });

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

                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setId(1);//dummy
                    chatMessage.setMessage(messageText);

                    chatMessage.setUsername("Nicolas ");
                    chatMessage.setMe(true);


                    messageET.setText("");

                    displayMessage(chatMessage);
                    return true;
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
        scroll();
    }
    /*
    * //////////////////////////////////////////////////
    * //function for scrolling through the chat
    * /////////////////////////////////////////////////
    */
    private void scroll() {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
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
