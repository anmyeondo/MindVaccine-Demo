package com.example.mindvaccine_demo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class ChatbotFragment extends Fragment {
    RecyclerView rv;
    ChatAdapter adapter;
    Button btnAddChat, btnM2E;
    EditText editText;
    boolean isLast = false;
    InputMethodManager manager;

    String botTexts[] = {
            "안녕하세요!",
            "오늘 하루는 어떠셨나요?",
            "좋은일만 가득하세요!",
            "사랑합니다!",
            "좋은 글귀가 있어서 가져왔어요.\n" +
                    "걱정말아요. 행복에 예정된 당신인걸요.",
            "좋은 글귀가 있어서 가져왔어요. \n" +
                    "많이 힘들었죠? 괜찮아요. 온 힘을 다했으니까 다 잘될거에요."
    };

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
        manager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);

        manager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ChatAdapter(new ArrayList<ChatObject>());
        rv.setAdapter(adapter);

        btnAddChat = view.findViewById(R.id.btn_add);
        btnM2E = view.findViewById(R.id.btn_mte);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_ENTER:
                        btnAddChat.callOnClick();
                        break;
                    case KeyEvent.KEYCODE_ESCAPE:
                        manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    default:
                        return false;
                }
                return true;
            }
        });

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

        rv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });


        btnAddChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thisText = editText.getText().toString();
                if (thisText != null && !thisText.equals("")) {
                    putChatting(false, thisText);
                    editText.setText("");
                    if (isLast) {
                        rv.smoothScrollToPosition(adapter.getItemCount());
                    }
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

    private void hideKeyboard() {
        if (getActivity() != null && getActivity().getCurrentFocus() != null) {
            manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void putChatting(boolean isChatbot, String text) {
        if (isChatbot) {
            if (text.equals("!글귀")) {
                int range = botTexts.length;
                Random random = new Random();
                int i = random.nextInt(range);
                String t = botTexts[i];
                adapter.putChat(new ChatObject(Boolean.TRUE, t));
            } else {
                adapter.putChat(new ChatObject(Boolean.TRUE, text));
            }
        } else {
            adapter.putChat(new ChatObject(Boolean.FALSE, text));
            if (text.equals("hello")) {
                putChatting(true, "hello!");
            } else {
                putChatting(true, "!글귀");
            }
        }
    }
}