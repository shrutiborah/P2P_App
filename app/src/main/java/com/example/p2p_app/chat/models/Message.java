package com.example.p2p_app.chat.models;

import java.util.List;

public class Message {
    private int id;
    private ChatUser sender;
    private ChatUser receiver;
    private String message;
    private String datetime;
    private boolean read;
    private List<Attachments> attachments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChatUser getSender() {
        return sender;
    }

    public void setSender(ChatUser sender) {
        this.sender = sender;
    }

    public ChatUser getReceiver() {
        return receiver;
    }

    public void setReceiver(ChatUser receiver) {
        this.receiver = receiver;
    }

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

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public List<Attachments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachments> attachments) {
        this.attachments = attachments;
    }
}
