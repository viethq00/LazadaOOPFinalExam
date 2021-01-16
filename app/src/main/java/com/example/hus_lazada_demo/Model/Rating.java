package com.example.hus_lazada_demo.Model;
public class Rating {
    private String userPhone, pid, rateValue, comment;

    public Rating() {

    }

    public Rating(String userPhone, String pid, String rateValue, String comment) {
        this.userPhone = userPhone;
        this.pid = pid;
        this.rateValue = rateValue;
        this.comment = comment;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRateValue() {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
