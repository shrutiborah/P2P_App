package com.example.p2p_app.Activities;

import java.io.Serializable;

public class ResetpassResponse implements Serializable {
    private String new_password1,new_password2;

    public String getNew_password1() {
        return new_password1;
    }

    public void setNew_password1(String new_password1) {
        this.new_password1 = new_password1;
    }

    public String getNew_password2() {
        return new_password2;
    }

    public void setNew_password2(String new_password2) {
        this.new_password2 = new_password2;
    }
}
