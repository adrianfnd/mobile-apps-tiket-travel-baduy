package com.example.uasmobiletikettravelbaduy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Travel implements Parcelable {
    private int id;
    private String namaPaket;
    private String tujuan;
    private int durasi;
    private double harga;
    private String tanggalKeberangkatan;
    private int kapasitas;
    private String fasilitasHotel;

    public Travel() {}

    public Travel(int id, String namaPaket, String tujuan, int durasi, double harga,
                  String tanggalKeberangkatan, int kapasitas, String fasilitasHotel) {
        this.id = id;
        this.namaPaket = namaPaket;
        this.tujuan = tujuan;
        this.durasi = durasi;
        this.harga = harga;
        this.tanggalKeberangkatan = tanggalKeberangkatan;
        this.kapasitas = kapasitas;
        this.fasilitasHotel = fasilitasHotel;
    }

    // Getter dan Setter untuk semua field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getTanggalKeberangkatan() {
        return tanggalKeberangkatan;
    }

    public void setTanggalKeberangkatan(String tanggalKeberangkatan) {
        this.tanggalKeberangkatan = tanggalKeberangkatan;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getFasilitasHotel() {
        return fasilitasHotel;
    }

    public void setFasilitasHotel(String fasilitasHotel) {
        this.fasilitasHotel = fasilitasHotel;
    }

    protected Travel(Parcel in) {
        id = in.readInt();
        namaPaket = in.readString();
        tujuan = in.readString();
        durasi = in.readInt();
        harga = in.readDouble();
        tanggalKeberangkatan = in.readString();
        kapasitas = in.readInt();
        fasilitasHotel = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(namaPaket);
        dest.writeString(tujuan);
        dest.writeInt(durasi);
        dest.writeDouble(harga);
        dest.writeString(tanggalKeberangkatan);
        dest.writeInt(kapasitas);
        dest.writeString(fasilitasHotel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Travel> CREATOR = new Creator<Travel>() {
        @Override
        public Travel createFromParcel(Parcel in) {
            return new Travel(in);
        }

        @Override
        public Travel[] newArray(int size) {
            return new Travel[size];
        }
    };
}
