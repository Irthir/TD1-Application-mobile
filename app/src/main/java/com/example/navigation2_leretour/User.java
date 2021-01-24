package com.example.navigation2_leretour;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String sNom;
    private String sPrenom;

    public User(String sNom,String sPrenom)
    {
        super();
        this.sNom=sNom;
        this.sPrenom=sPrenom;
    }

    protected User(Parcel in) {
        sNom = in.readString();
        sPrenom = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sNom);
        dest.writeString(sPrenom);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getNom()
    {
        return sNom;
    }

    public String getPrenom()
    {
        return sPrenom;
    }

    public void setNom(String sNom)
    {
        this.sNom = sNom;
    }

    public void setsPrenom(String sPrenom)
    {
        this.sPrenom = sPrenom;
    }
}
