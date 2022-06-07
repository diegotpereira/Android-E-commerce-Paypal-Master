package br.com.android_e_commerce_paypal_master.networking;

import br.com.android_e_commerce_paypal_master.db.model.User;
import br.com.android_e_commerce_paypal_master.networking.model.AppConfig;
import br.com.android_e_commerce_paypal_master.networking.model.LoginRequest;
import br.com.android_e_commerce_paypal_master.networking.model.RegisterRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiClient {

    @POST("login")
    Call<User> login(@Body LoginRequest loginRequest);

    @POST("register")
    Call<User>register(@Body RegisterRequest registerRequest);

    @GET("appConfig")
    Call<AppConfig> getAppConfig();

//    @FormUrlEncoded
//    @POST("getChecksum")
//    Call<ChecksumResponse> getCheckSum(@FieldMap Map<String, String> params);

}
