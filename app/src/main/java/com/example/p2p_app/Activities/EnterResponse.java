package com.example.p2p_app.Activities;

import java.io.Serializable;

public class EnterResponse implements Serializable {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
