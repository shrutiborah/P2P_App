package com.example.p2p_app.Activities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {

    @POST("/api/auth/register/")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("/auth/login/")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

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
    Call<List<MyProfileResponse>> getPosts();

    @GET("/materials/")
    Call<List<Materialresponse>> getAllMaterial();

    @GET("/my-material/")
    Call<List<MyMaterialResponse>> getMyMaterial();


    @PUT("/auth/user/")
    Call<DataModal> updateData(@Body DataModal dataModal);




}

