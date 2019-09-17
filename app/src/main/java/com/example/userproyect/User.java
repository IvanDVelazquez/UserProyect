package com.example.userproyect;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private Name name;
    @SerializedName("picture")
    private Picture picture;
    @SerializedName("location")
    private Location location;

    public User(String email, Name name, Picture picture, Location location) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
