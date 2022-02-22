package com.example.p2p_app.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.example.p2p_app.R;
import com.example.p2p_app.chat.adapters.MessageAdapter;
import com.example.p2p_app.chat.models.Message;
import com.example.p2p_app.chat.viewmodel.MessageActivityViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

public class MessageActivity extends AppCompatActivity {

    private static final String TAG = "MESSAGE ACTIVITY";

    private MessageActivityViewModel messageActivityViewModel;
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private ProgressBar progressBar;
    Intent intent;
    AppCompatImageButton appCompatImageButton;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);
        Log.e(TAG, "Starting WebSocketService");

        recyclerView = findViewById(R.id.chatRecyclerView);
        progressBar = findViewById(R.id.progressBar);
        editText = findViewById(R.id.inputMessage);
        appCompatImageButton = findViewById(R.id.send);
        intent = getIntent();
        int id  = intent.getIntExtra("id", 100);
        joinChat(id);
        Log.e(TAG, "Intent Extra Value (User ID): " + (String.valueOf(id)));
        progressBar.setVisibility(View.VISIBLE);

        messageActivityViewModel = new ViewModelProvider(this).get(MessageActivityViewModel.class);
        messageActivityViewModel.init(id);
        messageActivityViewModel.getMessageItems().observe(this, new Observer<List<Message>>() {
          @Override
          public void onChanged(List<Message> messages) {
              messageAdapter.notifyDataSetChanged();
          }
        });

        messageActivityViewModel.getIsUpdating().observe( this, new Observer<Boolean>() {

          @Override
          public void onChanged(Boolean aBoolean) {
              if (aBoolean) {
                  progressBar.setVisibility(View.VISIBLE);
              }
              else {
                  progressBar.setVisibility(View.GONE);
                  recyclerView.smoothScrollToPosition(messageActivityViewModel.getMessageItems().getValue().size() - 1);
                  messageAdapter.notifyDataSetChanged();
              }
          }
        });

        initRecyclerView();

        appCompatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(id);
            }
        });
    }

    private void sendMessage(int id) {
        if (editText.getText() != null) {
            String message = editText.getText().toString();
            JSONObject jsonMessageItem = new JSONObject();
            try {
                jsonMessageItem.put("message", message);
                jsonMessageItem.put("receiver", id);
                //TODO: Attachments??
            } catch (JSONException e) {
                e.printStackTrace();
            }
            WebSocketService.getWebSocket().send(String.valueOf(jsonMessageItem));
        }
        editText.setText(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(messageActivityViewModel.getMessageItems().getValue()).clear();
    }

    private void initRecyclerView() {
        Log.e(TAG, "Init Adapter");
        messageAdapter = new MessageAdapter(this, messageActivityViewModel.getMessageItems().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messageAdapter);
    }

    private void joinChat(Integer receiver) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("command", "join_chat");
            jsonObject.put("user_id", receiver);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e(TAG, WebSocketService.getWebSocket() == null? "web socket at joinchat is null" : "web socket at joinchat is not null");

        WebSocketService.getWebSocket().send(String.valueOf(jsonObject));
    }


}