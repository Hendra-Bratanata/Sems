package com.shadad.epm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.UUID;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSend;
    TextView tvId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        tvId = findViewById(R.id.tvDetailHardwareId);
         Intent intent = getIntent();
         String id = intent.getStringExtra("id");
         tvId.setText(id);





    }

    @Override
    public void onClick(View view) {

    }
}