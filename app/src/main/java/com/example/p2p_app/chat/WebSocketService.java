package com.example.p2p_app.chat;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.p2p_app.Activities.LoginActivity;
import com.example.p2p_app.chat.data.MyProfile;
import com.example.p2p_app.chat.models.ChatUser;
import com.example.p2p_app.chat.models.MessageItem;
import com.example.p2p_app.chat.models.User;
import com.example.p2p_app.utility.Constants;
import com.example.p2p_app.utility.RetrofitClient;
import com.example.p2p_app.utility.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WebSocketService extends Service {

    private String TAG = "WebSocketService";
    public WebSocketService() {
    }
    SessionManager sessionManager =  new SessionManager(LoginActivity.getContext()); //TODO: Check
    public OkHttpClient client;
    private static WebSocket WEB_SOCKET = null;
    private final static String SOCKET_ENDPOINT = Constants.SOCKET_ENDPOINT;
    private final WebSocketClientBinder mBinder = new WebSocketClientBinder();

    public class WebSocketClientBinder extends Binder {
        public WebSocketService getService() {
            return  WebSocketService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
        initWebSocketClient();
        Log.e(TAG, "Starting WebsocketService");
//        return START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    private void initWebSocketClient() {
        client = new OkHttpClient();
        String token = sessionManager.fetchAccessToken();
        Request request = new Request.Builder()
                .url(SOCKET_ENDPOINT)
                .addHeader("Authorization", "Bearer " + token)
                .build();
        WebSocketService.WebSocketServiceListener socketOperationService = new WebSocketService.WebSocketServiceListener();
        WEB_SOCKET = client.newWebSocket(request, socketOperationService);
    }

    public static WebSocket getWebSocket(){
        return WEB_SOCKET;
    }

    public class WebSocketServiceListener extends WebSocketListener {



        @Override
        public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
            super.onOpen(webSocket, response);
        }

        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
            Log.e("Where?", "onMessage()");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject = new JSONObject(text);
                        Log.e("Message", text);
                        if(jsonObject.has("message")) {
                            Intent intent = new Intent();
                            intent.setAction("message signal");
                            intent.putExtra("Message", "message");
                            WebSocketService.super.sendBroadcast(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            Log.e(TAG, "Closed, Reason: " +  reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
            Log.e(TAG, "Failed, Message: " +  t.getMessage());
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        if (WEB_SOCKET == null) {
            initWebSocketClient();
        }
        return mBinder;
    }
}