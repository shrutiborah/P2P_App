package com.example.p2p_app.utility;

import android.widget.ImageView;

import com.example.p2p_app.Activities.DataModal;
import com.example.p2p_app.Activities.EnterRequest;
import com.example.p2p_app.Activities.EnterResponse;
import com.example.p2p_app.Activities.LoginRequest;
import com.example.p2p_app.Activities.LoginResponse;
import com.example.p2p_app.Activities.Materialresponse;
import com.example.p2p_app.Activities.MyMaterialResponse;
import com.example.p2p_app.Activities.MyProfileResponse;
import com.example.p2p_app.Activities.RegisterRequest;
import com.example.p2p_app.Activities.RegisterResponse;
import com.example.p2p_app.Activities.ResetpassRequest;
import com.example.p2p_app.Activities.ResetpassResponse;
import com.example.p2p_app.Activities.UploadResponse;
import com.example.p2p_app.Activities.UserResponse;
import com.example.p2p_app.chat.models.Chat;
import com.example.p2p_app.chat.models.Message;
import com.example.p2p_app.chat.models.PagedMessage;
import com.example.p2p_app.chat.models.Profile;
import com.example.p2p_app.chat.models.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface UserService {


    @POST("/api/auth/register/")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST(Constants.LOGIN_URL)
    Call<LoginResponse> login(@Body LoginRequest loginRequest);


    @FormUrlEncoded
    @POST("/upload-material/")
    Call<UploadResponse> uploadDocument(
            @Field("PDF") String encodedPDF
    );

    @POST("/auth/password/reset/")
    Call<EnterResponse> enterEmail(@Body EnterRequest enterRequest);

    @POST("/auth/password/change/")
    Call<ResetpassResponse> resetPassword(@Body ResetpassRequest resetpassRequest);


    @GET("profiles/")
    Call<List<UserResponse>> getAllUsers();

    @GET("/my-profile")
    Call<List<MyProfileResponse>> getPosts(@Header("Authorization") String authHeader);

    @GET("/materials/")
    Call<List<Materialresponse>> getAllMaterial();

    @GET("/my-material/")
    Call<List<MyMaterialResponse>> getMyMaterial();


    @PUT("/auth/user/")
    Call<DataModal> updateData(@Body DataModal dataModal);

    @GET(Constants.CHATS_URL)
    Call<List<Chat>> chat( @Header("Authorization") String authHeader);


    @GET(Constants.MESSAGES)
    Call<List<Message>> getMessage(@Query("receiver") int id, @Header("Authorization") String authHeader);

    @GET(Constants.MESSAGES)
    Call<PagedMessage> getPagedMessage(@Query("receiver") int id, @Query("limit") int limit, @Query("offset") int offset, @Header("Authorization") String authHeader);

    @GET(Constants.MY_PROFILE)
    Call<Profile> getId(@Header("Authorization") String authHeader);

    @GET(Constants.PROFILE + "{user_id}")
    Call<Profile> getProfile(@Path("user_id") Integer user_id, @Header("Authorization") String authHeader);

    @GET("{image_path}")
    Call<ResponseBody> getProfileImage(@Path("image_path") String image_path, @Header("Authorization") String authHeader);
}

