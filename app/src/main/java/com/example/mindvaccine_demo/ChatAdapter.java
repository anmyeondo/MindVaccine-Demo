package com.example.mindvaccine_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    ArrayList<ChatObject> list;

    public ChatAdapter(ArrayList<ChatObject> list) {
        this.list = list;
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChatHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        final ChatObject co = this.list.get(position);
//        holder.name.setText(co.getName());
        holder.message.setText(co.getChat());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ChatHolder extends RecyclerView.ViewHolder{

        public TextView name, message;

        public ChatHolder(View itemView) {
            super(itemView);
//            this.name = itemView.findViewById(R.id.ci_tv_name);
            this.message = itemView.findViewById(R.id.ci_tv_message);
        }
    }

    public void putChat(ChatObject co){
        this.list.add(co);
        notifyDataSetChanged();
    }

    public void refresh(ArrayList<ChatObject> list2){
        this.list = list2;
        notifyDataSetChanged();
    }
}
