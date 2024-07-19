package com.example.uasmobiletikettravelbaduy.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobiletikettravelbaduy.R;
import com.example.uasmobiletikettravelbaduy.activities.DetailTravelActivity;
import com.example.uasmobiletikettravelbaduy.activities.MainActivity;
import com.example.uasmobiletikettravelbaduy.models.Travel;

import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {
    private Context context;
    private List<Travel> travelList;

    public TravelAdapter(Context context, List<Travel> travelList) {
        this.context = context;
        this.travelList = travelList;
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_travel, parent, false);
        return new TravelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, int position) {
        Travel travel = travelList.get(position);
        holder.tvNamaPaket.setText(travel.getNamaPaket());
        holder.tvTujuan.setText(travel.getTujuan());
        holder.tvHarga.setText("Rp " + travel.getHarga());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTravelActivity.class);
                intent.putExtra("travel", travel);
                context.startActivity(intent);
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).editTravel(travel);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).deleteTravel(travel.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return travelList.size();
    }

    public void updateData(List<Travel> newTravelList) {
        this.travelList = newTravelList;
        notifyDataSetChanged();
    }

    public class TravelViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaPaket, tvTujuan, tvHarga;
        Button btnEdit, btnDelete;

        public TravelViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaPaket = itemView.findViewById(R.id.tvNamaPaket);
            tvTujuan = itemView.findViewById(R.id.tvTujuan);
            tvHarga = itemView.findViewById(R.id.tvHarga);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
