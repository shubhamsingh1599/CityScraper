package com.example.android.cityscraper;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import androidx.annotation.NonNull;

//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder> {

    Context context;
    List<Message> messages;
    DatabaseReference messageDb;


    public MessageAdapter(ChatThreads context, List<Message> messages, DatabaseReference messagedb) {
        this.context=context;
        this.messageDb=messageDb;
        this.messages=messages;
    }

    @NonNull
    @Override
    public MessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_message,viewGroup,false);
        return new MessageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapterViewHolder holder, int i) {
        Message message=messages.get(i);
        if(AllMethods.name.equals(message.getName()))
        {
            holder.tvTitle.setText("You: "+message.getMessage());
            holder.tvTitle.setGravity(Gravity.START);
            holder.l1.setBackgroundColor(Color.parseColor("#F0F0F0"));
        }
        else
        {
            holder.tvTitle.setText(message.getName()+": "+message.getMessage());
            holder.ibDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageButton ibDelete;
        LinearLayout l1;

        public MessageAdapterViewHolder(View itemView)
        {
            super(itemView);
            tvTitle=(TextView)itemView.findViewById(R.id.tvTitle);
            ibDelete=(ImageButton)itemView.findViewById(R.id.btnDelete);
            l1=(LinearLayout)itemView.findViewById(R.id.l1Message);

            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    messageDb.child(messages.get(getAdapterPosition()).getKey()).removeValue();
                }
            });
        }
    }
}

