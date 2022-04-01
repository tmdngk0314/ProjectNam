package com.example.projectnam;

public class Notice {

    String notice;
    String date;

    public Notice(String notice, String date) {
        this.notice = notice;
        this.date = date;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
