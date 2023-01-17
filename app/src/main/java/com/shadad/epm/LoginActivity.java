package com.shadad.epm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
Button btnDaftar;
Button btnLogin;
EditText edtEmail;
EditText edtPass;
ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnDaftar = findViewById(R.id.btnDaftar);
        btnLogin = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPass =  findViewById(R.id.edtLoginPass);
        loading = findViewById(R.id.loadingLogin);

        loading.setVisibility(View.INVISIBLE);
        btnLogin.setOnClickListener(view -> {
            if(TextUtils.isEmpty(edtEmail.getText())){
                edtEmail.setError("Wajib di isi");
            }
            if(TextUtils.isEmpty(edtPass.getText())){
                edtPass.setError("Wajib di Isi");
            }
            else {
                loading.setVisibility(View.VISIBLE);
                btnDaftar.setClickable(false);
                btnLogin.setClickable(false);
                String email =  edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                cekData(email, pass);
            }
        });
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, daftar.class ));
            }
        });
    }

    private void cekData(String email, String pass) {
        String url = "http://sems.api88.link/getUser.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                btnLogin.setClickable(true);
                btnDaftar.setClickable(true);
                loading.setVisibility(View.INVISIBLE);

                JSONObject obj = new JSONObject(response);
                JSONArray arr = obj.getJSONArray("data");
                for (int i = 0 ; i < arr.length(); i++){
                    JSONObject obj2 = arr.getJSONObject(i);
                    User user = new User(obj2);
                    Log.d("TAG", "cekData: "+obj2);
                    if(user.getEmail().equalsIgnoreCase(email)){
                        if(user.getPass().equals(pass)){
                            if (user.getStatus().equals("1")){
                                Intent intent =  new Intent(this, ScansActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(this, "Email Belum Terkonfirmasi", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(this, "Password salah", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this, "Email Belum Terdaftar", Toast.LENGTH_SHORT).show();
                    }

                }


            }catch (JSONException e){
                e.printStackTrace();
            }

        },error -> {

        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}