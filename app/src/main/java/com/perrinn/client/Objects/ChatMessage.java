package com.perrinn.client.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Antreas Christofi on 20-07-2016.
 */
public class ChatMessage implements Parcelable{
    /*
 * //////////////////////////////////////////////////
 * //variables
 * /////////////////////////////////////////////////
 */
    public long id;
    public boolean isMe;
    public String message;
    //private Long userId;
    public String username;
    public long time;

    private ChatMessage(){

    }

    public ChatMessage(long id, boolean isMe, String message, String username, long time) {
        this.id = id;
        this.isMe = isMe;
        this.message = message;
        //this.userId = userId;
        this.username = username;
        this.time = time;
    }

    public ChatMessage(Parcel source){
        this.id = source.readLong();
        boolean[] isMeA = new boolean[1];
        source.readBooleanArray(isMeA);
        this.isMe = isMeA[0];
        this.message = source.readString();
        this.username = source.readString();
        this.time = source.readLong();
    }

    @Override
    public int describeContents() {
        return 5;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeBooleanArray(new boolean[] {isMe});
        parcel.writeString(message);
        parcel.writeString(username);
        parcel.writeLong(time);
    }

    public static final Parcelable.Creator<ChatMessage> CREATOR = new Parcelable.Creator<ChatMessage>(){

        @Override
        public ChatMessage createFromParcel(Parcel parcel) {
            return new ChatMessage(parcel);
        }

        @Override
        public ChatMessage[] newArray(int i) {
            return new ChatMessage[i];
        }
    };

    /*
    * //////////////////////////////////////////////////
    * //getter/setter methods below
    * /////////////////////////////////////////////////
    */
    /*public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }*/


}
