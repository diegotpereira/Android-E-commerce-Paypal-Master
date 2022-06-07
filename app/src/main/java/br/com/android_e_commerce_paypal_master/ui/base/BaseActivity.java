package br.com.android_e_commerce_paypal_master.ui.base;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import br.com.android_e_commerce_paypal_master.R;
import br.com.android_e_commerce_paypal_master.model.ErrorResponse;
import br.com.android_e_commerce_paypal_master.networking.ApiClient;
import br.com.android_e_commerce_paypal_master.ui.login.LoginActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

public abstract class BaseActivity extends AppCompatActivity {

    private static ApiClient mApi;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = view.findViewById(R.id.activity_content_holder);
        DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), activityContainer, true);
        setContentView(R.layout.activity_base);
    }
    public ApiClient getApi() {
        if (mApi == null) {
            mApi = ApiService.getClient().create(ApiClient.class);
        }
           return mApi;
    }
    public abstract @LayoutRes int getLayoutId();

    @Override
    public void setContentView(final int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = view.findViewById(R.id.activity_content_holder);
        DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), activityContainer, true);
        super.setContentView(view);
        ButterKnife.bind(this);
    }
    public void exbirProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
    public void toggleProgress(boolean ehCarregado) {
        if (ehCarregado)
            exbirProgress();
        else
            esconderProgress();
    }
    public void esconderProgress() {
        progressBar.setVisibility(View.GONE);
    }
    public void handleError(Throwable throwable) {
        exibirErroDialog(getString(R.string.msg_unknown));
    }
    public void handleUnknownError() {
        exibirErroDialog(getString(R.string.msg_unknown));
    }
    public void handleError(ResponseBody responseBody) {
        String mensagem = null;
        if (responseBody != null) {
            try {
                ErrorResponse errorResponse = new Gson().fromJson(responseBody.charStream(), ErrorResponse.class);
                mensagem = errorResponse.erro;
            } catch (JsonSyntaxException e) {

            } catch (JsonIOException e) {

            }
        }
        mensagem = TextUtils.isEmpty(mensagem) ? getString(R.string.msg_unknown) : mensagem;
        exibirErroDialog(mensagem);
    }
    public void exibirErroDialog(String mensagem) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.error))
                .setMessage(mensagem)
                .setPositiveButton(android.R.string.ok, (dialog, wich) -> {

                })
                .show();
    }
    public void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void esconderToolbar() {
        appBar.setVisibility(View.GONE);
    }
    public void alterarStatusBarCor() {
        alterarStatusBarCor();
    }
    public void alterarStatusBarCor(int cor) {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(cor);
        }
    }
    public void encherTela() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }
    public void inciarTelaAbertura(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}