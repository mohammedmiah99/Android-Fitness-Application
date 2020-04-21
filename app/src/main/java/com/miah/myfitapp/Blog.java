package com.miah.myfitapp;

public class Blog {
    String blogId;
    String note;
    String name;
    String email, title;


    public Blog(){
    }

    public Blog(String blogId, String note, String name, String email, String title) {
        this.blogId = blogId;
        this.note = note;
        this.name = name;
        this.email = email;
        this.title = title;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
