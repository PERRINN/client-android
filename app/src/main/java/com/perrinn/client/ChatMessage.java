package com.perrinn.client;

/**
 * Created by Antreas Christofi on 07-04-2016.
 */
public class ChatMessage {
    private long id;
    private boolean isMe;
    private String message;
    private Long userId;
    private String dateTime;
    private long seq_no;//for security against replay attacks in case of MITM


    //getter methods

    public long getId() {
        return id;
    }

    public long getSeq_no(){ return seq_no;}

    public boolean getIsme() {
        return isMe;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public long getUserId() {
        return userId;
    }

    public String getDate() {
        return dateTime;
    }

    //setter methods

    public void setMe(boolean isMe) {
        this.isMe = isMe;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setDate(String dateTime) {
        this.dateTime = dateTime;
    }
}
