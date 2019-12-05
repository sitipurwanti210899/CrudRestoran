package com.gosigitgo.crudrestoran;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gosigitgo.crudrestoran.model.Data;
import com.gosigitgo.crudrestoran.model.ResponseGetMakanan;
import com.gosigitgo.crudrestoran.network.ConfigRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {

    @BindView(R.id.edt_insert_makanan)
    EditText edtInsertMakanan;
    @BindView(R.id.edt_insert_harga)
    EditText edtInsertHarga;
    @BindView(R.id.edt_gambar)
    EditText edtGambar;
    @BindView(R.id.btn_insert)
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_insert)
    public void onViewClicked() {
        String nama = edtInsertMakanan.getText().toString();
        String harga = edtInsertHarga.getText().toString();
        String gambar = edtGambar.getText().toString();

        if (TextUtils.isEmpty(nama)||TextUtils.isEmpty(harga)||TextUtils.isEmpty(gambar)){
            Toast.makeText(this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else {
            insertData (nama, harga, gambar);
        }
    }
    private void insertData(String nama, String harga, String gambar){
        ConfigRetrofit.service.insertMakanan(nama, harga, gambar).enqueue(new Callback<ResponseGetMakanan>() {
            @Override
            public void onResponse(Call<ResponseGetMakanan> call, Response<ResponseGetMakanan> response) {
                String pesan = response.body().getPesan();
                boolean sukses = response.body().isSukses();
                //jika berhasil
                if (sukses){
                    Toast.makeText(InsertActivity.this, pesan, Toast.LENGTH_SHORT).show();

                    finish();
                } //jika gagal
                else {
                    Toast.makeText(InsertActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetMakanan> call, Throwable t) {
                Toast.makeText(InsertActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
