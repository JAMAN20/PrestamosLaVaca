package com.example.payov2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.payov2.R;

public class SincronizacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sincronizacion);

        ImageView atras;
        atras = findViewById(R.id.atrasIconS);

        atras.setOnClickListener(v -> super.onBackPressed());

    }
}