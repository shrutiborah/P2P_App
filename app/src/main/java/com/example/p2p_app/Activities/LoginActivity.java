package com.example.p2p_app.Activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.p2p_app.R;
import com.example.p2p_app.chat.WebSocketService;
import com.example.p2p_app.chat.models.Message;
import com.example.p2p_app.utility.ApiClient;
import com.example.p2p_app.utility.RetrofitClient;
import com.example.p2p_app.utility.SessionManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email, password, username;
    Button login;
//    SharedPreferences sharedPreferences;
//    public static final String SHARED_PREFS = "ACCESS_TOKEN";
    SessionManager sessionManager;
    public static Context context;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();

        email = findViewById(R.id.inemail);
        username = findViewById(R.id.username);
        password = findViewById(R.id.InputPassword);
        login = findViewById(R.id.btnLogin);
        sessionManager = new SessionManager(this);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ;

        username.setText("lastmonk");
        email.setText("lastmonk@example.com");
        password.setText("Alcohol#99");
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

    public static Context getContext(){
        return context;
    }

    public void loginUser(LoginRequest loginRequest) {
//        Call<LoginResponse> loginResponseCall = ApiClient.getService().login(loginRequest);
//        loginResponseCall.enqueue(new Callback<LoginResponse>() {
        RetrofitClient.getInstance().getService().login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("access_token", loginResponse.getMessage());
//                    editor.apply();
                    sessionManager.saveAccessToken(loginResponse.getMessage());
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class).putExtra("data", loginResponse.getStatusCode()));
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


