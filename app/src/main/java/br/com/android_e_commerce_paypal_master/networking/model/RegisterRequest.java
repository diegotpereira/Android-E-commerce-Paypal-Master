package br.com.android_e_commerce_paypal_master.networking.model;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    public String nome;
    public String email;
    public String password;

    @SerializedName("c_password")
    public  String confirmaPassword;

}
