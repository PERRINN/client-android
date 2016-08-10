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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.perrinn.client.adapters.ChatAdapter;
import com.perrinn.client.R;
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
    private static RelativeLayout modifiedDock;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat,container,false);
        modifiedDock= (RelativeLayout) rootView.findViewById(R.id.chatdock);


        chatHistory = new ArrayList<ChatMessage>();
        adapter = new ChatAdapter(getContext(), new ArrayList<ChatMessage>());
        messagesContainer=(ListView)rootView.findViewById(R.id.messagesContainer);
        messageET=(EditText)rootView.findViewById(R.id.messageEdit);
        sendBtn=(Button)rootView.findViewById(R.id.chatSendButton);
        messagesContainer.setAdapter(adapter);


        sendBtn.setOnClickListener(new View.OnClickListener() {
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


}
