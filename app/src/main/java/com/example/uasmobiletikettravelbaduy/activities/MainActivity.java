package com.example.uasmobiletikettravelbaduy.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uasmobiletikettravelbaduy.R;
import com.example.uasmobiletikettravelbaduy.adapters.TravelAdapter;
import com.example.uasmobiletikettravelbaduy.database.DatabaseHelper;
import com.example.uasmobiletikettravelbaduy.models.Travel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TravelAdapter adapter;
    private DatabaseHelper dbHelper;
    private FloatingActionButton fabTambah;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fabTambah = findViewById(R.id.fabTambah);
        btnLogout = findViewById(R.id.btnLogout);

        dbHelper = new DatabaseHelper(this);
        List<Travel> travelList = dbHelper.getAllTravel();

        adapter = new TravelAdapter(this, travelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TambahTravelActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout() {
        SharedPreferences preferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            List<Travel> travelList = dbHelper.getAllTravel();
            adapter.updateData(travelList);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            List<Travel> travelList = dbHelper.getAllTravel();
            adapter.updateData(travelList);
        }
    }

    public void editTravel(Travel travel) {
        Intent intent = new Intent(MainActivity.this, EditTravelActivity.class);
        intent.putExtra("travel", travel);
        startActivityForResult(intent, 2);
    }

    public void deleteTravel(int id) {
        int rowsAffected = dbHelper.deleteTravel(id);
        if (rowsAffected > 0) {
            Toast.makeText(this, "Travel berhasil dihapus", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal menghapus travel", Toast.LENGTH_SHORT).show();
        }
        List<Travel> travelList = dbHelper.getAllTravel();
        adapter.updateData(travelList);
    }
}
