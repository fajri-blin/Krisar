package com.example.krisar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView listViewKrisars;

    List<Krisar> krisarList;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //listViewKrisars = findViewById(R.id.listViewKrisars);

        krisarList = new ArrayList<>();

        reff = FirebaseDatabase.getInstance().getReference().child("krisar");

    }

    @Override
    protected void onStart() {
        super.onStart();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                krisarList.clear();
                for(DataSnapshot krisarSnapshot : dataSnapshot.getChildren()){
                    Krisar krisar = krisarSnapshot.getValue(Krisar.class);

                    krisarList.add(krisar);
                }

                KrisarList adapter = new KrisarList(MainActivity.this, krisarList);
                listViewKrisars.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.add_menu){
            Intent Add = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(Add);
        }
        if(id==R.id.logout){
            FirebaseAuth.getInstance().signOut();
            Intent Login = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(Login);
            Toast.makeText(MainActivity.this,"Logout Success", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
