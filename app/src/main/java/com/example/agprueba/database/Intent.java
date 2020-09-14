package com.example.agprueba.database;

public class Intent {

    private  String date;
    private  String estate;

    public Intent() {
    }

    public Intent(String date, String estate) {
        this.date = date;
        this.estate = estate;
    }

    public  String getDate() {
        return date;
    }

    public  void setDate(String date) {
        this.date = date;
    }

    public  String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }
}
