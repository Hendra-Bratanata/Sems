package com.shadad.epm;

import org.json.JSONException;
import org.json.JSONObject;

public class DataItem {
    String id;
    String amper;
    String volt;
    String power;
    String date;
    String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmper() {
        return amper;
    }

    public void setAmper(String amper) {
        this.amper = amper;
    }

    public String getVolt() {
        return volt;
    }

    public void setVolt(String volt) {
        this.volt = volt;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataItem(JSONObject obj) {
        try {
            String id = obj.getString("ID");
            String amper = obj.getString("AMPER");
            String volt = obj.getString("VOLT");
            String power = obj.getString("POWER");
            String date = obj.getString("DATE");
            String time = obj.getString("TIME");
            this.id = id;
            this.amper = amper;
            this.volt = volt;
            this.power = power;
            this.date = date;
            this.time = time;

        }catch (JSONException e){
            e.printStackTrace();
        }

    }



}
