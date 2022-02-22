package com.example.p2p_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.p2p_app.R;
import com.example.p2p_app.utility.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfilesActivity extends AppCompatActivity implements UserAdapter.ClickedItem{

    Toolbar toolbar;
    RecyclerView recyclerView;
    UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profiles);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        userAdapter = new UserAdapter(this::ClickedUser);
        getAllUsers();

    }

    public void getAllUsers () {

        Call<List<UserResponse>> userlist = ApiClient.getService().getAllUsers();

        userlist.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                if (response.isSuccessful()) {
                    List<UserResponse> userResponses = response.body();
                    userAdapter.setData(userResponses);
                    recyclerView.setAdapter(userAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void ClickedUser(UserResponse userResponse) {

        startActivity(new Intent(this,UserDetailActivity.class).putExtra("data",userResponse));

    }
}