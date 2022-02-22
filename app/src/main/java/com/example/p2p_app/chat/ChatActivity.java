package com.example.p2p_app.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.p2p_app.R;
import com.example.p2p_app.chat.adapters.ChatAdapter;
import com.example.p2p_app.chat.adapters.ClickListener;
import com.example.p2p_app.chat.data.MyProfile;
import com.example.p2p_app.chat.models.Chat;
import com.example.p2p_app.chat.models.ChatUser;
import com.example.p2p_app.chat.viewmodel.ChatActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "Chat Activity";

    private ChatActivityViewModel chatActivityViewModel;
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        getApplicationContext().startService(new Intent(ChatActivity.this, WebSocketService.class));

        recyclerView = findViewById(R.id.chatRecyclerview);
        progressBar = findViewById(R.id.progress_bar);
        FloatingActionButton mFab = findViewById(R.id.fab);

        MyProfile.getInstance(); //TODO EFFICIENT?


        chatActivityViewModel = new ViewModelProvider(this).get(ChatActivityViewModel.class);
        chatActivityViewModel.init();

        chatActivityViewModel.getChatItems().observe(this, new Observer<List<Chat>>() {
            @Override
            public void onChanged(List<Chat> chats) {
                chatAdapter.notifyDataSetChanged();

            }
        });

        chatActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    showProgressBar();
                }
                else {
                    hideProgressBar();
                    recyclerView.smoothScrollToPosition(
                            chatActivityViewModel.getChatItems().getValue()  == null ? 0
                                    : chatActivityViewModel.getChatItems().getValue().size() - 1);
                }
            }
        });

        initRecyclerview();

        chatAdapter.setOnItemClickListener(new ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                int id  = chatAdapter.getItem(position).getUser().getId();
                Toast.makeText(ChatActivity.this, "UserID: "+ String.valueOf(id), Toast.LENGTH_LONG).show();
                startActivity( new Intent(ChatActivity.this, MessageActivity.class).putExtra("id", id));
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, String.valueOf(chatAdapter.getItemCount()));
                chatAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApplicationContext().stopService(new Intent(ChatActivity.this, WebSocketService.class));
    }

    private void initRecyclerview(){
        Log.e(TAG, "initrecyclerview");
        chatAdapter =  new ChatAdapter(this, chatActivityViewModel.getChatItems().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatAdapter);

    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}