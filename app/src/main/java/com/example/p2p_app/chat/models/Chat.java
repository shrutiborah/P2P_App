package com.example.p2p_app.chat.models;

import com.google.gson.annotations.SerializedName;

public class Chat {
    @SerializedName("user")
    private ChatUser user;

    @SerializedName("unread_messages")
    private int unread_messages;

    @SerializedName("last_message")
    private LastMessage last_message;

    public User gettUser() {
        return (User) user;
    }

    public ChatUser getUser() {
        return user;
    }

    public void setUser(ChatUser user) {
        this.user = user;
    }

//        public ChatUser getUser() {
//        return (User) user;
//    }

    public int getUnread_messages() {
        return unread_messages;
    }

    public LastMessage getLast_message() {
        return last_message;
    }

//    public void setUser(ChatUser user) {
//        this.user = user;
//    }

    public void setUnread_messages(int unread_messages) {
        this.unread_messages = unread_messages;
    }

    public void setLast_message(LastMessage last_message) {
        this.last_message = last_message;
    }

}

