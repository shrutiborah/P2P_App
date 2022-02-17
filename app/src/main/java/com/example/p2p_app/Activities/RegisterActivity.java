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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button register;
    EditText edUsername,edEmail, edPassword, edCpassword;
    TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (Button) findViewById(R.id.btnRegister);
        edUsername = findViewById(R.id.username);
        edEmail = findViewById(R.id.inemail);
        edPassword = findViewById(R.id.Password);
        edCpassword = findViewById(R.id.Confirmpassword);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edUsername.getText().toString()) || TextUtils.isEmpty(edEmail.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString()) || TextUtils.isEmpty(edCpassword.getText().toString())) {
                    String message = "All inputs required";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                }else {

                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setUsername(edUsername.getText().toString());
                    registerRequest.setEmail(edEmail.getText().toString());
                    registerRequest.setPassword1(edPassword.getText().toString());
                    registerRequest.setPassword2(edCpassword.getText().toString());

                    registerUser(registerRequest);
                }
            }
        });
         btn = findViewById(R.id.AlreadyHaveAccount);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        startActivity(new Intent(RegisterActivity.this, com.example.p2p_app.Activities.LoginActivity.class));





            }
        });
        
        
    }
    public void registerUser(RegisterRequest registerRequest){

        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if(response.isSuccessful()){

                    String message = "Registration Successful";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();


                }else {

                    String message = "An error occured please try again later..";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_SHORT).show();

            }
        });
    }




    @Override
    public void onClick(View view) {

    }
}