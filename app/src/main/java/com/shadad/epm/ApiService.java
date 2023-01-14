package com.shadad.epm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface ApiService {
    @GET("{apiKey}")
    Call<HardwareResponse>getData(@Path("apiKey") String apiKey);
}
