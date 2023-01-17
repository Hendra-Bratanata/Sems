package com.shadad.epm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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

public class daftar extends AppCompatActivity {
    EditText edtEmail;
    EditText edtPass;
    EditText edtRePass;
    Button btndaftar;
    ProgressBar loanding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        btndaftar = findViewById(R.id.btnDaftar);
        edtEmail = findViewById(R.id.edtDaftarEmail);
        edtPass = findViewById(R.id.edtDaftarPass);
        edtRePass = findViewById(R.id.edtDaftarRePass);
        loanding = findViewById(R.id.loadingDaftar);
        loanding.setVisibility(View.INVISIBLE);

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtEmail.getText())){
                    edtEmail.setError("Wajib di isi");
                }
                if(TextUtils.isEmpty(edtPass.getText())){
                    edtEmail.setError("Wajib di isi");
                }
                if(TextUtils.isEmpty(edtRePass.getText())){
                    edtEmail.setError("Wajib di isi");
                }
                else {

                    String email = edtEmail.getText().toString();
                    String pass = edtPass.getText().toString();
                    String rePass = edtRePass.getText().toString();
                    if(pass.equals(rePass)){
                        loanding.setVisibility(View.VISIBLE);
                        btndaftar.setClickable(false);
                        kirimData(email,pass);
                    }else {
                        edtRePass.setError("password tidak sama");
                        edtPass.setError("password tidak sama");
                    }
                }
            }
        });




    }

    private void kirimData(String email, String pass) {

        String url = "http://sems.api88.link/daftar.php?email="+email+"&pass="+pass;
        StringRequest sr =  new StringRequest(Request.Method.GET, url, response -> {
            loanding.setVisibility(View.INVISIBLE);
            btndaftar.setClickable(true);
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray arr = obj.getJSONArray("data");
                String obj2 = arr.getJSONObject(0).getString("STATUS");
                Toast.makeText(this, obj2, Toast.LENGTH_SHORT).show();
                if(obj2.equals("OK")){
                    this.finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

        });
        RequestQueue qn = Volley.newRequestQueue(this);
        qn.add(sr);
    }

}