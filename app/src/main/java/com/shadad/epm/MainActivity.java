package com.shadad.epm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shadad.epm.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecycleViewClickInterface{
private ActivityMainBinding binding;
    ArrayList<DataItemHardware> dataItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvHardware.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        binding.rvHardware.addItemDecoration(itemDecoration);
        getDataHardware();

//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("https://sems.api88.link/getData.php?api_key=50bfbf83-76db-4cc8-9cc9-eaeb6d5a99b4", new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                String TAG = "TAG";
//                Log.e(TAG, "onSuccess: " + statusCode);
//                String data = new String(responseBody);
//                try {
//                    JSONObject obj = new JSONObject(data);
//                    JSONArray arr = obj.getJSONArray("data");
//                    String data1 = arr.getJSONObject(0).getString("ID");
//                    Log.e(TAG, "onSuccess: "+data1);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//            }
//        });

    }

    void getDataHardware(){
        Call<HardwareResponse> client =  ApiConfig.getApiService().getData("getHardware.php");
        client.enqueue(new Callback<HardwareResponse>() {
            @Override
            public void onResponse(Call<HardwareResponse> call, retrofit2.Response<HardwareResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataItems = new ArrayList<>();
                        for (int i = 0 ; i < response.body().getData().size(); i++){
                            dataItems.add(response.body().getData().get(i));
                        }
                        HardwareAdapter adapter = new HardwareAdapter(dataItems, MainActivity.this);
                        binding.rvHardware.setAdapter(adapter);


                    } else {
                        if (response.body() != null) {
                            Log.e("TAG", "onFailure: " + response.body().getData());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<HardwareResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int posisition) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("id",dataItems.get(posisition).getID());
        startActivity(intent);
        Toast.makeText(this, dataItems.get(posisition).getID(), Toast.LENGTH_SHORT).show();
    }
}