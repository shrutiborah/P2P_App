package com.example.p2p_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.p2p_app.R;

public class UserDetailActivity extends AppCompatActivity {

    TextView username, gender, created;
    UserResponse userResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        username = findViewById(R.id.username);
        gender = findViewById(R.id.gender);
        created = findViewById(R.id.created);

        Intent intent = getIntent();
        if (intent.getExtras() !=null){
            userResponse = (UserResponse) intent.getSerializableExtra("data");
            String usernamedata = userResponse.getUser();
            String usergender = userResponse.getGender();
            String usercreated = userResponse.getCreated();

            gender.setText(usergender);
            created.setText(usercreated);
            username.setText(usernamedata);

        }
    }
}