package com.example.projectnam;

public class LockerInfo {
    public String result;
    public String[] lockername;
    public String[] location;


    public LockerInfo(){

        lockername=new String[20];
        location=new String[20];

    }



    public String[] getLockername() {
        return lockername;
    }

    public void setLockername(String[] lockername) {
        this.lockername = lockername;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }




}
