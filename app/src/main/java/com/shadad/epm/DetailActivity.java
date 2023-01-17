package com.shadad.epm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAction;
    TextView tvId;
    TextView tvStack;
    TextView tvStatus;
    TextView tvAmper;
    TextView tvVoltage;
    TextView tvPower;
    TextView tvEnergy;
    TextView tvDate;
    TextView tvTime;
    ProgressBar loading;

    SwipeRefreshLayout swipeRefreshLayout;
    String id;
    String stack;
    String status;
    DataItem sems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai);
        btnAction = findViewById(R.id.btnActionDetail);
        btnAction.setOnClickListener(this);
        tvId = findViewById(R.id.tvDetailId);
        tvStack = findViewById(R.id.tvDetailStack);
        tvStatus = findViewById(R.id.tvDetailStatus);
        tvAmper = findViewById(R.id.tvDetailAmper);
        tvVoltage = findViewById(R.id.tvDetailVoltage);
        tvPower = findViewById(R.id.tvDetailPower);
        tvEnergy = findViewById(R.id.tvDetailEnergy);
        tvDate = findViewById(R.id.tvDetailDate);
        tvTime = findViewById(R.id.tvDetailTime);
        loading = findViewById(R.id.loadingDetail);
        loading.setVisibility(View.INVISIBLE);
        swipeRefreshLayout = findViewById(R.id.swap);
        swipeRefreshLayout.setColorSchemeResources(R.color.teal_200);

         Intent intent = getIntent();
         id = intent.getStringExtra("id");
        Log.d("TAG", "onCreate: "+id);
         stack = intent.getStringExtra("stack");
         status = intent.getStringExtra("status");

        swipeRefreshLayout.setOnRefreshListener(() -> {
            getSemsData(id);
            swipeRefreshLayout.setRefreshing(false);
        });
        getSemsData(id);


    }
    public void getSemsData(String id){
        loading.setVisibility(View.VISIBLE);
        String url = "http://sems.api88.link/getData.php?hardwareID="+id;
        Log.d("TAG", "getSemsData: "+url);
        StringRequest sr = new StringRequest(Request.Method.GET, url, response -> {
            try {
                loading.setVisibility(View.INVISIBLE);
                JSONObject obj = new JSONObject(response);
                JSONArray arr =  obj.getJSONArray("data");
                JSONObject obj2 = arr.getJSONObject(0);
                sems = new DataItem(obj2);
                Log.d("TAG", "onResponse: "+sems.id);
                    float energy = Float.parseFloat(sems.getPower());
                    float en = energy * 3600 / 1000000;
                tvId.setText("ID \t\t\t\t\t\t\t\t: "+sems.getId());
                tvStack.setText("Stack \t\t\t\t: "+stack);
                tvStatus.setText("Status \t\t\t: "+status);
                tvAmper.setText("Amper \t\t: "+sems.amper);
                tvVoltage.setText("Volt \t\t\t\t\t\t: "+sems.volt);
                tvPower.setText("Power \t\t\t: "+sems.power);
                    tvEnergy.setText("Energy \t\t: "+en);
                tvDate.setText("Date \t\t\t\t\t: "+sems.date);
                tvTime.setText("Time \t\t\t\t: "+sems.time);
                btnAction.setText(status);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(sr);

    }

    @Override
    public void onClick(View view) {
        if(status.equalsIgnoreCase("on")){
            status = "OFF";
            String statusINT = "6";
            btnAction.setText(status);
            setHardware(statusINT, id);
        }
        else if(status.equalsIgnoreCase("off")){
            status = "ON";
            String statusINT = "5";
            btnAction.setText(status);
            setHardware(statusINT, id);
        }


    }

    private void setHardware(String statusINT, String id) {
        String url = "http://sems.api88.link/setHardware.php?id="+statusINT+"&idH="+id;
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, url, response -> {

        }, error -> {

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}