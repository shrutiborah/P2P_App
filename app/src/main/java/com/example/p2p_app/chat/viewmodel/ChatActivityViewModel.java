package com.example.p2p_app.chat.viewmodel;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.p2p_app.chat.models.Chat;
import com.example.p2p_app.chat.repositories.ChatRepository;

import java.util.List;

public class ChatActivityViewModel extends ViewModel {

    String TAG = "Chat Activity View Model";

    private MutableLiveData<List<Chat>> chatItems;
    private ChatRepository chatRepository;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public void init() {
        if (chatItems != null) {
            return;
        }
        chatRepository = ChatRepository.getInstance();
        chatItems = chatRepository.getChatItems();
        Log.e(TAG + " init ", chatItems.toString());
    }

    public void queryRepo() {
        chatRepository = ChatRepository.getInstance();
        chatItems = chatRepository.getChatItems();
    }

    public LiveData<List<Chat>> getChatItems(){
        Log.e(TAG + " getChatItems: ", chatItems.toString());
        return chatItems;
    }
    public LiveData<Boolean> getIsUpdating(){
        return isUpdating;
    }
}
