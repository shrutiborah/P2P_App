package com.example.p2p_app.chat.models;

import java.io.Serializable;
import java.util.List;

public class MessageItem implements Serializable {

    public Integer id;
    public ChatUser sender;
    public ChatUser receiver;
    public String message;
    public String datetime;
    public Boolean read;
    public List<Integer> attachments = null; //int id. object?

    public MessageItem(ChatUser sender, ChatUser receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }
}