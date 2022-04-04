package com.example.ideaflower.classs;

public class vote {
    private String email;
    private String content;

    public vote(String email, String content) {
        this.email = email;
        this.content = content;
    }

    public vote() {
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
