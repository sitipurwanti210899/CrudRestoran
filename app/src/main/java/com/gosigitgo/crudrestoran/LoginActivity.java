package com.gosigitgo.crudrestoran;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gosigitgo.crudrestoran.model.Data;
import com.gosigitgo.crudrestoran.model.ResponseLogin;
import com.gosigitgo.crudrestoran.network.ConfigRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_to_register)
    TextView tvToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.tv_to_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else {
                    login(email, password);
                }

                break;
            case R.id.tv_to_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }
    //buat parametr baru
    private void login (String email, String password){
        ConfigRetrofit.service.actionLogin(email, password).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
            String pesan = response.body().getPesan();
            boolean sukses = response.body().isSukses();
            //jika berhasil
            if (sukses){

                Data data =response.body().getData();
                String email = data.getUserEmail();
                String password = data.getUserPassword();

                Preferences.actionLogin(LoginActivity.this, email, password);


                Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } //jika gagal
                else {
                    Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
            }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

            }
        });
    }

}
