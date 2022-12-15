package com.example.client.Models;

import com.google.gson.annotations.SerializedName;

public class Producer {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("place")
    public String place;

    public Producer(int id, String name, String place) {
        this.id = id;
        this.name = name;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
