package com.example.mindvaccine_demo;

public class ChatObject {
    private Boolean isleft;
    private String chat;


    public ChatObject(Boolean isleft, String chat) {
        this.isleft = isleft;
        this.chat = chat;
    }

    public Boolean getType() {
        return isleft;
    }

    public void setType(String type) {
        this.isleft = isleft;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}
