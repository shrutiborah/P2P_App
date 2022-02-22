package com.example.p2p_app.chat.viewmodel;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.p2p_app.chat.models.Message;
import com.example.p2p_app.chat.repositories.MessageRepository;

import java.util.List;
import java.util.Objects;

public class MessageActivityViewModel  extends ViewModel {
    String TAG = "MESSAGE ACTIVITY VIEW MODEL: ";

    private MutableLiveData<List<Message>> messageItems;
    public static MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();
    int id;


    public void init(int id) {
        isUpdating.setValue(true);
        if (messageItems == null) {
            this.id = id;
        }
        else {
            Objects.requireNonNull(messageItems.getValue()).clear();
        }
        messageItems =  MessageRepository.getInstance().getMessageList(this.id);
    }

    public LiveData<List<Message>> getMessageItems() {
        return messageItems;
    }

    public LiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }

    public void reload() {
        messageItems =  MessageRepository.getInstance().getMessageList(this.id);
        Log.e(TAG, "Reloaded");
    }

    @SuppressLint("StaticFieldLeak")
    public void loadMessageItems(final int id) {
        // Do an asynchronous operation to fetch data.
        isUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Message> messages = MessageRepository.getInstance().getMessageList(id).getValue();
                if (messageItems.getValue() == null) {
                } else {
                    messageItems.getValue().clear();
                }
                messageItems.postValue(messages);
                isUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

    }
}
