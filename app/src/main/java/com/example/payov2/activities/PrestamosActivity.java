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
import com.example.payov2.adapters.ViewLoansAdapter;
import com.example.payov2.db.PayoDatabaseHelper;

import java.util.ArrayList;

public class PrestamosActivity extends AppCompatActivity {

    RecyclerView loansList;
    PayoDatabaseHelper db;
    ArrayList loan_Id, clientFullName, clientDirection, paymentFrecuency, startDate, dueDate, capital, applyInterestTo, interest, numberOfPayments,
            applyInterestForDelay, interestForDelay, graceDays, total, quotaValue;
    ViewLoansAdapter viewLoansAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_prestamos);

        ImageView atras;
        atras = findViewById(R.id.atrasIconP);

        atras.setOnClickListener(v -> super.onBackPressed());

        loansList = findViewById(R.id.listaPrestamos);

        db = new PayoDatabaseHelper(PrestamosActivity.this);
        loan_Id = new ArrayList<>();
        clientFullName = new ArrayList<>();
        clientDirection = new ArrayList<>();
        paymentFrecuency = new ArrayList<>();
        startDate = new ArrayList<>();
        dueDate = new ArrayList<>();
        capital = new ArrayList<>();
        applyInterestTo = new ArrayList<>();
        interest = new ArrayList<>();
        numberOfPayments = new ArrayList<>();
        applyInterestForDelay = new ArrayList<>();
        interestForDelay = new ArrayList<>();
        graceDays = new ArrayList<>();
        total = new ArrayList<>();
        quotaValue = new ArrayList<>();
    try
    {
        displayData();
    }
    catch (Exception e)
    {
        Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
    }

        viewLoansAdapter = new ViewLoansAdapter(PrestamosActivity.this, this, loan_Id, clientFullName, clientDirection, paymentFrecuency, startDate, dueDate, capital, applyInterestTo, interest,
                numberOfPayments, applyInterestForDelay, interestForDelay, graceDays, total, quotaValue);
        loansList.setAdapter(viewLoansAdapter);
        loansList.setLayoutManager(new LinearLayoutManager(PrestamosActivity.this));
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
        Cursor cursor = db.readAllDataLoans();
        if(cursor.getCount() ==0)
        {
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                loan_Id.add(cursor.getString(0));
                clientFullName.add(cursor.getString(1));
                clientDirection.add(cursor.getString(2));
                paymentFrecuency.add(cursor.getString(3));
                startDate.add(cursor.getString(4));
                dueDate.add(cursor.getString(5));
                capital.add(cursor.getString(6));
                applyInterestTo.add(cursor.getString(7));
                interest.add(cursor.getString(8));
                numberOfPayments.add(cursor.getString(9));
                applyInterestForDelay.add(cursor.getString(10));
                interestForDelay.add(cursor.getString(11));
                graceDays.add(cursor.getString(12));
                total.add(cursor.getString(13));
                quotaValue.add(cursor.getString(14));
            }
        }
    }
}