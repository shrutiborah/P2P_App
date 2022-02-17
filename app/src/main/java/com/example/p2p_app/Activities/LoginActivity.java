package com.example.p2p_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.p2p_app.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email, password, username;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.inemail);
        username = findViewById(R.id.username);
        password = findViewById(R.id.InputPassword);
        login = findViewById(R.id.btnLogin);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    String message = "All inputs required";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                }else{
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setUsername(username.getText().toString());
                    loginRequest.setEmail(email.getText().toString());
                    loginRequest.setPassword(password.getText().toString());

                    loginUser(loginRequest);

                }
            }
        });
        
        
        
        TextView btn = findViewById(R.id.textViewSignUp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));


            }
        });

        TextView forgotPass = findViewById(R.id.ForgotPassword);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotpasswordActivity.class));
            }
        });
    }

    public void loginUser(LoginRequest loginRequest) {
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){

                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class).putExtra("data", loginResponse));
                    finish();


                }else {

                    String message = "An error occured please try again later..";
                    Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();


            }
        });

    }
}


