package com.example.p2p_app.chat.models;

public class LastMessage {
    private String message;
    private String datetime;
    private ChatUser sender;
    private ChatUser receiver;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public ChatUser getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public ChatUser getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
