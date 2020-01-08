package com.example.krisar;

public class Krisar {
    String krisarid;
    String nados;
    String matkul;
    String kelas;
    String krisar;

    public Krisar(){

    }

    public Krisar(String krisarid, String nados, String matkul, String kelas, String krisar) {
        this.krisarid = krisarid;
        this.nados = nados;
        this.matkul = matkul;
        this.kelas = kelas;
        this.krisar = krisar;
    }

    public void setKrisarid(String krisarid) {
        this.krisarid = krisarid;
    }

    public void setNados(String nados) {
        this.nados = nados;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public void setKrisar(String krisar) {
        this.krisar = krisar;
    }

    public String getKrisarid() {
        return krisarid;
    }

    public String getNados() {
        return nados;
    }

    public String getMatkul() {
        return matkul;
    }

    public String getKelas() {
        return kelas;
    }


    public String getKrisar() {
        return krisar;
    }
}

