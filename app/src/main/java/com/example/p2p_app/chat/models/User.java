package com.example.p2p_app.chat.models;

import android.widget.ImageView;

public class User extends ChatUser {
//    private int id;
//    private String name;
//    private String email;
    private ImageView profileImage;

    public User(int id) {
        super(id);
    }

//    public User(int id) {
//        super.setId(id);
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getEmail() {
//        return email;
//    }

    public ImageView getProfileImage() {
        return profileImage;
    }

//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public void setProfileImage(ImageView profileImage) {
        this.profileImage = profileImage;
    }
}