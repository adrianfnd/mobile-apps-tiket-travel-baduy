package com.example.uasmobiletikettravelbaduy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uasmobiletikettravelbaduy.R;
import com.example.uasmobiletikettravelbaduy.models.Travel;

public class DetailTravelActivity extends AppCompatActivity {
    private TextView tvNamaPaket, tvTujuan, tvDurasi, tvHarga, tvTanggal, tvKapasitas, tvFasilitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_travel);

        tvNamaPaket = findViewById(R.id.tvNamaPaket);
        tvTujuan = findViewById(R.id.tvTujuan);
        tvDurasi = findViewById(R.id.tvDurasi);
        tvHarga = findViewById(R.id.tvHarga);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvKapasitas = findViewById(R.id.tvKapasitas);
        tvFasilitas = findViewById(R.id.tvFasilitas);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("travel")) {
            Travel travel = intent.getParcelableExtra("travel");
            if (travel != null) {
                tvNamaPaket.setText("Nama Paket: " + travel.getNamaPaket());
                tvTujuan.setText("Tujuan: " + travel.getTujuan());
                tvDurasi.setText("Durasi: " + travel.getDurasi() + " hari");
                tvHarga.setText("Harga: Rp " + travel.getHarga());
                tvTanggal.setText("Tanggal Keberangkatan: " + travel.getTanggalKeberangkatan());
                tvKapasitas.setText("Kapasitas: " + travel.getKapasitas() + " orang");
                tvFasilitas.setText("Fasilitas Hotel: " + travel.getFasilitasHotel());
            }
        }
    }
}