package com.example.p2p_app.utility;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.p2p_app.Activities.LoginActivity;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    SessionManager sessionManager;
    public final  String TAG = "AUTH Interceptor";

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        sessionManager = new SessionManager(LoginActivity.getContext());
        Request newRequest = chain.request();
        if(newRequest.url().encodedPath().equals(Constants.LOGIN_URL)) {
            newRequest.newBuilder()
                    .build();
        }
        else {
            String token = sessionManager.fetchAccessToken();
            Log.e(TAG, token);
            newRequest.newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
        }
        return chain.proceed(newRequest);
    }
}
