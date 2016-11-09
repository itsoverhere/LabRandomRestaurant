package edu.dlsu.mobidev.labrandomrestaurant;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by courtneyngo on 10/15/16.
 */
public class Restaurant implements Parcelable{

    public static final String RESTAURANT = "restaurant";
    public static final String NAME = "name";
    public static final String WEIGHT = "weight";

    private String name;
    private int weight;
    private int listPosition;

    public Restaurant(){}

    public Restaurant(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Restaurant(String name, int weight, int listPosition) {
        this.name = name;
        this.weight = weight;
        this.listPosition = listPosition;
    }

    protected Restaurant(Parcel in) {
        name = in.readString();
        weight = in.readInt();
        listPosition = in.readInt();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(weight);
        dest.writeInt(listPosition);
    }

    public int getListPosition() {
        return listPosition;
    }

    public void setListPosition(int listPosition) {
        this.listPosition = listPosition;
    }
}
