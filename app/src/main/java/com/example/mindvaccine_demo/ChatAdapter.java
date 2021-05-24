package com.example.mindvaccine_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        return new ChatHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_bot, parent, false));
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        final ChatObject co = this.list.get(position);

        if (co.getType()) {
            // left
            holder.le_m.setText(co.getChat());
            holder.ri.setVisibility(View.GONE);
        } else {
            // right
            holder.ri_m.setText(co.getChat());
            holder.le.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public static class ChatHolder extends RecyclerView.ViewHolder{

        public LinearLayout le, ri;
        public TextView le_m, ri_m;

        public ChatHolder(View itemView) {
            super(itemView);
            this.le_m = itemView.findViewById(R.id.come);
            this.ri_m = itemView.findViewById(R.id.out);
            this.le = itemView.findViewById(R.id.le);
            this.ri = itemView.findViewById(R.id.ri);
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
