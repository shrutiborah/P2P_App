package com.example.p2p_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.p2p_app.R;
import com.example.p2p_app.utility.ApiClient;
import com.example.p2p_app.utility.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyProfileActivity extends AppCompatActivity {

    private TextView textViewResult;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        sharedPreferences = getSharedPreferences("ACCESS_TOKEN", Context.MODE_PRIVATE);
        String ACCESS_TOKEN = sharedPreferences.getString("access_token", null);
        Log.e("ACCESS TOKEN", ACCESS_TOKEN);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);

        Call<List<MyProfileResponse>> call = ApiClient.getService().getPosts("Bearer " + ACCESS_TOKEN);

        call.enqueue(new Callback<List<MyProfileResponse>>() {
            @Override
            public void onResponse(Call<List<MyProfileResponse>> call, Response<List<MyProfileResponse>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<MyProfileResponse> myProfileResponses = response.body();

                for (MyProfileResponse myProfileResponse : myProfileResponses) {
                    String content = "";
                    content += "ID: " + myProfileResponse.getId() + "\n";
                    content += "Points " + myProfileResponse.getPoints() + "\n";
                    content += "User: " + myProfileResponse.getUser() + "\n";
                    content += "Created " + myProfileResponse.getCreated() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<MyProfileResponse>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}