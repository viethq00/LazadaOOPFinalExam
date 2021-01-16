package com.example.hus_lazada_demo.Model;

public class History {
    private String address;
    private String city;
    private String date;
    private String name;
    private String phoneOrder;
    private static String state;
    private String time;
    private String totalAmount;

    public History(String address, String city, String date, String name, String phoneOrder, String state, String time, String totalAmount) {
        this.address = address;
        this.city = city;
        this.date = date;
        this.name = name;
        this.phoneOrder = phoneOrder;
        this.state = state;
        this.time = time;
        this.totalAmount = totalAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneOrder() {
        return phoneOrder;
    }

    public void setPhoneOrder(String phoneOrder) {
        this.phoneOrder = phoneOrder;
    }

    public static String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    public History(){

    }
}
