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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotpasswordActivity extends AppCompatActivity {

    Button enter;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        email = findViewById(R.id.email);
        enter = findViewById(R.id.btnEnter);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ;
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email.getText().toString())) {
                    String message = "All inputs required";
                    Toast.makeText(ForgotpasswordActivity.this, message, Toast.LENGTH_SHORT).show();

                }else{
                    EnterRequest enterRequest = new EnterRequest();

                    enterRequest.setEmail(email.getText().toString());


                    enterEmail(enterRequest);

                }
            }
        });


        }

    private void enterEmail(EnterRequest enterRequest) {
        Call<EnterResponse> enterResponseCall = ApiClient.getService().enterEmail(enterRequest);
        enterResponseCall.enqueue(new Callback<EnterResponse>() {
            @Override
            public void onResponse(Call<EnterResponse> call, Response<EnterResponse> response) {

                if(response.isSuccessful()){

                    EnterResponse enterResponse = response.body();
                    startActivity(new Intent(ForgotpasswordActivity.this,ResetPasswordActivity.class).putExtra("data", enterResponse));
                    finish();


                }else {

                    String message = "An error occured please try again later..";
                    Toast.makeText(ForgotpasswordActivity.this,message,Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<EnterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(ForgotpasswordActivity.this,message,Toast.LENGTH_SHORT).show();

            }
        });
    }

}
