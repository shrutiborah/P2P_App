package com.example.p2p_app.chat.repositories;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.p2p_app.Activities.LoginActivity;
import com.example.p2p_app.chat.models.Chat;
import com.example.p2p_app.chat.models.Profile;
import com.example.p2p_app.utility.RetrofitClient;
import com.example.p2p_app.utility.SessionManager;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;


public class ChatRepository {
    public final String TAG = "CHAT REPOSITORY";

    private static ChatRepository instance;
    List<Chat> chatList = new ArrayList<>();
    Profile profile;

    private ChatRepository(){
    }

    public static ChatRepository getInstance() {
        if (instance == null){
            synchronized (ChatRepository.class) {
                if (instance == null)
                    instance = new ChatRepository();
            }
        }
        return instance;
    }

    public MutableLiveData<List<Chat>> getChatItems() {
        loadChatList();
        MutableLiveData<List<Chat>> data = new MutableLiveData<>();
        data.setValue(chatList); //TODO: Please set
        return data;
    }

    private void loadChatList () {
        Log.e(TAG, "reached loadChatList()");
        final SessionManager sessionManager = new SessionManager(LoginActivity.getContext());
        String token = sessionManager.fetchAccessToken();
        RetrofitClient.getInstance().getService().chat("Bearer " + token).enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, retrofit2.Response<List<Chat>> response) {
                Log.e("Response", response.body() != null ? response.body().toString() : "null");
                if (response.isSuccessful()) {
                    chatList.clear();
                    chatList.addAll(response.body());
                    Log.e("loadChat", "Chat loaded");
                } else {
                    Log.e("loadChat", "Oops! something went wrong while loading the Chat");
                }
            }
            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                Log.e("TAG", "Failed");
            }
        });
    }

    public Profile getProfile(int userId) {
        Log.e(TAG, "reached loadChatList()");
        final SessionManager sessionManager = new SessionManager(LoginActivity.getContext());
        String token = sessionManager.fetchAccessToken();
        RetrofitClient.getInstance().getService().getProfile(userId,"Bearer " + token).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, retrofit2.Response<Profile> response) {
                Log.e("Response", response.body() != null ? response.body().toString() : "null");
                if (response.isSuccessful()) {
                    profile = response.body();
                }
            }
            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.e("TAG", "Failed");
            }
        });
        return profile;
    }
}
