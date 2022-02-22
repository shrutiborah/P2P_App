package com.example.p2p_app.utility;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.p2p_app.chat.ChatActivity;


public class SessionManager{
    Context context;
    private final SharedPreferences sharedPreferences;
    private static final String ACCESS_TOKEN = "access_token";
    public SessionManager(Context context) {
        this.context =  context;
        sharedPreferences = context.getSharedPreferences("ACCESS_TOKEN", Context.MODE_PRIVATE);
    }


    public void saveAccessToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESS_TOKEN, token);
        editor.apply();
    }
    public String fetchAccessToken(){
        return sharedPreferences.getString(ACCESS_TOKEN, null);
    }

}
