package com.example.p2p_app.chat.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.p2p_app.R;
import com.example.p2p_app.chat.data.MyProfile;
import com.example.p2p_app.chat.models.Message;
import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static ClickListener clickListener;
    private  List<Message> messageItems = new ArrayList<>();
    private final Context context;
    String TAG = "MessageAdapter ";
    public MessageAdapter(Context context, List<Message> messageItems) {
        this.messageItems = messageItems;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e(TAG, "MyProfile.getInstance().getId(): " + String.valueOf(MyProfile.getInstance().getId()) );

        Log.e(TAG, "Message Item: " + messageItems.get(position).getMessage());
        Log.e(TAG, "Message Item: " + messageItems.get(position).getAttachments());
        Log.e(TAG, "Message Item: " + messageItems.get(position).getDatetime());
        Log.e(TAG, "Message Item: " + messageItems.get(position).getReceiver().getId());

        if(messageItems.get(position).getReceiver().getId() == MyProfile.getInstance().getId()) {
            ((ViewHolder)holder).sentMessageCardView.setVisibility(View.GONE);
            ((ViewHolder)holder).receivedMessageCardView.setVisibility(View.VISIBLE);
            ((ViewHolder)holder).receivedMessageTextView.setText(messageItems.get(position).getMessage());
            ((ViewHolder)holder).receivedDateTimeTextView.setText(messageItems.get(position).getDatetime());
            if ((messageItems.get(position).getAttachments()).size() != 0) {
                ((ViewHolder)holder).receivedAttachmentTitleTextView.setText(messageItems.get(position).getAttachments().get(0).getFile());
            } else {
                ((ViewHolder)holder).receivedAttachmentTitleTextView.setVisibility(View.GONE);
                ((ViewHolder)holder).receivedAttachmentImageView.setVisibility(View.GONE);
                ((ViewHolder)holder).sentAttachmentTitleTextView.setVisibility(View.VISIBLE);
                ((ViewHolder)holder).sentAttachmentImageView.setVisibility(View.VISIBLE);
            }


        } else {
            ((ViewHolder)holder).receivedMessageCardView.setVisibility(View.GONE);
            ((ViewHolder)holder).sentMessageCardView.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).sentMessageTextView.setText(messageItems.get(position).getMessage());
            ((ViewHolder)holder).sentDateTimeTextView.setText(messageItems.get(position).getDatetime());
            if((messageItems.get(position).getAttachments()).size() != 0){
                ((ViewHolder)holder).sentAttachmentTitleTextView.setText(messageItems.get(position).getAttachments().get(0).getFile());
            }
            else {
               ((ViewHolder)holder).sentAttachmentTitleTextView.setVisibility(View.GONE);
               ((ViewHolder)holder).sentAttachmentImageView.setVisibility(View.GONE);
                ((ViewHolder)holder).receivedAttachmentTitleTextView.setVisibility(View.VISIBLE);
                ((ViewHolder)holder).receivedAttachmentImageView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return messageItems == null? 0 : messageItems.size();
//        return messageItems.size();
    }

    public Message getItem(int position) {
        return messageItems.get(position);
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private CardView receivedMessageCardView, sentMessageCardView;
        private TextView receivedMessageTextView, sentMessageTextView, sentDateTimeTextView, receivedDateTimeTextView, sentAttachmentTitleTextView, receivedAttachmentTitleTextView;
        ImageView sentAttachmentImageView, receivedAttachmentImageView;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.receivedMessageCardView = itemView.findViewById(R.id.receive_card_view);
            this.sentMessageCardView = itemView.findViewById(R.id.send_card_view);
            this.receivedMessageTextView = itemView.findViewById(R.id.receive_text_view);
            this.sentMessageTextView = itemView.findViewById(R.id.send_text_view);
            this.sentDateTimeTextView = itemView.findViewById(R.id.send_date_time_text_view);
            this.receivedDateTimeTextView = itemView.findViewById(R.id.received_date_time_text_view);
            this.sentAttachmentTitleTextView = itemView.findViewById(R.id.send_attachment_text_view);
            this.receivedAttachmentTitleTextView = itemView.findViewById(R.id.received_attachment_text_view);
            this.sentAttachmentImageView = itemView.findViewById(R.id.sent_attachment_image_view);
            this.receivedAttachmentImageView = itemView.findViewById(R.id.received_attachment_image_view);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        MessageAdapter.clickListener = clickListener;
    }
}
