package com.example.client.Models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    public int id;
    @SerializedName("login")
    public String login;
    @SerializedName("password")
    public String password;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
