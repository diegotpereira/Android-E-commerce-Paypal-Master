package br.com.android_e_commerce_paypal_master.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import br.com.android_e_commerce_paypal_master.R;
import br.com.android_e_commerce_paypal_master.db.AppBancoDados;
import br.com.android_e_commerce_paypal_master.db.AppRef;
import br.com.android_e_commerce_paypal_master.db.model.User;
import br.com.android_e_commerce_paypal_master.networking.model.LoginRequest;
import br.com.android_e_commerce_paypal_master.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.entrada_email)
    EditText entradaEmail;

    @BindView(R.id.entrada_password)
    EditText entradaSenha;

    @BindView(R.id.loader)
    AVLoadingIndicatorView loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        encherTela();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        alterarStatusBarCor(ContextCompat.getColor(this, R.color.colorAccent));
        esconderToolbar();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
    @OnClick(R.id.btn_login)
    void onLoginClick() {
        String email = entradaEmail.getText().toString();
        String senha = entradaSenha.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)) {

            Toast.makeText(getApplicationContext(), getString(R.string.msg_enter_credentials), Toast.LENGTH_SHORT).show();

            return;
        }
        loader.setVisibility(View.VISIBLE);

        LoginRequest request = new LoginRequest();
        request.email = email;
        request.senha = senha;

        getApi().login(request).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                loader.setVisibility(View.INVISIBLE);

                if (!response.isSuccessful()) {
                    handleError(response.errorBody());

                    return;
                }
                AppBancoDados.salvarUsuario(response.body());
                AppRef.getInstance().salvarAuthToken(response.body().token);
                inciarTelaAbertura(LoginActivity.this);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loader.setVisibility(View.INVISIBLE);
                handleError(t);
            }
        });
    }
}