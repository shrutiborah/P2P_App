package com.example.p2p_app.utility;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL= Constants.BASE_URL;
    private static RetrofitClient myClient;
    private final Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if (myClient == null){
            myClient = new RetrofitClient();
        }
        return myClient;
    }

    public UserService getService(){
        return retrofit.create(UserService.class);
    }

    private OkHttpClient okhttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }
}

