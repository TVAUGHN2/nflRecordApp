package edu.depaul.tvaughn2.tvaughn2nflrecordapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Travis on 10/23/2016.
 */

public class Team implements Parcelable{

    enum Type {NFC, AFC}

    String name;
    Type type;
    String shortDescription;
    String longDescription;
    float rating = 4.0f;

    public Team(String name, Type type, String shortDescription, String longDescription) {
        this.name = name;
        this.type = type;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {this.shortDescription = shortDescription;}

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {this.longDescription = longDescription;}

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String toString() {return name;}

    public static int getIconResource(Type type) {
        switch (type) {
            case NFC:
                return R.drawable.nfc3_logo; //R.drawable.NFC;
            case AFC:
                return R.drawable.afc2_logo; //R.drawable.AFC;
        }
        return -1;
    }

    private Team(Parcel in) {
        name = in.readString();
        type = Type.values()[in.readInt()];
        shortDescription = in.readString();
        longDescription = in.readString();
        rating = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(type.ordinal());
        out.writeString(shortDescription);
        out.writeString(longDescription);
        out.writeFloat(rating);
    }

    public static final Parcelable.Creator<Team> CREATOR
            = new Parcelable.Creator<Team>() {
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

}
