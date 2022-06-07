package br.com.android_e_commerce_paypal_master.db;

import br.com.android_e_commerce_paypal_master.db.model.User;
import io.realm.Realm;

public class AppBancoDados {

    public AppBancoDados() {

    }
    public static void salvarUsuario(User user) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.delete(User.class);
        });
    }
    public static User getUsuario() {
        return Realm.getDefaultInstance().where(User.class).findFirst();
    }
}
