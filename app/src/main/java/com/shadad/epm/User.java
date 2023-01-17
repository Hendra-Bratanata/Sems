package com.shadad.epm;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    String email;
    String pass;
    String status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User(JSONObject obj) {

        try {
            String email= obj.getString("EMAIL");
            String pass = obj.getString("PASS");
            String status = obj.getString("STATUS");
            this.email = email;
            this.pass = pass;
            this.status = status;

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
