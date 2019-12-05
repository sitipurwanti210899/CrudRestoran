package com.gosigitgo.crudrestoran;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gosigitgo.crudrestoran.model.DataItem;
import com.gosigitgo.crudrestoran.model.ResponseGetMakanan;
import com.gosigitgo.crudrestoran.network.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @BindView(R.id.rv_makanan)
    RecyclerView rvMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String email = Preferences.getEmail(this);
        String password = Preferences.getPasword(this);

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        rvMakanan.setLayoutManager(new LinearLayoutManager(this));
        rvMakanan.setHasFixedSize(true);

        //proses load
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        reloadData();
    }

    private void reloadData(){
        ConfigRetrofit.service.getAllMakanan().enqueue(new Callback<ResponseGetMakanan>() {
            @Override
            public void onResponse(Call<ResponseGetMakanan> call, Response<ResponseGetMakanan> response) {
                if (response.isSuccessful()){
                    String pesan = response.body().getPesan();
                    boolean sukses = response.body().isSukses();

                    if (sukses){
                        progressDialog.dismiss();

                        List<DataItem> dataItems = response.body().getData();

                        MakananAdapter adapter = new MakananAdapter(dataItems, MainActivity.this);
                        adapter.notifyDataSetChanged();

//                        helper = new ItemTouchHelper(new ItemTouchHelper
//                                .SimpleCallback(0, ItemTouchHelper.LEFT |
//                                ItemTouchHelper.RIGHT) {
//
//
//                            @Override
//                            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                                return false;
//                            }
//
//                            @Override
//                            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                                dataItems.remove(viewHolder.getAdapterPosition());
//                                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//
//                            }});
//
//                        helper.attachToRecyclerView(rvMakanan);



                            rvMakanan.setAdapter(adapter);
                    }else {
                        Toast.makeText(MainActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetMakanan> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        reloadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            Preferences.actionLogout(this);
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        if (item.getItemId()== R.id.menu_insert){
            startActivity(new Intent(MainActivity.this, InsertActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.rv_makanan)
    public void onViewClicked() {
    }
}
