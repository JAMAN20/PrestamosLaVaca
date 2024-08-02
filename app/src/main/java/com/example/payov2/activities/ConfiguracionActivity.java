package com.example.payov2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.payov2.R;

public class ConfiguracionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_configuracion);

        ImageView atras;
        atras = findViewById(R.id.atrasIconCF);

        atras.setOnClickListener(v -> super.onBackPressed());

    }
}