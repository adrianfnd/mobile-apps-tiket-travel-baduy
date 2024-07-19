package com.example.uasmobiletikettravelbaduy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uasmobiletikettravelbaduy.models.Travel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "travel_baduy.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_TRAVEL = "travel";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA_PAKET = "nama_paket";
    public static final String COLUMN_TUJUAN = "tujuan";
    public static final String COLUMN_DURASI = "durasi";
    public static final String COLUMN_HARGA = "harga";
    public static final String COLUMN_TANGGAL_KEBERANGKATAN = "tanggal_keberangkatan";
    public static final String COLUMN_KAPASITAS = "kapasitas";
    public static final String COLUMN_FASILITAS_HOTEL = "fasilitas_hotel";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRAVEL_TABLE = "CREATE TABLE " + TABLE_TRAVEL + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAMA_PAKET + " TEXT,"
                + COLUMN_TUJUAN + " TEXT,"
                + COLUMN_DURASI + " INTEGER,"
                + COLUMN_HARGA + " REAL,"
                + COLUMN_TANGGAL_KEBERANGKATAN + " TEXT,"
                + COLUMN_KAPASITAS + " INTEGER,"
                + COLUMN_FASILITAS_HOTEL + " TEXT"
                + ")";
        db.execSQL(CREATE_TRAVEL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAVEL);
        onCreate(db);
    }

    public long tambahTravel(Travel travel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA_PAKET, travel.getNamaPaket());
        values.put(COLUMN_TUJUAN, travel.getTujuan());
        values.put(COLUMN_DURASI, travel.getDurasi());
        values.put(COLUMN_HARGA, travel.getHarga());
        values.put(COLUMN_TANGGAL_KEBERANGKATAN, travel.getTanggalKeberangkatan());
        values.put(COLUMN_KAPASITAS, travel.getKapasitas());
        values.put(COLUMN_FASILITAS_HOTEL, travel.getFasilitasHotel());
        long id = db.insert(TABLE_TRAVEL, null, values);
        db.close();
        return id;
    }

    public List<Travel> getAllTravel() {
        List<Travel> travelList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TRAVEL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Travel travel = new Travel();
                travel.setId(cursor.getInt(0));
                travel.setNamaPaket(cursor.getString(1));
                travel.setTujuan(cursor.getString(2));
                travel.setDurasi(cursor.getInt(3));
                travel.setHarga(cursor.getDouble(4));
                travel.setTanggalKeberangkatan(cursor.getString(5));
                travel.setKapasitas(cursor.getInt(6));
                travel.setFasilitasHotel(cursor.getString(7));
                travelList.add(travel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return travelList;
    }

    public int updateTravel(Travel travel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA_PAKET, travel.getNamaPaket());
        values.put(COLUMN_TUJUAN, travel.getTujuan());
        values.put(COLUMN_DURASI, travel.getDurasi());
        values.put(COLUMN_HARGA, travel.getHarga());
        values.put(COLUMN_TANGGAL_KEBERANGKATAN, travel.getTanggalKeberangkatan());
        values.put(COLUMN_KAPASITAS, travel.getKapasitas());
        values.put(COLUMN_FASILITAS_HOTEL, travel.getFasilitasHotel());
        int rowsAffected = db.update(TABLE_TRAVEL, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(travel.getId())});
        db.close();
        return rowsAffected;
    }

    public int deleteTravel(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRAVEL, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return id;
    }
}