package br.com.android_e_commerce_paypal_master.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;

import br.com.android_e_commerce_paypal_master.R;
import br.com.android_e_commerce_paypal_master.db.AppRef;
import br.com.android_e_commerce_paypal_master.ui.base.BaseActivity;
import br.com.android_e_commerce_paypal_master.ui.login.LoginActivity;

public class TelaAberturaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        encherTela();
        setContentView(R.layout.activity_tela_abertura);
        alterarStatusBarCor(ContextCompat.getColor(this, R.color.colorAccent));
        esconderToolbar();

        Intent intent = new Intent(TelaAberturaActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();


        // verificar para token autenticação no perfil
//        if (TextUtils.isEmpty(AppRef.getInstance().getAuthToken())) {
//            // token do usuário não está presente, leve-o para a tela de login
//            Intent intent = new Intent(TelaAberturaActivity.this, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            finish();
//
//            return;
//        }
        buscarconfigAplicativo();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tela_abertura;
    }

    // Buscando a configuração do aplicativo do servidor
    // Isso fará com que a configuração do PayTM seja necessária
    // para operações relacionadas ao pagamento
    private void buscarconfigAplicativo() {
//        Call<AppConfig> call = getApi().getAppConfig();
    }
}