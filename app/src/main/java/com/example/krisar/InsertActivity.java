package com.example.krisar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InsertActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private String KrisarId;

    EditText inpNados, inpMatkul, inpKelas, inpKrisar;
    Button btnSubmit, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        inpNados = findViewById(R.id.inpNados);
        inpMatkul = findViewById(R.id.inpMatkul);
        inpKelas = findViewById(R.id.inpKelas);
        inpKrisar = findViewById(R.id.inpKrisar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("DataKrisar");
        KrisarId = databaseReference.push().getKey();
    }

    public void addKrisar(String nados, String matkul, String kelas, String krisar) {
        Krisar krisars = new Krisar(nados, matkul, kelas, krisar);
        databaseReference.child("Krisars").child(KrisarId).setValue(krisars);
    }

    public void InsertData(View view) {
        addKrisar(inpNados.getText().toString().trim(), inpMatkul.getText().toString().trim(), inpKelas.getText().toString().trim(), inpKrisar.getText().toString().trim());
    }

    public void ReadData(View view) {
        databaseReference.child("Krisars").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String dbnados = ds.child("nados").getValue(String.class);
                        String dbmatkul = ds.child("matkul").getValue(String.class);
                        String dbkelas = ds.child("kelas").getValue(String.class);
                        String dbkrisar = ds.child("krisar").getValue(String.class);

                        Log.d("TAG", dbnados+"/"+dbmatkul+"/"+dbkelas+"/"+dbkrisar);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
