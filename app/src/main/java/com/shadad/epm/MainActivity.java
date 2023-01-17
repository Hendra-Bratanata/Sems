package com.shadad.epm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shadad.epm.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements RecycleViewClickInterface{
private ActivityMainBinding binding;
    ArrayList<DataItemHardware> dataHardwareItems;
    SwipeRefreshLayout swipeRefreshLayout;

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
        swipeRefreshLayout = findViewById(R.id.mainSwip);
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_200);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataHardware();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
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
                        dataHardwareItems = new ArrayList<>();
                        for (int i = 0 ; i < response.body().getData().size(); i++){
                            dataHardwareItems.add(response.body().getData().get(i));
                        }
                        HardwareAdapter adapter = new HardwareAdapter(dataHardwareItems, MainActivity.this);
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
        intent.putExtra("id", dataHardwareItems.get(posisition).getID());
        intent.putExtra("stack", dataHardwareItems.get(posisition).getSTACK());
        intent.putExtra("status", dataHardwareItems.get(posisition).getSTATUS());
        startActivity(intent);
//        Toast.makeText(this, dataHardwareItems.get(posisition).getID(), Toast.LENGTH_SHORT).show();
    }
}