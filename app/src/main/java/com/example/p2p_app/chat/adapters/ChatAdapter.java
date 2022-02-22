package com.example.p2p_app.chat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.p2p_app.R;
import com.example.p2p_app.chat.models.Chat;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static ClickListener clickListener;
    private final List<Chat> chatItems;
    private final Context context;


    public ChatAdapter(Context context, List<Chat> chatItems) {
        this.chatItems = chatItems;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        RequestOptions defaultOptions = new RequestOptions().error(R.color.yellow);
        String email = chatItems.get(position).getUser().getEmail() == null ? "Unknown User" : chatItems.get(position).getUser().getEmail();
        String userName = email.substring(0, email.indexOf("@"));


        ((ViewHolder) holder).nameTextView.setText(userName);
        ((ViewHolder) holder).setUserid(chatItems.get(position).getUser().getId());


        Glide.with(context)
                .setDefaultRequestOptions(defaultOptions)
                .load(R.drawable.ic_baseline_person_24)
                .circleCrop()
                .into(((ViewHolder) holder).profileImageView);

    }

    @Override
    public int getItemCount() {
        return chatItems == null? 0: chatItems.size();
    }

    public Chat getItem(int position) {
        return chatItems.get(position);
    }


public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

    private final TextView nameTextView;
    private final ImageView profileImageView;
    private int userid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public ViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        profileImageView = (ImageView) itemView.findViewById(R.id.profileImageView);
    }

    @Override
    public void onClick(View view) {
        clickListener.onItemClick(getAdapterPosition(), view);

    }
}
    public void setOnItemClickListener(ClickListener clickListener) {
        ChatAdapter.clickListener = clickListener;
    }

//    public interface ClickListener {
//        void onItemClick(int position, View v);
//    }
}