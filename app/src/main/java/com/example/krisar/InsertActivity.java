package com.example.krisar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {
    EditText inpNados;
    EditText inpMatkul;
    EditText inpKelas;
    EditText inpKrisar;
    Button btnSubmit;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }
}
