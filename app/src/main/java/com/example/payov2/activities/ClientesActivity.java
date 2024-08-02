package com.example.payov2.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.payov2.MainActivity;
import com.example.payov2.R;
import com.example.payov2.adapters.ClientsAdapater;
import com.example.payov2.db.PayoDatabaseHelper;

import java.util.ArrayList;
import java.util.Currency;

public class ClientesActivity extends AppCompatActivity {

    ImageView delete;
    RecyclerView clientList;
    PayoDatabaseHelper db;
    ArrayList id, fullName, identification, phone, homePhone, mail, direction, refFullName, refPhone, refDirection;
    ClientsAdapater clientsAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_clientes);

        ImageView atras;
        atras = findViewById(R.id.atrasIconCC);

        atras.setOnClickListener(v -> super.onBackPressed());

        clientList = findViewById(R.id.listaClientes);
        delete = findViewById(R.id.ImgViewDeleteAll);

        db = new PayoDatabaseHelper(ClientesActivity.this);
        id = new ArrayList<>();
        fullName = new ArrayList<>();
        identification = new ArrayList<>();
        phone = new ArrayList<>();
        homePhone = new ArrayList<>();
        mail = new ArrayList<>();
        direction = new ArrayList<>();
        refFullName = new ArrayList<>();
        refPhone = new ArrayList<>();
        refDirection = new ArrayList<>();

        displayData();

        clientsAdapater = new ClientsAdapater(ClientesActivity.this,this, id, fullName, identification, phone, homePhone, mail, direction, refFullName, refPhone, refDirection);
        clientList.setAdapter(clientsAdapater);
        clientList.setLayoutManager(new LinearLayoutManager(ClientesActivity.this));

        delete.setOnClickListener(v -> confirmDialog());
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
                id.add(cursor.getString(0));
                fullName.add(cursor.getString(1));
                identification.add(cursor.getString(2));
                phone.add(cursor.getString(3));
                homePhone.add(cursor.getString(4));
                mail.add(cursor.getString(5));
                direction.add(cursor.getString(6));
                refFullName.add(cursor.getString(7));
                refPhone.add(cursor.getString(8));
                refDirection.add(cursor.getString(9));
            }
        }
    }

    void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Quieres borrar todos los clientes?");
        builder.setMessage("¿Estás seguro de que quieres borrar todos los clientes?, esta acción no es reversible");
        builder.setPositiveButton("Sí", (dialog, which) -> {
            PayoDatabaseHelper db = new PayoDatabaseHelper(ClientesActivity.this);
            db.deleteAllData();
            recreate();
        });
        builder.setNegativeButton("No", (dialog, which) -> {

        });
        builder.create().show();
    }

}