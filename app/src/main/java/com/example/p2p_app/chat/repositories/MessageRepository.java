package com.example.p2p_app.chat.repositories;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.p2p_app.Activities.LoginActivity;
import com.example.p2p_app.chat.models.Message;
import com.example.p2p_app.chat.models.PagedMessage;
import com.example.p2p_app.chat.viewmodel.MessageActivityViewModel;
import com.example.p2p_app.utility.RetrofitClient;
import com.example.p2p_app.utility.SessionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MessageRepository {
    public final String TAG = "MESSAGE REPOSITORY: ";

    private static MessageRepository instance;
    private List<Message> messageList = new ArrayList<>();
    private int id;

    MessageReceiver messageReceiver = null;

    private MessageRepository(){
        doRegisterReceiver();
        Log.e(TAG, "initialized");
    };

    public static MessageRepository getInstance() {
        if (instance == null) {
            synchronized (MessageRepository.class) {
                if (instance == null)
                    instance = new MessageRepository();
            }
        }
        return instance;
    }

    public MutableLiveData<List<Message>> getMessageList( int id) {
        this.id = id;
        loadMessageList();
        MutableLiveData<List<Message>> data = new MutableLiveData<>();
        data.setValue(messageList);
        return data;
    }

    private void loadMessageList() {
        Log.e(TAG, "reached loadChatList()");
        final SessionManager sessionManager = new SessionManager(LoginActivity.getContext());
        String token = sessionManager.fetchAccessToken();
        RetrofitClient.getInstance().getService().getMessage(id, "Bearer " + token).enqueue(new Callback<List<Message>>() { //TODO: SPECIFY RECEIVER ID
            @Override
            public void onResponse(Call<List<Message>> call, retrofit2.Response<List<Message>> response) {
                Log.e(TAG, response.body() != null ? " RESPONSE : " + response.body().toString() : "null");
                if (response.isSuccessful()) {
                    messageList.clear(); //TODO: EFFICIENT?
//                    messageList = response.body();
                    messageList.addAll(response.body());
                    Collections.reverse(messageList);
                    MessageActivityViewModel.isUpdating.setValue(false);
                    //casted?? response model is same as json structure?
                    Log.e(TAG, "Chat loaded");
                } else {
                    Log.e("loadChat", "Oops! something went wrong while loading the Chat");
                }
            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.e("TAG", "Failed");
            }
        });
    }

    private void doRegisterReceiver() {
        Log.e(TAG, "Registering Receiver");
        messageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter("message signal");
        LoginActivity.getContext().registerReceiver(messageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "Got signal at:  MessageReceiver extends BroadcastReceiver");
            if ("message".equals(intent.getSerializableExtra("Message"))) {
                MessageActivityViewModel.isUpdating.setValue(true);
                getMessageSignaled();
            }
        }
    }

    private void getMessageSignaled() {
        final SessionManager sessionManager = new SessionManager(LoginActivity.getContext());
        String token = sessionManager.fetchAccessToken();
        RetrofitClient.getInstance().getService().getPagedMessage(id,1, 0 ,"Bearer " + token).enqueue(new Callback<PagedMessage>() {
            @Override
            public void onResponse(Call<PagedMessage> call, retrofit2.Response<PagedMessage> response) {
                Log.e(TAG, response.body() != null ? " RESPONSE : " + response.body().toString() : "null");
                if (response.isSuccessful()) {
                    Message message = response.body().getMessageList().get(0);
                    messageList.add(message);
                    MessageActivityViewModel.isUpdating.setValue(false);
                    Log.e(TAG, "Chat loaded");
                } else {
                    Log.e("loadChat", "Oops! something went wrong while loading the Chat");
                }
            }
            @Override
            public void onFailure(Call<PagedMessage> call, Throwable t) {
                Log.e("TAG", "Failed, Message: " + t.getMessage());
            }
        });
    }













}
