package com.perrinn.client.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by alessandrosilacci on 21/08/16.
 */
public class Team implements Parcelable{
    private String name;
    private String description;
    private int bgres;
    private ArrayList<Member> members;
    private boolean selected;

    public Team(String name, String description, int bgres, ArrayList<Member> members, boolean selected) {
        this.name = name;
        this.description = description;
        this.bgres = bgres;
        this.members = members;
        this.selected = selected;
    }

    public Team(Parcel source) {
        name = source.readString();
        description = source.readString();
        bgres = source.readInt();
        boolean[] s = new boolean[1];
        source.readBooleanArray(s);
        selected = s[0];
        if(source.dataSize() > 4){
            Parcelable[] parc = source.readParcelableArray(Member.class.getClassLoader());
            Member[] m = null;
            if(parc != null){
                m = Arrays.copyOf(parc,parc.length,Member[].class);
                members = new ArrayList<Member>();
                for(Member me : m){
                    members.add(me);
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getBgres() {
        return bgres;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBgres(int bgres) {
        this.bgres = bgres;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int describeContents() {
        return 4+(members != null ? members.size():0);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(bgres);
        dest.writeBooleanArray(new boolean[] {selected});
        Member[] m = new Member[members.size()];
        dest.writeParcelableArray(members.toArray(m),0);
    }

    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>(){

        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
