package com.example.krisar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_update extends AppCompatActivity {
    private DatabaseReference database;
    private FirebaseAuth auth;
    EditText NamaDosen,Matakuliah,Kelas,KritikSaran;
    Button btnUpdate;
    Button btnDelete;
    Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        database = FirebaseDatabase.getInstance().getReference();

        NamaDosen = findViewById(R.id.inpNados);
        Matakuliah = findViewById(R.id.inpMatkul);
        Kelas = findViewById(R.id.inpKelas);
        KritikSaran = findViewById(R.id.inpKrisar);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_update.this, MainActivity.class));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Krisar krisar = new Krisar();
                onDeleteData(krisar);
                Intent intent = new Intent(activity_update.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final String getNados = getIntent().getExtras().getString("Nados");
        final String getMatkul = getIntent().getExtras().getString("MatKul");
        final String getKelas = getIntent().getExtras().getString("Kelas");
        final String getKrisar = getIntent().getExtras().getString("Krisar");


        NamaDosen.setText(getNados);
        Matakuliah.setText(getMatkul);
        Kelas.setText(getKelas);
        KritikSaran.setText(getKrisar);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Krisar krisar = new Krisar();
                krisar.setNados(NamaDosen.getText().toString());
                krisar.setMatkul(Matakuliah.getText().toString());
                krisar.setKelas(Kelas.getText().toString());
                krisar.setKrisar(KritikSaran.getText().toString());
                updateKrisar(krisar);
            }
        });



    }
    private void updateKrisar(Krisar krisar) {
//        String userID = auth.getUid();
//        String getKey = getIntent().getExtras().getString("KrisarID");
        String getId = getIntent().getExtras().getString("KrisarID");
        /**
         * Baris kode yang digunakan untuk mengupdate data barang
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        database.child("krisar")
                .child(getId)//akses parent index, ibaratnya seperti nama tabel
                .setValue(krisar) //set value barang yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update barang sukses
                         */
                        Snackbar.make(findViewById(R.id.btnUpdate), "Data berhasil diupdatekan", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(activity_update.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void submitupdate(Krisar krisar) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("barang").push().setValue(krisar).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                NamaDosen.setText("");
                Matakuliah.setText("");
                Kelas.setText("");
                KritikSaran.setText("");
                Snackbar.make(findViewById(R.id.btnUpdate), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }


    public void onDeleteData(Krisar krisar) {
        String getId = getIntent().getExtras().getString("KrisarID");
        /**
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter lewat interface.
         * Yang kemudian akan mendelete data di Firebase Realtime DB
         * berdasarkan key barang.
         * Jika sukses akan memunculkan Toast
         */
        if(database!=null){
            database.child("krisar").child(getId).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(activity_update.this,"success delete", Toast.LENGTH_LONG).show();
                }
            });

        }
    }

}
