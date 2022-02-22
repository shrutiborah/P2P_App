package com.example.p2p_app.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2p_app.R;
import com.example.p2p_app.utility.UserService;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditprofileActivity extends AppCompatActivity {

    String url = "http://10.0.2.2:8000/";
    private final String TAG = "EDIT PROFILE ACTIVITY";

    // creating our variables for our views such
    // as text view, button and progress
    // bar and response text view.
    private EditText firstname, lastname;
    private Button updateBtn;
    private ProgressBar loadingPB;
    private TextView responseTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        // initializing our views with their ids.
        firstname = findViewById(R.id.idEdtUserName);
        lastname = findViewById(R.id.idEdtJob);
        updateBtn = findViewById(R.id.idBtnUpdate);
        loadingPB = findViewById(R.id.idPBLoading);
        responseTV = findViewById(R.id.idTVResponse);

        // adding on click listener for our button.
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking if the edit text is empty or not.
                if (TextUtils.isEmpty(firstname.getText().toString()) && TextUtils.isEmpty(lastname.getText().toString())) {

                    // displaying a toast message if the edit text is empty.
                    Toast.makeText(EditprofileActivity.this, "Please enter your data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // calling a method to update data in our API.
                callPUTDataMethod(firstname.getText().toString(), lastname.getText().toString());
            }
        });
    }

    private void callPUTDataMethod(String first_name, String last_name) {

        // below line is for displaying our progress bar.
        loadingPB.setVisibility(View.VISIBLE);

        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)

                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())

                // at last we are building our retrofit builder.
                .build();

        // below the line is to create an instance for our retrofit api class.
        UserService userService = retrofit.create(UserService.class);

        // passing data from our text fields to our modal class.
        DataModal modal = new DataModal(first_name, last_name);

        // calling a method to create an update and passing our modal class.
        Call<DataModal> call = userService.updateData(modal);




            call.enqueue(new Callback<DataModal>() {
                @Override
                public void onResponse(Call<DataModal> call, Response<DataModal> response) {

                    Toast.makeText(EditprofileActivity.this, "Data updated to API", Toast.LENGTH_SHORT).show();

                    // below line is for hiding our progress bar.
                    loadingPB.setVisibility(View.GONE);

                    // on below line we are setting empty
                    // text to our both edit text.
                    firstname.setText("");
                    lastname.setText("");

                    // we are getting a response from our body and
                    // passing it to our modal class.
                    DataModal responseFromAPI = response.body();
                    Log.e(TAG, responseFromAPI != null ? responseFromAPI.toString() : "null");

                    // on below line we are getting our data from modal class
                    // and adding it to our string.
                    String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getFirst_name()+ "\n" + "Last Name: " + responseFromAPI.getLast_name();

                    // below line we are setting our string to our text view.
                    responseTV.setText(responseString);

                }

                @Override
                public void onFailure(Call<DataModal> call, Throwable t) {

                    responseTV.setText("Error found is : " + t.getMessage());
                }
            });


    }
}