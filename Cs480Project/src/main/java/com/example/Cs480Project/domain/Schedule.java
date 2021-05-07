package com.example.Cs480Project.domain;

public class Schedule {
    String time;
    String day;
    String month;
    public Schedule(){

    }

    public Schedule(String time, String day, String month) {
        this.time = time;
        this.day = day;
        this.month = month;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
