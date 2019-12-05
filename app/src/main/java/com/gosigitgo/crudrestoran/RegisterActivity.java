package com.gosigitgo.crudrestoran;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gosigitgo.crudrestoran.model.ResponseRegister;
import com.gosigitgo.crudrestoran.network.ConfigRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edt_nama)
    EditText edtNama;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_hp)
    EditText edtHp;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {

        String nama = edtNama.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String hp = edtHp.getText().toString();

        if (TextUtils.isEmpty(nama)|| TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)|| TextUtils.isEmpty(hp)){
            Toast.makeText(this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else {
            register (nama, email, password, hp);
        }
    }
    private void register (String nama, String email, String password, String hp){
        ConfigRetrofit.service.actionRegister(nama, email, password, hp).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                if (response.isSuccessful()){
                    String pesan = response.body().getPesan();
                    boolean sukses = response.body().isSukses();

                    if (sukses){
                        Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
