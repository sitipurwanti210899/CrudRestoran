package com.gosigitgo.crudrestoran;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gosigitgo.crudrestoran.model.DataItem;
import com.gosigitgo.crudrestoran.model.ResponseGetMakanan;
import com.gosigitgo.crudrestoran.network.ConfigRetrofit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    @BindView(R.id.edt_update_makanan)
    EditText edtUpdateMakanan;
    @BindView(R.id.edt_update_harga)
    EditText edtUpdateHarga;
    @BindView(R.id.edt_gambarr)
    EditText edtGambarr;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    DataItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        //terima data
        item =getIntent().getParcelableExtra("UPDATE");

        edtUpdateMakanan.setText(item.getMenuNama());
        edtUpdateHarga.setText(item.getMenuHarga());
        edtGambarr.setText(item.getMenuGambar());
    }

    @OnClick(R.id.btn_update)
    public void onViewClicked() {
        String nama = edtUpdateMakanan.getText().toString();
        String harga = edtUpdateHarga.getText().toString();
        String gambar = edtGambarr.getText().toString();

        if (TextUtils.isEmpty(nama)||TextUtils.isEmpty(harga)||TextUtils.isEmpty(gambar)){
            Toast.makeText(this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else {
            updateData(item.getMenuId(), nama, harga, gambar);
        }
    }
    private void updateData (String id, String nama, String harga, String gambar){
        ConfigRetrofit.service.updateMakanan(id, nama, harga, gambar).enqueue(new Callback<ResponseGetMakanan>() {
            @Override
            public void onResponse(Call<ResponseGetMakanan> call, Response<ResponseGetMakanan> response) {
                String pesan = response.body().getPesan();
                boolean sukses = response.body().isSukses();
                //jika berhasil
                if (sukses){
                    Toast.makeText(UpdateActivity.this, pesan, Toast.LENGTH_SHORT).show();

                    finish();
                } //jika gagal
                else {
                    Toast.makeText(UpdateActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetMakanan> call, Throwable t) {

            }
        });
    }
}
