package com.example.payov2.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.payov2.R;
import com.example.payov2.adapters.ClientsAdapater;
import com.example.payov2.adapters.LoansAdapter;
import com.example.payov2.db.PayoDatabaseHelper;

import java.util.ArrayList;

public class NuevoPrestamoActivity extends AppCompatActivity {

    RecyclerView clientList;
    PayoDatabaseHelper db;
    LoansAdapter loansAdapter;
    ArrayList client_Id, client_FullName, clientDirection_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nuevo_prestamo);

        ImageView atras;
        atras = findViewById(R.id.atrasIconNP);

        atras.setOnClickListener(v -> super.onBackPressed());

        clientList = findViewById(R.id.listaClientesP);

        db = new PayoDatabaseHelper(NuevoPrestamoActivity.this);
        client_Id = new ArrayList<>();
        client_FullName = new ArrayList<>();
        clientDirection_client = new ArrayList<>();

        displayData();

        loansAdapter = new LoansAdapter(NuevoPrestamoActivity.this, this, client_Id, client_FullName, clientDirection_client);
        clientList.setAdapter(loansAdapter);
        clientList.setLayoutManager(new LinearLayoutManager(NuevoPrestamoActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }

    void displayData()
    {
        Cursor cursor = db.readAllData();
        if(cursor.getCount() ==0)
        {
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                client_Id.add(cursor.getString(0));
                client_FullName.add(cursor.getString(1));
                clientDirection_client.add(cursor.getString(6));
            }
        }
    }

}