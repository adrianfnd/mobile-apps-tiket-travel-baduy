package com.example.uasmobiletikettravelbaduy.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uasmobiletikettravelbaduy.R;
import com.example.uasmobiletikettravelbaduy.database.DatabaseHelper;
import com.example.uasmobiletikettravelbaduy.models.Travel;

public class TambahTravelActivity extends AppCompatActivity {
    private EditText etNamaPaket, etTujuan, etDurasi, etHarga, etTanggal, etKapasitas, etFasilitas;
    private Button btnSimpan;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_travel);

        etNamaPaket = findViewById(R.id.etNamaPaket);
        etTujuan = findViewById(R.id.etTujuan);
        etDurasi = findViewById(R.id.etDurasi);
        etHarga = findViewById(R.id.etHarga);
        etTanggal = findViewById(R.id.etTanggal);
        etKapasitas = findViewById(R.id.etKapasitas);
        etFasilitas = findViewById(R.id.etFasilitas);
        btnSimpan = findViewById(R.id.btnSimpan);

        dbHelper = new DatabaseHelper(this);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTravel();
            }
        });
    }

    private void saveTravel() {
        String namaPaket = etNamaPaket.getText().toString();
        String tujuan = etTujuan.getText().toString();
        int durasi = Integer.parseInt(etDurasi.getText().toString());
        double harga = Double.parseDouble(etHarga.getText().toString());
        String tanggal = etTanggal.getText().toString();
        int kapasitas = Integer.parseInt(etKapasitas.getText().toString());
        String fasilitas = etFasilitas.getText().toString();

        Travel travel = new Travel(0, namaPaket, tujuan, durasi, harga, tanggal, kapasitas, fasilitas);
        long id = dbHelper.tambahTravel(travel);

        if (id != -1) {
            Toast.makeText(this, "Travel berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Gagal menambahkan travel", Toast.LENGTH_SHORT).show();
        }
    }
}