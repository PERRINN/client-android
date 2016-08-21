package com.perrinn.client.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alessandrosilacci on 21/08/16.
 */
public class Member implements Parcelable {
    private String name;
    private int profilePicture;

    public Member(String name, int profilePicture) {
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public Member(Parcel source) {
        name = source.readString();
        profilePicture = source.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }


    @Override
    public int describeContents() {
        return 2;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(profilePicture);
    }

    public static final Parcelable.Creator<Member> CREATOR = new Parcelable.Creator<Member>(){

        @Override
        public Member createFromParcel(Parcel source) {
            return new Member(source);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };
}
