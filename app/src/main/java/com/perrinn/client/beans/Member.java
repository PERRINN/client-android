package com.perrinn.client.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alessandrosilacci on 21/08/16.
 */
public class Member implements Parcelable {
    private String firstName;
    private String lastName;
    private int profilePicture;

    public Member(String firstName, String lastName, int profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
    }

    public Member(Parcel source) {
        firstName = source.readString();
        lastName = source.readString();
        profilePicture = source.readInt();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        dest.writeString(firstName);
        dest.writeString(lastName);
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
