package br.com.android_e_commerce_paypal_master.db.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    public long id;
    public String nome;
    public String email;
    public String token;
}
