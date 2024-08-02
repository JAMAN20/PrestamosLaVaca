package com.example.payov2.activities;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.payov2.MainActivity;
import com.example.payov2.R;

public class CobrosDelDiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cobros_del_dia);

        ImageView atras;
        atras = findViewById(R.id.atrasIconC);

        atras.setOnClickListener(v -> super.onBackPressed());

    }
}