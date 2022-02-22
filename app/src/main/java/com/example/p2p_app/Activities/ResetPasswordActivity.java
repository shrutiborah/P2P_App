package com.example.p2p_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p2p_app.R;
import com.example.p2p_app.utility.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {

    Button reset;
    EditText password1,password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        password1 = findViewById(R.id.Password);
        password2 = findViewById(R.id.Password2);
        reset = findViewById(R.id.btnReset);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ;
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(password1.getText().toString()) || TextUtils.isEmpty(password2.getText().toString())) {
                    String message = "All inputs required";
                    Toast.makeText(ResetPasswordActivity.this, message, Toast.LENGTH_SHORT).show();

                }else{
                    ResetpassRequest resetpassRequest = new ResetpassRequest();

                    resetpassRequest.setNew_password1(password1.getText().toString());
                    resetpassRequest.setNew_password2(password2.getText().toString());

                    resetPassword(resetpassRequest);

                }
            }
        });


    }

    private void resetPassword(ResetpassRequest resetpassRequest) {
        Call<ResetpassResponse> resetpassResponseCall = ApiClient.getService().resetPassword(resetpassRequest);
        resetpassResponseCall.enqueue(new Callback<ResetpassResponse>() {
            @Override
            public void onResponse(Call<ResetpassResponse> call, Response<ResetpassResponse> response) {
                if(response.isSuccessful()) {

                    ResetpassResponse resetpassResponse = response.body();
                    startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class).putExtra("data", resetpassResponse));
                    finish();
                }else{
                    String message = "An error occured please try again later..";
                    Toast.makeText(ResetPasswordActivity.this,message,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResetpassResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(ResetPasswordActivity.this,message,Toast.LENGTH_SHORT).show();

            }
        });
    }


}