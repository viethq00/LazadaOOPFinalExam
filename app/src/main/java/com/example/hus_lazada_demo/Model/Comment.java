package com.example.hus_lazada_demo.Model;

public class Comment {
    private String comment;
    private String userPhone;

    public Comment() {

    }

    public Comment(String comment, String userPhone) {
        this.comment = comment;
        this.userPhone = userPhone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
