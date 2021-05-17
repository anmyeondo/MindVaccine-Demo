package com.example.mindvaccine_demo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class ChatbotFragment extends Fragment {
    RecyclerView rv;
    ChatAdapter adapter;
    Button btnAddChat, btnM2E;
    EditText editText;
    boolean isLast = false;

    int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatbot, container, false);
        
        // 채팅 뷰 설정
        editText = view.findViewById(R.id.edit_text);

        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ChatAdapter(new ArrayList<ChatObject>());
        rv.setAdapter(adapter);

        btnAddChat = view.findViewById(R.id.btn_add);
        btnM2E = view.findViewById(R.id.btn_mte);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int last = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int total= recyclerView.getAdapter().getItemCount() - 1;

                isLast = (last == total);
                if (isLast) {
                    btnM2E.setVisibility(View.GONE);
                } else {
                    btnM2E.setVisibility(View.VISIBLE);
                }
            }
        });
        btnAddChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.putChat(new ChatObject("seed", editText.getText().toString()));
                if (isLast) {
                    rv.smoothScrollToPosition(adapter.getItemCount());
                }
            }
        });

        btnM2E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv.smoothScrollToPosition(adapter.getItemCount());
            }
        });

        return view;
    }

    // 테스트용
    public String getChatting(){
        return "보낼 채팅";
    }
}