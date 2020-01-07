package com.example.krisar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InsertActivity extends AppCompatActivity {

    EditText inpNados, inpMatkul, inpKelas, inpKrisar;
    Button btnSubmit, btnCancel;

    DatabaseReference databaseKrisar;
    private String KrisarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseKrisar = FirebaseDatabase.getInstance().getReference("krisar");

        inpNados = findViewById(R.id.inpNados);
        inpMatkul = findViewById(R.id.inpMatkul);
        inpKelas = findViewById(R.id.inpKelas);
        inpKrisar = findViewById(R.id.inpKrisar);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backmain = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(backmain);
            }
        });
    }
    private void InsertData(){
        String nados = inpNados.getText().toString().trim();
        String matkul = inpMatkul.getText().toString().trim();
        String kelas = inpKelas.getText().toString().trim();
        String krisar = inpKrisar.getText().toString().trim();

        if(nados.isEmpty()){
            inpNados.setError("Masukkan Nama Dosen");
            inpNados.requestFocus();
        }
        else if(matkul.isEmpty()){
            inpMatkul.setError("Masukkan Nama Matakuliah");
            inpMatkul.requestFocus();
        }
        else if(kelas.isEmpty()){
            inpKelas.setError("Masukkan Kelas");
            inpKelas.requestFocus();
        }
        else if(krisar.isEmpty()){
            inpKrisar.setError("Masukkan Kritik dan Saran Anda");
            inpKrisar.requestFocus();
        }
        else if(nados.isEmpty() && matkul.isEmpty() && kelas.isEmpty() && krisar.isEmpty()){
            Toast.makeText(this, "Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
        }
        else if(!((nados.isEmpty() && matkul.isEmpty() && kelas.isEmpty() && krisar.isEmpty())))
        {
            String krisarid = databaseKrisar.push().getKey();

            Krisar krisarl = new Krisar(krisarid, nados, kelas, matkul, krisar);

            databaseKrisar.child(krisarid).setValue(krisarl);

            Toast.makeText(this, "Data Has been Inserted", Toast.LENGTH_SHORT).show();
            Intent toMain = new Intent(InsertActivity.this, MainActivity.class);
            startActivity(toMain);
        }

    }

}
