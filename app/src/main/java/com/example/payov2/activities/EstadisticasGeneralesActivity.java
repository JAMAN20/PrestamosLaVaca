package com.example.payov2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.payov2.R;

public class EstadisticasGeneralesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_estadisticas_generales);

        ImageView atras;
        atras = findViewById(R.id.atrasIconE);

        atras.setOnClickListener(v -> super.onBackPressed());

    }
}