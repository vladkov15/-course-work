package com.example.client.Models;

import com.google.gson.annotations.SerializedName;

public class ProducerPost {
    @SerializedName("name")
    public String name;
    @SerializedName("place")
    public String place;

    public ProducerPost(String name, String place) {
        this.name = name;
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
