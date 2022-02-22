package com.example.p2p_app.chat.data;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.p2p_app.Activities.LoginActivity;
import com.example.p2p_app.chat.models.Profile;
import com.example.p2p_app.chat.models.User;
import com.example.p2p_app.utility.RetrofitClient;
import com.example.p2p_app.utility.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfile {

    private static final String TAG = "My Profile: " ;
    private static MyProfile instance;
    private static int id;

    private static  Context context = LoginActivity.getContext();
    private static SessionManager sessionManager = new SessionManager(context);

    private MyProfile() {};

    public static MyProfile getInstance() {
        if (instance == null) {
            synchronized (MyProfile.class) {
                if (instance == null) {
                    new Handler(Looper.getMainLooper()).post( new Runnable() {
                        @Override
                        public void run() {
                            setId();
                        }});
                    instance = new MyProfile();
                }

            }
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    private static void setId() {
        RetrofitClient
                .getInstance()
                .getService()
                .getId("Bearer " + sessionManager.fetchAccessToken())
                .enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if (response.isSuccessful()) {
                            Profile profile = response.body();
                            Log.e(TAG, response.body().toString());
                            id = profile.getId();
                            Log.e(TAG, String.valueOf(profile.getId()));
                        }
                        else {
                            Log.e(TAG, "Response Failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        Log.e(TAG, "Failed at fetching my profile " + "MESSAGE: " + t.getMessage());
                    }
                });
    }
}
