package com.perrinn.client.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.perrinn.client.objects.ChatMessage;
import com.perrinn.client.R;

import java.util.List;

/**
 * Created by Antreas Christofi on 20-07-2016.
 */
public class ChatAdapter extends BaseAdapter {
    /*
    * //////////////////////////////////////////////////
    * //variables
    * /////////////////////////////////////////////////
    */
    private final List<ChatMessage> chatMessages;
    private Activity context;

    /*
* //////////////////////////////////////////////////
* //constructor
* /////////////////////////////////////////////////
*/
    public ChatAdapter(Activity context, List<ChatMessage> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;
    }
    /*
    * //////////////////////////////////////////////////
    * //get message count
    * /////////////////////////////////////////////////
    */
    @Override
    public int getCount() {
        if (chatMessages != null) {
            return chatMessages.size();
        } else {
            return 0;
        }
    }

    /*
* //////////////////////////////////////////////////
* //get position of chatmessage object
* /////////////////////////////////////////////////
*/
    @Override
    public ChatMessage getItem(int position) {
        if (chatMessages != null) {
            return chatMessages.get(position);
        } else {
            return null;
        }
    }
    /*
    * //////////////////////////////////////////////////
    * //get id of chatmessage
    * /////////////////////////////////////////////////
    */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
* //////////////////////////////////////////////////
* //get view for chat ui
* /////////////////////////////////////////////////
*/
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ChatMessage chatMessage = getItem(position);
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = vi.inflate(R.layout.list_item_chat_message, null);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        boolean myMsg = chatMessage.getIsme() ;//Just a dummy check to simulate whether it me or other sender
        holder.txtMessage.setText(chatMessage.getMessage());
        holder.txtInfo.setText(chatMessage.getDate());


        return convertView;
    }

    /*
* //////////////////////////////////////////////////
* //add message to list
* /////////////////////////////////////////////////
*/
    public void add(ChatMessage message) {
        chatMessages.add(message);
    }

    /*
* //////////////////////////////////////////////////
* //add all messages into list
* /////////////////////////////////////////////////
*/
    public void add(List<ChatMessage> messages) {
        chatMessages.addAll(messages);
    }
/*
* //////////////////////////////////////////////////
* //create a viewholder to hold the view
* /////////////////////////////////////////////////
*/
    private ViewHolder createViewHolder(View v) {
        ViewHolder holder = new ViewHolder();
        holder.txtMessage = (TextView) v.findViewById(R.id.txtMessage);
        holder.content = (LinearLayout) v.findViewById(R.id.content);
        holder.contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
        holder.txtInfo = (TextView) v.findViewById(R.id.txtInfo);
        return holder;
    }

    /*
    * //////////////////////////////////////////////////
    * //viewholder class
    * /////////////////////////////////////////////////
    */
    private static class ViewHolder {
        public TextView txtMessage;
        public TextView txtInfo;
        public LinearLayout content;
        public LinearLayout contentWithBG;
    }
}
