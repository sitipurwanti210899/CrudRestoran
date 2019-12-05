package com.gosigitgo.crudrestoran;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gosigitgo.crudrestoran.model.DataItem;

import java.util.List;

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.ViewHolder> {
//1
    List<DataItem> dataItems;
    Context context;

    public MakananAdapter(List<DataItem> dataItems, Context context) {
        this.dataItems = dataItems;
        this.context = context;
    }
//2
    @NonNull
    @Override
    public MakananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_makanan,parent, false);
        return new ViewHolder(view);
    }
//4
    @Override
    public void onBindViewHolder(@NonNull MakananAdapter.ViewHolder holder, int position) {
        //tampilkan nama hrga dan gambar
    holder.tvharga.setText(dataItems.get(position).getMenuHarga());
    holder.tvnama.setText(dataItems.get(position).getMenuNama());

        Glide.with(context)
                .load(dataItems.get(position).getMenuGambar())
                .error(R.drawable.ic_launcher_background).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("UPDATE",dataItems.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }
//3
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tvnama,tvharga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.item_image);
            tvnama = itemView.findViewById(R.id.tv_nama);
            tvharga = itemView.findViewById(R.id.tv_harga);
        }
    }
}
