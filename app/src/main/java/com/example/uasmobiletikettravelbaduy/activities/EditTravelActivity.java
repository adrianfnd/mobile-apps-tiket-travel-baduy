package com.example.uasmobiletikettravelbaduy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uasmobiletikettravelbaduy.R;
import com.example.uasmobiletikettravelbaduy.database.DatabaseHelper;
import com.example.uasmobiletikettravelbaduy.models.Travel;

public class EditTravelActivity extends AppCompatActivity {
    private EditText etNamaPaket, etTujuan, etDurasi, etHarga, etTanggal, etKapasitas, etFasilitas;
    private Button btnUpdate;
    private DatabaseHelper dbHelper;
    private Travel travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_travel);

        etNamaPaket = findViewById(R.id.etNamaPaket);
        etTujuan = findViewById(R.id.etTujuan);
        etDurasi = findViewById(R.id.etDurasi);
        etHarga = findViewById(R.id.etHarga);
        etTanggal = findViewById(R.id.etTanggal);
        etKapasitas = findViewById(R.id.etKapasitas);
        etFasilitas = findViewById(R.id.etFasilitas);
        btnUpdate = findViewById(R.id.btnUpdate);

        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("travel")) {
            travel = intent.getParcelableExtra("travel");
            if (travel != null) {
                etNamaPaket.setText(travel.getNamaPaket());
                etTujuan.setText(travel.getTujuan());
                etDurasi.setText(String.valueOf(travel.getDurasi()));
                etHarga.setText(String.valueOf(travel.getHarga()));
                etTanggal.setText(travel.getTanggalKeberangkatan());
                etKapasitas.setText(String.valueOf(travel.getKapasitas()));
                etFasilitas.setText(travel.getFasilitasHotel());
            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTravel();
            }
        });
    }

    private void updateTravel() {
        String namaPaket = etNamaPaket.getText().toString();
        String tujuan = etTujuan.getText().toString();
        int durasi = Integer.parseInt(etDurasi.getText().toString());
        double harga = Double.parseDouble(etHarga.getText().toString());
        String tanggal = etTanggal.getText().toString();
        int kapasitas = Integer.parseInt(etKapasitas.getText().toString());
        String fasilitas = etFasilitas.getText().toString();

        travel.setNamaPaket(namaPaket);
        travel.setTujuan(tujuan);
        travel.setDurasi(durasi);
        travel.setHarga(harga);
        travel.setTanggalKeberangkatan(tanggal);
        travel.setKapasitas(kapasitas);
        travel.setFasilitasHotel(fasilitas);

        int rowsAffected = dbHelper.updateTravel(travel);
        if (rowsAffected > 0) {
            Toast.makeText(this, "Travel berhasil diperbarui", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Gagal memperbarui travel", Toast.LENGTH_SHORT).show();
        }
    }
}
